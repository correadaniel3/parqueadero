package co.ceiba.parqueadero.logica;


import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.exception.ParqueaderoLogicaException;
import co.ceiba.parqueadero.logica.ParqueaderoLogica;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;
import co.ceiba.parqueadero.repository.ParqueaderoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParqueaderoLogicaImpltest {

	@Autowired
	ParqueaderoLogica parqueaderoLogica;
	
	@Autowired
	ParqueaderoRepository parqueaderoRepository;
	
	Vehiculo veh;
	Parqueadero parq;
	Calendar entrada;
	Calendar salida;
	
	@Before
	public void inicializacion() {
		veh=new Vehiculo("XYZ105");
		veh.setTipo("2");
		parq=new Parqueadero(veh,Calendar.getInstance());
		entrada=Calendar.getInstance();
		salida=Calendar.getInstance();
	}
	
	@Test
	public void test1IngresarVehiculo() {
		try {
			Assert.assertTrue(parqueaderoLogica.ingresarVehiculo("XYZ105", 0));
		} catch (ParqueaderoLogicaException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2CantidadHoras() {
		salida.add(Calendar.HOUR_OF_DAY, 11);
		Assert.assertEquals(11,parqueaderoLogica.cantidadHoras(entrada, salida));
	}
	
	@Test
	public void test3CalcularMonto(){
		salida.add(Calendar.DATE,1);
		salida.add(Calendar.HOUR_OF_DAY,3);
		parq.setFechaSalida(salida);
		double monto=parqueaderoLogica.calcularMonto(parq);
		Assert.assertEquals(11000,monto,0f);
	}
	
	@Test
	public void test31CalcularMonto(){
		salida.add(Calendar.HOUR,11);
		parq.setFechaSalida(salida);
		double monto=parqueaderoLogica.calcularMonto(parq);
		Assert.assertEquals(8000,monto,0f);
	}
	
	@Test
	public void test32CalcularMonto(){
		salida.add(Calendar.HOUR_OF_DAY,5);
		parq.setFechaSalida(salida);
		double monto=parqueaderoLogica.calcularMonto(parq);
		Assert.assertEquals(5000,monto,0f);
	}

	@Test
	public void test4SalidaParqueadero() {
		try {
			Assert.assertEquals(8000,parqueaderoLogica.salidaParqueadero("XYZ105"),0f);
		} catch (ParqueaderoLogicaException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void test5Borrado() throws ParqueaderoException {
		parqueaderoRepository.eliminar("XYZ105");
	}

}
