package co.ceiba.parqueadero.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.exception.ParqueaderoServiceException;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.service.ParqueaderoService;

@RestController
public class ParqueaderoController {
	
	@Autowired
	ParqueaderoService parqueaderoService;
	
	@CrossOrigin
	 @RequestMapping(path="/ingresar/placa={placa}&cilindraje={cilindraje}", 
			 method = RequestMethod.GET)
	 public boolean ingresar(@PathVariable String placa,
			 @PathVariable String cilindraje) throws  Exception {
				return parqueaderoService.ingresarVehiculoParqueadero(placa, 
						Integer.parseInt(cilindraje)); 
	 }
	 
	 @CrossOrigin
	 @RequestMapping(path="/salir/placa={placa}", method = RequestMethod.GET)
	 public double salir(@PathVariable String placa) throws Exception {
				return parqueaderoService.salidaVehiculoParqueadero(placa); 
	 }
	 
	 @CrossOrigin
	 @RequestMapping(path="/obtenerVehiculos", method = RequestMethod.GET)
	 public List<Parqueadero> obtenerVehiculos() throws ParqueaderoServiceException {
			try {
				return parqueaderoService.obtenerVehiculos();
			} catch (ParqueaderoException e) {
				throw new ParqueaderoServiceException("No se pudo obtener todos los vehiculos");
			} 
	 }
}
