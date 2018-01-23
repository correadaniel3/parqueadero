package co.ceiba.parqueadero.repository;

import java.util.Calendar;
import java.util.List;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;

/**
 * Se exponen los metodos para funciones sobre base de datos para
 * parqueadero
 * @author daniel.correa
 *
 */
public interface ParqueaderoRepository {
	/**
	 * 
	 * @param vehiculo
	 * @param fechaIngreso
	 * @return true si fue insertado con exito
	 * @throws ParqueaderoException
	 */
	boolean insertar(Vehiculo vehiculo, Calendar fechaIngreso) throws ParqueaderoException;
	
	/**
	 * 
	 * @param placa del vehiculo a actualizar
	 * @param fechaSalida
	 * @return Registro del vehiculo en el parqueadero con su campo actualizado
	 * @throws ParqueaderoException
	 */
	Parqueadero actualizar(String placa, Calendar fechaSalida) throws ParqueaderoException;
	
	/**
	 * 
	 * @param placa del vehiculo dentro del parqueadero
	 * @return registro del vehiculo que se encuentra en el parqueadero
	 * @throws ParqueaderoException
	 */
	Parqueadero obtenerPorVehiculoSinSalir(String placa) throws ParqueaderoException;
	
	/**
	 * 
	 * @return lista de todos los vehiculos que se encuentran en el parqueadero
	 * @throws ParqueaderoException
	 */
	List<Parqueadero> obtenerVehiculos() throws ParqueaderoException;
	
	/**
	 * 
	 * @return lista de todos los carros que se encuentran en el parqueadero
	 * @throws ParqueaderoException
	 */
	List<Parqueadero> obtenerCarros() throws ParqueaderoException;
	
	/**
	 * lista de todas las motos que se encuentran en el parqueadero
	 * @return
	 * @throws ParqueaderoException
	 */
	List<Parqueadero> obtenerMotos() throws ParqueaderoException;
}
