package co.ceiba.parqueadero.service;
import java.util.List;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.exception.ParqueaderoServiceException;
import co.ceiba.parqueadero.modelo.Parqueadero;

public interface ParqueaderoService {
	boolean ingresarVehiculoParqueadero(String placa, int cilindraje) throws ParqueaderoServiceException;
	double salidaVehiculoParqueadero(String placa) throws ParqueaderoServiceException;
	List<Parqueadero> obtenerVehiculos() throws ParqueaderoServiceException, ParqueaderoException;
}
