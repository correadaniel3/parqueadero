package co.ceiba.parqueadero.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.exception.ParqueaderoLogicaException;
import co.ceiba.parqueadero.exception.ParqueaderoServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoServiceImplTest {

	@Autowired
	ParqueaderoService parqueaderoService;
	
	@Mock
	private ParqueaderoService parqService;
	
	
	@Test
	public void testIngresarVehiculoParqueadero() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		boolean resultado=true;
		
		Mockito.when(parqService.ingresarVehiculoParqueadero("UVW987", 0)).thenReturn(resultado);
		
		Assert.assertTrue(parqService.ingresarVehiculoParqueadero("UVW987", 0));
	}

	@Test
	public void testSalidaVehiculoParqueadero() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		double resultado=1000.0;
		
		Mockito.when(parqService.salidaVehiculoParqueadero("UVW987")).thenReturn(resultado);
		
		Assert.assertEquals(1000,parqService.salidaVehiculoParqueadero("UVW987"),0f);
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
	public void ingresarVehiculoExistente() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		
		Mockito.when(parqService.ingresarVehiculoParqueadero("GBE568",0)).thenThrow(new ParqueaderoServiceException());
		
		Assert.assertTrue(parqService.ingresarVehiculoParqueadero("GBE568", 0));
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void salidaVehiculoParqueaderoPlacaVacia() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		Assert.assertEquals(1000,parqueaderoService.salidaVehiculoParqueadero(""),0f);
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void salidaVehiculoParqueaderoPlacaInvalida() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		Assert.assertEquals(1000,parqueaderoService.salidaVehiculoParqueadero("Az01"),0f);
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void salidaVehiculoParqueaderoNoExistente() throws ParqueaderoServiceException, ParqueaderoLogicaException {
		
		Mockito.when(parqService.salidaVehiculoParqueadero("GBE568")).thenThrow(new ParqueaderoServiceException());

		Assert.assertEquals(1000,parqService.salidaVehiculoParqueadero("GBE568"),0f);
	}

}
