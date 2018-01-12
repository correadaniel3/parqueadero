package co.ceiba.parqueadero.repository;

import java.util.List;

import co.ceiba.parqueadero.exception.VehiculoException;
import co.ceiba.parqueadero.modelo.Carro;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Vehiculo;

public interface VehiculoRepository {
	List<Vehiculo> obtenerTodos() throws VehiculoException;
	Vehiculo insertar(String placa, int cilindraje) throws VehiculoException;
	boolean eliminar(String placa) throws VehiculoException;
	List<Carro> obtenerCarros() throws VehiculoException;
	List<Moto> obtenerMotos() throws VehiculoException;
	Vehiculo obtenerPorPlaca(String placa) throws VehiculoException;

}
