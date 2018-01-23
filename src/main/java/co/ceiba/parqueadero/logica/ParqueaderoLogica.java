package co.ceiba.parqueadero.logica;

import java.util.Calendar;

import co.ceiba.parqueadero.exception.ParqueaderoLogicaException;
import co.ceiba.parqueadero.exception.VehiculoException;
import co.ceiba.parqueadero.modelo.Parqueadero;
/**
 * Se exponen los metodos de la logica del negocio del parqueadero
 * @author daniel.correa
 *
 */
public interface ParqueaderoLogica {
	/**
	 * 
	 * @param parqueadero registro del vehiculo en el parqueadero al que se desea calcular el monto
	 * @return valor a pagar
	 * @throws VehiculoException
	 */
	double calcularMonto(Parqueadero parqueadero) throws VehiculoException;
	
	/**
	 * 
	 * @param placa del vehiculo al que se desea dar salida
	 * @return monto a pagar por la estadia del vehiculo
	 * @throws ParqueaderoLogicaException
	 */
	double salidaParqueadero(String placa) throws ParqueaderoLogicaException;
	
	/**
	 * 
	 * @param placa del vehiculo a ingresar al parqueadero
	 * @param cilindraje si es cero se ingresa un carro, en otro caso ingresa una moto
	 * @return true si ingresa con exito
	 * @throws ParqueaderoLogicaException
	 */
	boolean ingresarVehiculo(String placa, int cilindraje) throws ParqueaderoLogicaException;
	
	/**
	 * 
	 * @param ingreso fecha en la cual ingreso el vehiculo
	 * @param salida fecha en la cual sale el vehiculo
	 * @return cantidad de horas entre la entrada y salida del vehiculo
	 */
	long cantidadHoras(Calendar ingreso, Calendar salida);
	
	/**
	 * 
	 * @param ingreso fecha en la cual ingreso el vehiculo
	 * @param salida fecha en la cual sale el vehiculo
	 * @return cantidad de minutos entre la entrada y salida del vehiculo
	 */
	long cantidadMinutos(Calendar ingreso, Calendar salida);
}
