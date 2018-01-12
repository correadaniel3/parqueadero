package co.ceiba.parqueadero.service;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.exception.ParqueaderoServiceException;

public interface ParqueaderoService {
	boolean ingresarVehiculoParqueadero(String placa, int cilindraje) throws ParqueaderoServiceException, ParqueaderoException;
	double salidaVehiculoParqueadero(String placa) throws ParqueaderoServiceException, ParqueaderoException;
}
