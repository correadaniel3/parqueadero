package co.ceiba.parqueadero.repository;

import java.util.Calendar;

import co.ceiba.parqueadero.modelo.Vehiculo;

public interface ParqueaderoRepository {
	boolean insertar(Vehiculo vehiculo, Calendar fechaIngreso) throws Exception;
	boolean eliminar(String placa) throws Exception;
}
