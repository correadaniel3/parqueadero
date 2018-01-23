package co.ceiba.parqueadero.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import co.ceiba.parqueadero.exception.ParqueaderoLogicaException;
import co.ceiba.parqueadero.exception.ParqueaderoServiceException;
import co.ceiba.parqueadero.logica.ParqueaderoLogica;
import co.ceiba.parqueadero.repository.ParqueaderoRepository;
import co.ceiba.parqueadero.service.impl.ParqueaderoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ParqueaderoServiceImplTest {

	@InjectMocks
	ParqueaderoServiceImpl parqueaderoService;
	
	@Mock
	ParqueaderoLogica parqueaderoLogica;
	
	@Mock
	ParqueaderoRepository parqueaderorepository;
	
	
	@Test
	public void testIngresarVehiculoParqueadero() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		
		Mockito.when(parqueaderoLogica.ingresarVehiculo(Mockito.anyString(), Mockito.anyInt())).thenReturn(true);
		
		Assert.assertTrue(parqueaderoService.ingresarVehiculoParqueadero("UVW987", 0));
	}

	@Test
	public void testSalidaVehiculoParqueadero() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		double resultado=1000.0;
		
		Mockito.when(parqueaderoLogica.salidaParqueadero(Mockito.anyString())).thenReturn(1000.0);
		
		Assert.assertEquals(resultado,parqueaderoService.salidaVehiculoParqueadero("UVW987"),0f);
	}
	
	
	@Test(expected=ParqueaderoServiceException.class)
	public void ingresarVehiculoPlacaVacia() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		Assert.assertTrue(parqueaderoService.ingresarVehiculoParqueadero("", 0));
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void ingresarVehiculoPlacaInvalida() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		Assert.assertTrue(parqueaderoService.ingresarVehiculoParqueadero("AX12", 0));
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void ingresarVehiculoCilindrajeInvalido() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		Assert.assertTrue(parqueaderoService.ingresarVehiculoParqueadero("BGR123", -12));
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void salidaVehiculoParqueaderoPlacaVacia() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		Assert.assertEquals(1000,parqueaderoService.salidaVehiculoParqueadero(""),0f);
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void salidaVehiculoParqueaderoPlacaInvalida() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		Assert.assertEquals(1000,parqueaderoService.salidaVehiculoParqueadero("Az01"),0f);
	}
	

}
