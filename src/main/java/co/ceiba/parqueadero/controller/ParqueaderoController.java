package co.ceiba.parqueadero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parqueadero.exception.ParqueaderoServiceException;
import co.ceiba.parqueadero.service.ParqueaderoService;

@RestController
public class ParqueaderoController {
	
	@Autowired
	ParqueaderoService parqueaderoService;
	
	 @RequestMapping(path="/ingresar/placa={placa}&cilindraje={cilindraje}", method = RequestMethod.GET)
	 public boolean ingresar(@PathVariable String placa,
			 @PathVariable String cilindraje) throws ParqueaderoServiceException {
				return parqueaderoService.ingresarVehiculoParqueadero(placa, 
						Integer.parseInt(cilindraje)); 
	 }
	 
	 @RequestMapping(path="/salir/placa={placa}", method = RequestMethod.GET)
	 public double salir(@PathVariable String placa) throws ParqueaderoServiceException {
				return parqueaderoService.salidaVehiculoParqueadero(placa); 
	 }
}
