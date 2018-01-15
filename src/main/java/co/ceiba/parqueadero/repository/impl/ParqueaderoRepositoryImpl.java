package co.ceiba.parqueadero.repository.impl;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;
import co.ceiba.parqueadero.repository.ParqueaderoRepository;
import co.ceiba.parqueadero.repository.VehiculoRepository;

@Transactional
@Repository
public class ParqueaderoRepositoryImpl implements ParqueaderoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Override
	public boolean insertar(Vehiculo vehiculo, Calendar fechaIngreso) throws ParqueaderoException {
		Parqueadero parq;
		try {
			parq=new Parqueadero(vehiculo, fechaIngreso);
			entityManager.persist(parq);
			return true;
		}catch(Exception e) {
			throw new ParqueaderoException("No fue posible agregar el registro al parqueadero"
					+ " en la base de datos",e);
		}
	}

	@Override
	public boolean eliminar(String placa) throws ParqueaderoException {
		try{
			Parqueadero parq = obtenerPorVehiculo(placa);
			entityManager.remove(parq);
			return true;
		}catch(Exception e) {
			throw new ParqueaderoException("No fue posible eliminar el vehiculo del registro del"
					+ " parqueadero en la base de datos",e);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Parqueadero obtenerPorVehiculoSinSalir(String placa) throws ParqueaderoException {
		try {
			List<Parqueadero> parq = null;
			Vehiculo test=new Vehiculo(placa); 
			String hql = "FROM Parqueadero as parq WHERE parq.vehiculo = ? AND parq.fechaSalida is null";
			parq = (List<Parqueadero>) entityManager.createQuery(hql).setParameter(1, test)
					.getResultList();
			return parq.get(0);
		}catch (Exception e) {
			throw new ParqueaderoException("No fue posible obtener el registro del vehiculo en el parqueadero "
					+ " de la base de datos",e);
		}
	}


	@Override
	public int[] obtenerCantidadVehiculos() throws ParqueaderoException {
		try {
			int[] totales= {0,0};
			totales[0]=vehiculoRepository.obtenerCarros().size();
			totales[1]=vehiculoRepository.obtenerMotos().size();
			return totales;
		}catch(Exception e) {
			throw new ParqueaderoException("Error al calcular la cantidad de vehiculos"
					+ " en el parqueadero", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Parqueadero obtenerPorVehiculo(String placa) throws ParqueaderoException {
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
			throw new ParqueaderoException("No fue posible obtener el registro del vehiculo en el parqueadero "
					+ " de la base de datos",e);
		}
	}

	@Override
	public boolean actualizar(Parqueadero parqueadero) throws ParqueaderoException {
		try {
			Parqueadero parq= obtenerPorVehiculo(parqueadero.getVehiculo().getPlaca());
			parq.setFechaSalida(parqueadero.getFechaSalida());
			entityManager.flush();
			return true;
		} catch (Exception e) {
			throw new ParqueaderoException("No fue posible actualizar el registro del vehiculo"
					+ "en el parqueadero", e);
		}
	}
	

}
