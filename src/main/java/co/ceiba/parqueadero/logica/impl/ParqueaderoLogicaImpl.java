package co.ceiba.parqueadero.logica.impl;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.exception.ParqueaderoLogicaException;
import co.ceiba.parqueadero.exception.VehiculoException;
import co.ceiba.parqueadero.logica.ParqueaderoLogica;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;
import co.ceiba.parqueadero.repository.ParqueaderoRepository;
import co.ceiba.parqueadero.repository.VehiculoRepository;
import co.ceiba.parqueadero.utils.Constantes;
import co.ceiba.parqueadero.utils.Mensajes;
import lombok.Generated;

/**
 * Se implementan los metodos de la logica del negocio del parqueadero
 * @author daniel.correa
 *
 */
@Transactional
@Service
public class ParqueaderoLogicaImpl implements ParqueaderoLogica {
	
	@Autowired
	ParqueaderoRepository parqueaderoRepository;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	/*
	 * 
	 * @see co.ceiba.parqueadero.logica.ParqueaderoLogica#calcularMonto(co.ceiba.parqueadero.modelo.Parqueadero)
	 */
	@Override
	public double calcularMonto(Parqueadero parqueadero) throws VehiculoException {
		long horas=cantidadHoras(parqueadero.getFechaIngreso(),parqueadero.getFechaSalida());
		long minutos=cantidadMinutos(parqueadero.getFechaIngreso(),parqueadero.getFechaSalida());
		long minutosHora=minutos%60;
		long dias= horas/24;
		long horasDia= horas%24;
		double monto=0;
		if(minutosHora>0) {horasDia++;}
		monto=calcularMontoVehiculo(dias, horasDia, parqueadero.getVehiculo());
		return monto;
	}
	
	/**
	 * se agrega logica en caso de que el vehiculo haya pasado menos de una hora
	 * se cobre la cantidad de una hora
	 * @param dias que ha pasado el vehiculo en el parqueadero
	 * @param horasDia cantidad de horas dentro de un dia 
	 * @param veh vehiculo a calcular el monto
	 * @return monto a pagar por la estadia
	 * @throws VehiculoException
	 */
	private double calcularMontoVehiculo(long dias, long horasDia, Vehiculo veh) throws VehiculoException {
		double monto=0;
		if(horasDia>=Constantes.MINIMO_HORAS_DIA) {
			if(veh.getTipo().equals("2")) {monto+=(dias+1)*Constantes.DIA_CARRO;}
			else {monto+=(dias+1)*Constantes.DIA_MOTO;}
		}else {
			if(veh.getTipo().equals("2")) {monto+=dias*Constantes.DIA_CARRO + horasDia*Constantes.HORA_CARRO;}
			else {monto+=dias*Constantes.DIA_MOTO + horasDia*Constantes.HORA_MOTO;}
		}
		if(veh.getTipo().equals("1")) {
			Moto moto= vehiculoRepository.obtenerMotoPorPlaca(veh.getPlaca());
			if(moto.getCilindraje()>=Constantes.CILINDRAJE) {
				monto+=2000;
			}
		}
		if(monto==0) {
			if(veh.getTipo().equals("2")) {monto+=Constantes.HORA_CARRO;}
			else {monto+=Constantes.HORA_MOTO;}
		}
		return monto;
	}

	/*
	 * 
	 * @see co.ceiba.parqueadero.logica.ParqueaderoLogica#salidaParqueadero(java.lang.String)
	 */
	@Generated
	@Override
	public double salidaParqueadero(String placa) throws ParqueaderoLogicaException {
		try{
			Calendar salida=Calendar.getInstance();
			Parqueadero parq=parqueaderoRepository.actualizar(placa, salida);
			return calcularMonto(parq);
		}catch(Exception e) {
			throw new ParqueaderoLogicaException(Mensajes.ERROR_SALIDA_VEHICULO,e);
		}
	}

