package co.ceiba.parqueadero.repository;

import java.util.Calendar;
import java.util.List;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;

public interface ParqueaderoRepository {
	boolean insertar(Vehiculo vehiculo, Calendar fechaIngreso) throws ParqueaderoException;
	boolean eliminar(String placa) throws ParqueaderoException;
	boolean actualizar(Parqueadero parqueadero) throws ParqueaderoException;
	Parqueadero obtenerPorVehiculoSinSalir(String placa) throws ParqueaderoException;
	Parqueadero obtenerPorVehiculo(String placa) throws ParqueaderoException;
	int[] obtenerCantidadVehiculos() throws ParqueaderoException ;
	List<Parqueadero> obtenerVehiculos() throws ParqueaderoException;
}
