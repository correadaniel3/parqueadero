package co.ceiba.parqueadero.repository;

import java.util.Calendar;
import java.util.List;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;

public interface ParqueaderoRepository {
	boolean insertar(Vehiculo vehiculo, Calendar fechaIngreso) throws ParqueaderoException;
	Parqueadero actualizar(String placa, Calendar fechaSalida) throws ParqueaderoException;
	Parqueadero obtenerPorVehiculoSinSalir(String placa) throws ParqueaderoException;
	List<Parqueadero> obtenerVehiculos() throws ParqueaderoException;
	List<Parqueadero> obtenerCarros() throws ParqueaderoException;
	List<Parqueadero> obtenerMotos() throws ParqueaderoException;
}
