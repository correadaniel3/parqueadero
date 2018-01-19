package co.ceiba.parqueadero.utils;

import org.springframework.stereotype.Component;

@Component
public class Mensajes {
	
	//VehiculoRepository
	public static final String ERROR_TODOS_LOS_VEHICULOS="Error al obtener todos los vehiculos de la BD";
	public static final String ERROR_INSERTAR="No fue posible agregar el vehiculo en la base de datos";
	public static final String ERROR_ELIMINAR="No fue posible eliminar el vehiculo de la base de datos";
	public static final String ERROR_OBTENER_VEHICULO="No fue posible Obtener el vehiculo de la base de datos";
	public static final String ERROR_OBTENER_MOTO="Error al obtener la moto de la BD";
	
	//ParqueaderoRepository
	public static final String ERROR_REGISTRO="No fue posible agregar el registro al parqueadero";
	public static final String ERROR_OBTENER_SIN_SALIR="No fue posible obtener el registro del vehiculo en el parqueadero";
	public static final String ERROR_OBTENER_TODOS_SIN_SALIR="No fue posible obtener el registro de los vehiculos en el parqueadero";
	public static final String ERROR_ACTUALIZAR_VEHICULO="No fue posible actualizar el registro del vehiculo";
	public static final String ERROR_OBTENER_CARROS="Error al obtener todos los carros de la BD";
	public static final String ERROR_OBTENER_MOTOS="Error al obtener todas las motos de la BD";
	
	//ParqueaderoLogica
	public static final String ERROR_SALIDA_VEHICULO="no fue posible registrar la salida del vehiculo";
	public static final String ERROR_DUPLICADO="El vehiculo ya se encuentra en el parqueadero";
	public static final String RESTRICCION_PLACA="Esta placa no puede ser utilizada los dias lunes y domingo";
	public static final String CAPACIDAD_CARROS="El parqueadero no puede recibir mas carros";
	public static final String CAPACIDAD_MOTOS="El parqueadero no puede recibir mas motos";
	
	//ParqueaderoService
	public static final String PLACA_VACIA="La placa del vehiculo no puede ser vacia";
	public static final String PLACA_INVALIDA="No se ha ingresado una placa valida";
	public static final String CILINDRAJE_INVALIDO="El cilindraje no puede ser menor a cero";
	
	private Mensajes() {}
}
