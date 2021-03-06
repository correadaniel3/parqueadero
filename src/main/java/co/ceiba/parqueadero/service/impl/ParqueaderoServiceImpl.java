package co.ceiba.parqueadero.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.exception.ParqueaderoLogicaException;
import co.ceiba.parqueadero.exception.ParqueaderoServiceException;
import co.ceiba.parqueadero.logica.ParqueaderoLogica;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.repository.ParqueaderoRepository;
import co.ceiba.parqueadero.service.ParqueaderoService;
import co.ceiba.parqueadero.utils.Constantes;
import co.ceiba.parqueadero.utils.Mensajes;

/**
 * Se implementan los metodos que se conectaran con el controlador para exponer 
 * los servicios REST
 * @author daniel.correa
 *
 */
@Transactional
@Service
public class ParqueaderoServiceImpl implements ParqueaderoService {
	
	@Autowired
	ParqueaderoLogica parqueaderoLogica;
	
	@Autowired
	ParqueaderoRepository parqueaderorepository;
	
	/*
	 * 
	 * @see co.ceiba.parqueadero.service.ParqueaderoService#ingresarVehiculoParqueadero(java.lang.String, int)
	 */
	@Override
	public boolean ingresarVehiculoParqueadero(String placa, int cilindraje) throws ParqueaderoServiceException {
		if(placa.isEmpty()) {
			throw new ParqueaderoServiceException(Mensajes.PLACA_VACIA);
		}
		if(!placa.matches(Constantes.PATRON_PLACA)) {
			throw new ParqueaderoServiceException(Mensajes.PLACA_INVALIDA);
		}
		if(cilindraje <0) {
			throw new ParqueaderoServiceException(Mensajes.CILINDRAJE_INVALIDO);
		}
		try {
			return parqueaderoLogica.ingresarVehiculo(placa, cilindraje);
		} catch (ParqueaderoLogicaException e) {
			throw new ParqueaderoServiceException(e.getCause().toString().split(":")[1]);
		}
	}

	/*
	 * 
	 * @see co.ceiba.parqueadero.service.ParqueaderoService#salidaVehiculoParqueadero(java.lang.String)
	 */
	@Override
	public double salidaVehiculoParqueadero(String placa) throws ParqueaderoServiceException {
		if(placa.isEmpty()) {
			throw new ParqueaderoServiceException(Mensajes.PLACA_VACIA);
		}
		if(!placa.matches(Constantes.PATRON_PLACA)) {
			throw new ParqueaderoServiceException(Mensajes.PLACA_INVALIDA);
		}
		try {
			return parqueaderoLogica.salidaParqueadero(placa);
		} catch (ParqueaderoLogicaException e) {
			throw new ParqueaderoServiceException(Mensajes.ERROR_SALIDA_VEHICULO,e);
		}
}

	/*
	 * 
	 * @see co.ceiba.parqueadero.service.ParqueaderoService#obtenerVehiculos()
	 */
	@Override
	public List<Parqueadero> obtenerVehiculos() throws ParqueaderoException {
		return parqueaderorepository.obtenerVehiculos();
	}

}
