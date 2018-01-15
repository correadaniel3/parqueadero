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

@Transactional
@Service
public class ParqueaderoServiceImpl implements ParqueaderoService {
	
	@Autowired
	ParqueaderoLogica parqueaderoLogica;
	
	@Autowired
	ParqueaderoRepository parqueaderorepository;
	
	@Override
	public boolean ingresarVehiculoParqueadero(String placa, int cilindraje) throws ParqueaderoServiceException {
		if(placa.isEmpty()) {
			throw new ParqueaderoServiceException("La placa del vehiculo no puede ser vacia");
		}
		if(!placa.matches(Constantes.PATRON_PLACA)) {
			throw new ParqueaderoServiceException("No se ha ingresado una placa valida");
		}
		if(cilindraje <0) {
			throw new ParqueaderoServiceException("El cilindraje no puede ser menor a cero");
		}
		if(Integer.valueOf(cilindraje)==null) {
			throw new ParqueaderoServiceException("El cilindraje no puede ser nulo");
		}
		try {
			return parqueaderoLogica.ingresarVehiculo(placa, cilindraje);
		} catch (ParqueaderoLogicaException e) {
			throw new ParqueaderoServiceException("No fue posible registrar la"
					+ " entrada del vehiculo",e);
		}
	}

	@Override
	public double salidaVehiculoParqueadero(String placa) throws ParqueaderoServiceException {
		if(placa.isEmpty()) {
			throw new ParqueaderoServiceException("La placa del vehiculo no puede ser vacia");
		}
		if(!placa.matches(Constantes.PATRON_PLACA)) {
			throw new ParqueaderoServiceException("No se ha ingresado una placa valida");
		}
		try {
			return parqueaderoLogica.salidaParqueadero(placa);
		} catch (ParqueaderoLogicaException e) {
			throw new ParqueaderoServiceException("No fue posible registrar la"
					+ " salida del vehiculo",e);
		}
	}

	@Override
	public List<Parqueadero> obtenerVehiculos() throws ParqueaderoException {
		return parqueaderorepository.obtenerVehiculos();
	}

}
