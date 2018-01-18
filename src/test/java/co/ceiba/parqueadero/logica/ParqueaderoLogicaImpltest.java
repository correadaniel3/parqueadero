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

import co.ceiba.parqueadero.exception.ParqueaderoLogicaException;
import co.ceiba.parqueadero.logica.ParqueaderoLogica;
import co.ceiba.parqueadero.modelo.Moto;
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
	
	Vehiculo veh, moto, moto2;
	Parqueadero parq, parqMoto, parqMoto2;
	Calendar entrada;
	Calendar salida;
	
	@Before
	public void inicializacion() {
		veh=new Vehiculo("XYZ105");
		moto=new Moto("FOD223",200);
		moto2=new Moto("FOD223",550);
		moto.setTipo("1");
		moto2.setTipo("1");
		veh.setTipo("2");
		parq=new Parqueadero(veh,Calendar.getInstance());
		parqMoto=new Parqueadero(moto,Calendar.getInstance());
		parqMoto2=new Parqueadero(moto2,Calendar.getInstance());
		
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
	public void test21CantidadMinutos() {
		salida.add(Calendar.MINUTE, 2);
		Assert.assertEquals(2,parqueaderoLogica.cantidadMinutos(entrada, salida));
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
	public void test33CalcularMonto(){
		salida.add(Calendar.HOUR_OF_DAY,5);
		parqMoto.setFechaSalida(salida);
		double monto=parqueaderoLogica.calcularMonto(parqMoto);
		Assert.assertEquals(2500,monto,0f);
	}
	@Test
	public void test34CalcularMonto(){
		salida.add(Calendar.HOUR_OF_DAY,5);
		parqMoto2.setFechaSalida(salida);
		double monto=parqueaderoLogica.calcularMonto(parqMoto2);
		Assert.assertEquals(4500,monto,0f);
	}

	@Test
	public void test4SalidaParqueadero() {
		try {
			Assert.assertEquals(1000,parqueaderoLogica.salidaParqueadero("XYZ105"),0f);
		} catch (ParqueaderoLogicaException e) {
			e.printStackTrace();
		}
	}

	

}
