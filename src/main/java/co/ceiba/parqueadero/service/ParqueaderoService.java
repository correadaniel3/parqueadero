package co.ceiba.parqueadero.service;
import java.util.List;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.exception.ParqueaderoServiceException;
import co.ceiba.parqueadero.modelo.Parqueadero;

/**
 * Se exponen los metodos que se conectaran con el controlador para exponer 
 * los servicios REST
 * @author daniel.correa
 *
 */
public interface ParqueaderoService {
	/**
	 * se verifica que los parametros sean correctos para proceder con el ingreso
	 * @param placa del vehiculo a ingresar
	 * @param cilindraje si es cero ingresa un carro, en otro caso ingresa una moto
	 * @return true si el vehiculo ingreso sin problemas
	 * @throws ParqueaderoServiceException
	 */
	boolean ingresarVehiculoParqueadero(String placa, int cilindraje) throws ParqueaderoServiceException;
	
	/**
	 * se verifica que los parametros sean correctos para proceder con la salida
	 * @param placa del vehiculo a salir
	 * @return monto a pagar por la estadia
	 * @throws ParqueaderoServiceException
	 */
	double salidaVehiculoParqueadero(String placa) throws ParqueaderoServiceException;
	
	/**
	 * 
	 * @return lista de todos los vehiculos en el parqueadero
	 * @throws ParqueaderoException
	 */
	List<Parqueadero> obtenerVehiculos() throws ParqueaderoException;
}
