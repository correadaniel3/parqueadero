package co.ceiba.parqueadero.repository;

import java.util.List;

import co.ceiba.parqueadero.modelo.Vehiculo;

public interface VehiculoRepository {
	List<Vehiculo> obtenerTodos() throws Exception;
}