	/*
	 * 
	 * @see co.ceiba.parqueadero.logica.ParqueaderoLogica#ingresarVehiculo(java.lang.String, int)
	 */
	@Generated
	@Override
	public boolean ingresarVehiculo(String placa, int cilindraje) throws ParqueaderoLogicaException {
		try {
			Calendar fecha=Calendar.getInstance();
			validaciones(placa, cilindraje, fecha);
			Vehiculo vehiculo=vehiculoRepository.obtenerPorPlaca(placa);
			if(vehiculo==null) {
				vehiculo=vehiculoRepository.insertar(placa, cilindraje);
			}
			parqueaderoRepository.insertar(vehiculo, fecha);
			return true;
		}catch(Exception e) {
			throw new ParqueaderoLogicaException(Mensajes.ERROR_INSERTAR,e);
		}
	}
	
	/**
	 * metodo que resume las validaciones de ingreso como la capacidad,
	 * el dia y la placa del vehiculo
	 * @param placa del vehiculo a validar ingreso
	 * @param cilindraje del vehiculo
	 * @param fecha fecha en la que ingresa el vehiculo
	 * @throws Exception
	 */
	@Generated
	public void validaciones(String placa, int cilindraje, Calendar fecha) throws Exception {
		validarExistencia(placa);
		validarCantidad(cilindraje);
		validarRestricciones(placa, fecha);
	}
	
	/**
	 * Metodo que verifica si el vehiculo ya se encuentra en el parqueadero
	 * @param placa del vehiculo
	 * @throws ParqueaderoException
	 */
	@Generated
	public void validarExistencia(String placa) throws ParqueaderoException {
		Parqueadero parq=parqueaderoRepository.obtenerPorVehiculoSinSalir(placa);
		if(parq!=null) {
			throw new ParqueaderoException(Mensajes.ERROR_DUPLICADO);
		}
	}
	
	/**
	 * Valida si la placa y el dia permite al vehiculo entrar al parqueadero
	 * @param placa del vehiculo a validar
	 * @param fecha de entrada
	 * @throws ParqueaderoException
	 */
	@Generated
	public void validarRestricciones(String placa, Calendar fecha) throws ParqueaderoException {
		if(placa.substring(0, 1).equalsIgnoreCase(Constantes.LETRA_PLACA) && 
				(Constantes.getDiasRestringidos().contains(fecha.get(Calendar.DAY_OF_WEEK))))  {
			throw new ParqueaderoException(Mensajes.RESTRICCION_PLACA);
		}
	}
	
	/**
	 * Valida la cantidad maxima de carros y motos que soporta el parqueadero
	 * @param cilindraje necesario para verificar si el vehiculo es carro o moto
	 * @throws ParqueaderoException
	 */
	@Generated
	public void validarCantidad(int cilindraje) throws ParqueaderoException {
		int cantidadCarros=parqueaderoRepository.obtenerCarros().size();
		int cantidadMotos=parqueaderoRepository.obtenerMotos().size();
		if(cantidadCarros>=Constantes.CANTIDAD_MAXIMA_CARROS && cilindraje==0) {
			throw new ParqueaderoException(Mensajes.CAPACIDAD_CARROS);
		}
		if(cantidadMotos>=Constantes.CANTIDAD_MAXIMA_MOTOS && cilindraje >0) {
			throw new ParqueaderoException(Mensajes.CAPACIDAD_MOTOS);
		}
	}

	/*
	 * 
	 * @see co.ceiba.parqueadero.logica.ParqueaderoLogica#cantidadHoras(java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public long cantidadHoras(Calendar ingreso, Calendar salida) {
		long end= salida.getTimeInMillis();
		long start= ingreso.getTimeInMillis();
		return TimeUnit.MILLISECONDS.toHours(Math.abs(end - start));
	}
	
	/*
	 * 
	 * @see co.ceiba.parqueadero.logica.ParqueaderoLogica#cantidadMinutos(java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public long cantidadMinutos(Calendar ingreso, Calendar salida) {
		long end= salida.getTimeInMillis();
		long start= ingreso.getTimeInMillis();
		return TimeUnit.MILLISECONDS.toMinutes(Math.abs(end - start));
	}

}
