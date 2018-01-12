package co.ceiba.parqueadero.repository;

import java.util.Calendar;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;

public interface ParqueaderoRepository {
	boolean insertar(Vehiculo vehiculo, Calendar fechaIngreso) throws ParqueaderoException;
	boolean eliminar(String placa) throws ParqueaderoException;
	double calcularMonto(Parqueadero parqueadero);
	Parqueadero obtenerPorVehiculoSinSalir(String placa) throws ParqueaderoException;
	Parqueadero obtenerPorVehiculo(String placa) throws ParqueaderoException;
	double salidaParqueadero(String placa) throws ParqueaderoException;
	boolean ingresarVehiculo(String placa, int cilindraje) throws ParqueaderoException;
	int[] obtenerCantidadVehiculos() throws ParqueaderoException ;
	long cantidadHoras(Calendar ingreso, Calendar salida);
}
