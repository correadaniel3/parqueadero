package co.ceiba.parqueadero.service;
import co.ceiba.parqueadero.exception.ParqueaderoServiceException;

public interface ParqueaderoService {
	boolean ingresarVehiculoParqueadero(String placa, int cilindraje) throws ParqueaderoServiceException;
	double salidaVehiculoParqueadero(String placa) throws ParqueaderoServiceException;
}
