package co.ceiba.parqueadero.service;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.exception.ParqueaderoServiceException;
import co.ceiba.parqueadero.repository.ParqueaderoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParqueaderoServiceImplTest {

	@Autowired
	ParqueaderoService parqueaderoService;
	
	@Autowired
	ParqueaderoRepository parqueaderoRepository;
	
	@Test
	public void test1IngresarVehiculoParqueadero() throws ParqueaderoServiceException, ParqueaderoException {
		Assert.assertTrue(parqueaderoService.ingresarVehiculoParqueadero("UVW987", 0));
	}

	@Test
	public void test2SalidaVehiculoParqueadero() throws ParqueaderoServiceException, ParqueaderoException {
		Assert.assertEquals(1000,parqueaderoService.salidaVehiculoParqueadero("UVW987"),0f);
	}
	
	@Test
	public void test3Borrado() throws ParqueaderoException {
		parqueaderoRepository.eliminar("UVW987");
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void ingresarVehiculoPlacaVacia() throws ParqueaderoServiceException {
		Assert.assertTrue(parqueaderoService.ingresarVehiculoParqueadero("", 0));
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void ingresarVehiculoPlacaInvalida() throws ParqueaderoServiceException {
		Assert.assertTrue(parqueaderoService.ingresarVehiculoParqueadero("AX12", 0));
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void ingresarVehiculoCilindrajeInvalido() throws ParqueaderoServiceException {
		Assert.assertTrue(parqueaderoService.ingresarVehiculoParqueadero("BGR123", -12));
	}
	@Test(expected=ParqueaderoServiceException.class)
	public void ingresarVehiculoExistente() throws ParqueaderoServiceException {
		Assert.assertTrue(parqueaderoService.ingresarVehiculoParqueadero("GBE568", 0));
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void salidaVehiculoParqueaderoPlacaVacia() throws ParqueaderoServiceException {
		Assert.assertEquals(1000,parqueaderoService.salidaVehiculoParqueadero(""),0f);
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void salidaVehiculoParqueaderoPlacaInvalida() throws ParqueaderoServiceException {
		Assert.assertEquals(1000,parqueaderoService.salidaVehiculoParqueadero("Az01"),0f);
	}
	
	@Test(expected=ParqueaderoServiceException.class)
	public void salidaVehiculoParqueaderoNoExistente() throws ParqueaderoServiceException {
		Assert.assertEquals(1000,parqueaderoService.salidaVehiculoParqueadero("HJK825"),0f);
	}

}
