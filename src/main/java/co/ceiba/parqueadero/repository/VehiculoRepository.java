package co.ceiba.parqueadero.repository;

import java.util.List;

import co.ceiba.parqueadero.exception.VehiculoException;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Vehiculo;

/**
 * Se exponen los metodos para funciones sobre base de datos para
 * Vehiculo
 * @author daniel.correa
 *
 */
public interface VehiculoRepository {
	/**
	 * 
	 * @return Todos los vehiculos registrados que se encuentran o que alguna vez estuvieron en el parqueadero
	 * @throws VehiculoException
	 */
	List<Vehiculo> obtenerTodos() throws VehiculoException;
	
	/**
	 * 
	 * @param placa del vehiculo a ingresar
	 * @param cilindraje si es cero es un carro en otro caso es una moto
	 * @return registro del vehiculo ingresado
	 * @throws VehiculoException
	 */
	Vehiculo insertar(String placa, int cilindraje) throws VehiculoException;
	
	/**
	 * 
	 * @param placa del vehiculo a eliminar
	 * @return true si fue eliminado con exito
	 * @throws VehiculoException
	 */
	boolean eliminar(String placa) throws VehiculoException;
	
	/**
	 * 
	 * @param placa del vehiculo a consultar
	 * @return 
	 * @throws VehiculoException
	 */
	Vehiculo obtenerPorPlaca(String placa) throws VehiculoException;
	
	/**
	 * 
	 * @param placa de la moto a consultar
	 * @return
	 * @throws VehiculoException
	 */
	Moto obtenerMotoPorPlaca(String placa) throws VehiculoException;

}
