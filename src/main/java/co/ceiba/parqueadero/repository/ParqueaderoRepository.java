package co.ceiba.parqueadero.repository;

import java.util.Calendar;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;

public interface ParqueaderoRepository {
	boolean insertar(Vehiculo vehiculo, Calendar fechaIngreso) throws Exception;
	boolean eliminar(String placa) throws Exception;
	double calcularMonto(Parqueadero parqueadero);
	Parqueadero obtenerPorVehiculoSinSalir(String placa) throws Exception;
	Parqueadero obtenerPorVehiculo(String placa) throws Exception;
	double salidaParqueadero(String placa) throws Exception;
	boolean ingresarVehiculo(String placa, int cilindraje) throws Exception;
	int[] obtenerCantidadVehiculos() throws Exception ;
	long cantidadHoras(Calendar ingreso, Calendar salida);
}
