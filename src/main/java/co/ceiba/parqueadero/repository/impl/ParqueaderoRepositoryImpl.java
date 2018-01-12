package co.ceiba.parqueadero.repository.impl;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;
import co.ceiba.parqueadero.repository.ParqueaderoRepository;
import co.ceiba.parqueadero.repository.VehiculoRepository;
import co.ceiba.parqueadero.utils.Tarifa;

@Transactional
@Repository
public class ParqueaderoRepositoryImpl implements ParqueaderoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Override
	public boolean insertar(Vehiculo vehiculo, Calendar fechaIngreso) throws Exception {
		Parqueadero parq;
		try {
			parq=new Parqueadero(vehiculo, fechaIngreso);
			entityManager.persist(parq);
			return true;
		}catch(Exception e) {
			throw new Exception("No fue posible agregar el registro al parqueadero"
					+ " en la base de datos",e);
		}
	}

	@Override
	public boolean eliminar(String placa) throws Exception {
		try{
			Parqueadero parq = obtenerPorVehiculo(placa);
			entityManager.remove(parq);
			return true;
		}catch(Exception e) {
			throw new Exception("No fue posible eliminar el vehiculo del registro del"
					+ " parqueadero en la base de datos",e);
		}
	}

	@Override
	public double calcularMonto(Parqueadero parqueadero) {
		long horas=cantidadHoras(parqueadero.getFechaIngreso(),parqueadero.getFechaSalida());
		Tarifa tarifa=new Tarifa();
		long dias= horas/24;
		long horasDia= horas%24;
		double monto=0;
		if(parqueadero.getVehiculo().getTipo().equals("2")) {
			if(horasDia>=9) {
				monto=(dias+1)*tarifa.getDiaCarro();
			}else{
				monto=dias*tarifa.getDiaCarro() + horasDia*tarifa.getHoraCarro();
			}
		}else if(parqueadero.getVehiculo().getTipo().equals("1")) {
			Moto moto= (Moto)parqueadero.getVehiculo();
			if(horasDia>=9) {
				monto=(dias+1)*tarifa.getDiaMoto();
			}else {
				monto=dias*tarifa.getDiaMoto() + horasDia*tarifa.getHoraMoto();
			}
			if(moto.getCilindraje()>=500) {
				monto+=2000;
			}
		}
		return monto;
	}
	
	@Override
	public long cantidadHoras(Calendar ingreso, Calendar salida) {
		long end= salida.getTimeInMillis();
		long start= ingreso.getTimeInMillis();
		return TimeUnit.MILLISECONDS.toHours(Math.abs(end - start));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Parqueadero obtenerPorVehiculoSinSalir(String placa) throws Exception {
		try {
			List<Parqueadero> parq = null;
			Vehiculo test=new Vehiculo(placa); 
			String hql = "FROM Parqueadero as parq WHERE parq.vehiculo = ? AND parq.fechaSalida is null";
			parq = (List<Parqueadero>) entityManager.createQuery(hql).setParameter(1, test)
					.getResultList();
			return parq.get(0);
		}catch (Exception e) {
			throw new Exception("No fue posible obtener el registro del vehiculo en el parqueadero "
					+ " de la base de datos",e);
		}
	}

	@Override
	public double salidaParqueadero(String placa) throws Exception {
		try{
			Parqueadero parq= obtenerPorVehiculoSinSalir(placa);
			Calendar salida=Calendar.getInstance();
			salida.add(Calendar.HOUR_OF_DAY, 8);
			salida.add(Calendar.MINUTE, 10);
			parq.setFechaSalida(salida);
			entityManager.flush();
			return calcularMonto(parq);
		}catch(Exception e) {
			throw new Exception("no fue posible registrar la salida del vehiculo"
					+ "del parqueadero",e);
		}
	}

	@Override
	public boolean ingresarVehiculo(String placa, int cilindraje) throws Exception {
		try {
			int[] cantidadVehiculos=obtenerCantidadVehiculos();
			if(cantidadVehiculos[0]>20 && cilindraje==0) {
				throw new Exception("El parqueadero no puede recibir mas carros");
			}
			if(cantidadVehiculos[1]>10 && cilindraje >0) {
				throw new Exception("El parqueadero no puede recibir mas motos");
			}
			Calendar fecha=Calendar.getInstance();
			if(placa.substring(0, 1).equalsIgnoreCase("A") && 
					(fecha.get(Calendar.DAY_OF_WEEK)!=1 || 
					fecha.get(Calendar.DAY_OF_WEEK)!=2))  {
				return false;
			}
			Vehiculo vehiculo=vehiculoRepository.obtenerPorPlaca(placa);
			if(vehiculo==null) {
				vehiculo=vehiculoRepository.insertar(placa, cilindraje);
			}
			insertar(vehiculo, fecha);
			return true;
		}catch(Exception e) {
			throw new Exception("No fue posible agregar el registro al parqueadero"
					+ " en la base de datos",e);
		}
	}

	@Override
	public int[] obtenerCantidadVehiculos() throws Exception {
		try {
			int[] totales= {0,0};
			totales[0]=vehiculoRepository.obtenerCarros().size();
			totales[1]=vehiculoRepository.obtenerMotos().size();
			return totales;
		}catch(Exception e) {
			throw new Exception("Error al calcular la cantidad de vehiculos"
					+ " en el parqueadero", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Parqueadero obtenerPorVehiculo(String placa) throws Exception {
		try {
			List<Parqueadero> parq = null;
			String hql = "FROM Parqueadero";
			parq = (List<Parqueadero>) entityManager.createQuery(hql).getResultList();
			
			for(Parqueadero parqueadero:parq) {
				if(parqueadero.getVehiculo().getPlaca().equals(placa)) {
					
					return parqueadero;
				}
			}
			return null;
		}catch (Exception e) {
			throw new Exception("No fue posible obtener el registro del vehiculo en el parqueadero "
					+ " de la base de datos",e);
		}
	}
	

}
