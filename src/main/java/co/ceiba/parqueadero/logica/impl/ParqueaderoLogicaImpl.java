package co.ceiba.parqueadero.logica.impl;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.exception.ParqueaderoLogicaException;
import co.ceiba.parqueadero.logica.ParqueaderoLogica;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;
import co.ceiba.parqueadero.repository.ParqueaderoRepository;
import co.ceiba.parqueadero.repository.VehiculoRepository;
import co.ceiba.parqueadero.utils.Logica;

@Transactional
@Service
public class ParqueaderoLogicaImpl implements ParqueaderoLogica {
	
	@Autowired
	ParqueaderoRepository parqueaderoRepository;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Override
	public double calcularMonto(Parqueadero parqueadero) {
		long horas=cantidadHoras(parqueadero.getFechaIngreso(),parqueadero.getFechaSalida());
		long dias= horas/24;
		long horasDia= horas%24;
		double monto=0;
		if(parqueadero.getVehiculo().getTipo().equals("2")) {
			if(horasDia>=Logica.MINIMO_HORAS_DIA) {
				monto=(dias+1)*Logica.DIA_CARRO;
			}else{
				monto=dias*Logica.DIA_CARRO + horasDia*Logica.HORA_CARRO;
			}
		}else if(parqueadero.getVehiculo().getTipo().equals("1")) {
			Moto moto= (Moto)parqueadero.getVehiculo();
			if(horasDia>=Logica.MINIMO_HORAS_DIA) {
				monto=(dias+1)*Logica.DIA_MOTO;
			}else {
				monto=dias*Logica.DIA_MOTO + horasDia*Logica.HORA_MOTO;
			}
			if(moto.getCilindraje()>=Logica.CILINDRAJE) {
				monto+=2000;
			}
		}
		return monto;
	}

	@Override
	public double salidaParqueadero(String placa) throws ParqueaderoLogicaException {
		try{
			Parqueadero parq= parqueaderoRepository.obtenerPorVehiculoSinSalir(placa);
			Calendar salida=Calendar.getInstance();
			salida.add(Calendar.HOUR_OF_DAY, 8); //Codigo por eliminar
			salida.add(Calendar.MINUTE, 10);  //Codigo por eliminar
			parq.setFechaSalida(salida);
			parqueaderoRepository.actualizar(parq);
			return calcularMonto(parq);
		}catch(Exception e) {
			throw new ParqueaderoLogicaException("no fue posible registrar la salida del vehiculo"
					+ "del parqueadero",e);
		}
	}

	@Override
	public boolean ingresarVehiculo(String placa, int cilindraje) throws ParqueaderoLogicaException {
		try {
			int[] cantidadVehiculos=parqueaderoRepository.obtenerCantidadVehiculos();
			if(cantidadVehiculos[0]>Logica.CANTIDAD_MAXIMA_CARROS && cilindraje==0) {
				throw new ParqueaderoException("El parqueadero no puede recibir mas carros");
			}
			if(cantidadVehiculos[1]>Logica.CANTIDAD_MAXIMA_MOTOS && cilindraje >0) {
				throw new ParqueaderoException("El parqueadero no puede recibir mas motos");
			}
			Calendar fecha=Calendar.getInstance();
			if(placa.substring(0, 1).equalsIgnoreCase(Logica.LETRA_PLACA) && 
					(Logica.DIAS_RESTRINGIDOS.contains(fecha.get(Calendar.DAY_OF_WEEK))))  {
				throw new ParqueaderoException("Esta placa no puede ser utilizada los dias"
						+ "lunes y domingo");
			}
			Vehiculo vehiculo=vehiculoRepository.obtenerPorPlaca(placa);
			if(vehiculo==null) {
				vehiculo=vehiculoRepository.insertar(placa, cilindraje);
			}
			parqueaderoRepository.insertar(vehiculo, fecha);
			return true;
		}catch(Exception e) {
			throw new ParqueaderoLogicaException("No fue posible agregar el registro al parqueadero"
					+ " en la base de datos",e);
		}
	}

	@Override
	public long cantidadHoras(Calendar ingreso, Calendar salida) {
		long end= salida.getTimeInMillis();
		long start= ingreso.getTimeInMillis();
		return TimeUnit.MILLISECONDS.toHours(Math.abs(end - start));
	}

}
