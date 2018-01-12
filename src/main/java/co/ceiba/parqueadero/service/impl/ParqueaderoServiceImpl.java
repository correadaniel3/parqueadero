package co.ceiba.parqueadero.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.exception.ParqueaderoServiceException;
import co.ceiba.parqueadero.repository.ParqueaderoRepository;
import co.ceiba.parqueadero.service.ParqueaderoService;

@Transactional
@Service
public class ParqueaderoServiceImpl implements ParqueaderoService {
	
	@Autowired
	ParqueaderoRepository parqueaderoRepository;
	
	public String patronPlaca="^[A-Z]{3}\\d{3}";

	@Override
	public boolean ingresarVehiculoParqueadero(String placa, int cilindraje) throws ParqueaderoServiceException, ParqueaderoException {
		if(placa.isEmpty()) {
			throw new ParqueaderoServiceException("La placa del vehiculo no puede ser vacia");
		}
		if(!placa.matches(patronPlaca)) {
			throw new ParqueaderoServiceException("No se ha ingresado una placa valida");
		}
		if(cilindraje <0) {
			throw new ParqueaderoServiceException("El cilindraje no puede ser menor a cero");
		}
		if(Integer.valueOf(cilindraje)==null) {
			throw new ParqueaderoServiceException("El cilindraje no puede ser nulo");
		}
		return parqueaderoRepository.ingresarVehiculo(placa, cilindraje);
	}

	@Override
	public double salidaVehiculoParqueadero(String placa) throws ParqueaderoServiceException, ParqueaderoException {
		if(placa.isEmpty()) {
			throw new ParqueaderoServiceException("La placa del vehiculo no puede ser vacia");
		}
		if(!placa.matches(patronPlaca)) {
			throw new ParqueaderoServiceException("No se ha ingresado una placa valida");
		}
		return parqueaderoRepository.salidaParqueadero(placa);
	}

}
