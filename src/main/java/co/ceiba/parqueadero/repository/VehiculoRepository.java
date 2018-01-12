package co.ceiba.parqueadero.repository;

import java.util.List;

import co.ceiba.parqueadero.modelo.Carro;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Vehiculo;

public interface VehiculoRepository {
	List<Vehiculo> obtenerTodos() throws Exception;
	Vehiculo insertar(String placa, int cilindraje) throws Exception;
	boolean eliminar(String placa) throws Exception;
	List<Carro> obtenerCarros() throws Exception;
	List<Moto> obtenerMotos() throws Exception;
	Vehiculo obtenerPorPlaca(String placa) throws Exception;

}
