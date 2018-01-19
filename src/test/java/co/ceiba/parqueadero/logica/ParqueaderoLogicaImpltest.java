package co.ceiba.parqueadero.logica;


import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.exception.ParqueaderoLogicaException;
import co.ceiba.parqueadero.exception.VehiculoException;
import co.ceiba.parqueadero.logica.ParqueaderoLogica;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoLogicaImpltest {

	@Mock
	ParqueaderoLogica parqueaderoLogica;
	
	@Autowired
	ParqueaderoLogica parqLogica;
	
	Calendar salida;
	Calendar entrada;
	Parqueadero parq, parq2;
	Vehiculo veh, veh2;
	
	@Before
	public void inicializacion() {
		entrada=Calendar.getInstance();
		salida=Calendar.getInstance();
		veh=new Vehiculo("XYZ105");
		veh.setTipo("2");
		parq=new Parqueadero(veh,entrada);
		veh2=new Moto("XYZ105",250);
		veh2.setTipo("1");
		parq2=new Parqueadero(veh2,entrada);	
		
	}
	
	@Test
	public void testIngresarVehiculo() throws ParqueaderoLogicaException {
		boolean resultado= true;
		
		Mockito.when(parqueaderoLogica.ingresarVehiculo("ACB123", 0)).thenReturn(resultado);
		
		Assert.assertEquals(resultado, parqueaderoLogica.ingresarVehiculo("ACB123", 0));
	}
	
	
	@Test
	public void testCantidadHoras() {
		salida.add(Calendar.HOUR_OF_DAY, 11);
		Assert.assertEquals(11,parqLogica.cantidadHoras(entrada, salida));
	}
	@Test
	public void testCantidadMinutos() {
		salida.add(Calendar.MINUTE, 2);
		Assert.assertEquals(2,parqLogica.cantidadMinutos(entrada, salida));
	}
	
	@Test
	public void testCalcularMontoMasDeUnDia() throws VehiculoException{
		salida.add(Calendar.DATE,1);
		salida.add(Calendar.HOUR_OF_DAY,3);
		parq.setFechaSalida(salida);
		double monto=parqLogica.calcularMonto(parq);
		Assert.assertEquals(11000,monto,0f);
	}
	
	@Test
	public void testCalcularMontoMenosDeUnDia() throws VehiculoException{
		salida.add(Calendar.HOUR,11);
		parq.setFechaSalida(salida);
		double monto=parqLogica.calcularMonto(parq);
		Assert.assertEquals(8000,monto,0f);
	}
	
	@Test
	public void testCalcularMontoMenosDeNueveHoras() throws VehiculoException{
		salida.add(Calendar.HOUR_OF_DAY,5);
		parq.setFechaSalida(salida);
		double monto=parqLogica.calcularMonto(parq);
		Assert.assertEquals(5000,monto,0f);
	}
	@Test
	public void testCalcularMontoMoto() throws VehiculoException{
		salida.add(Calendar.HOUR_OF_DAY,5);
		parq2.setFechaSalida(salida);
		double respuesta=2500;
		
		Mockito.when(parqueaderoLogica.calcularMonto(parq2)).thenReturn(2500.0);
		
		Assert.assertEquals(respuesta,parqueaderoLogica.calcularMonto(parq2),0f);
	}
	@Test
	public void testCalcularMontoMotoCilindrajeMayor() throws VehiculoException{
		salida.add(Calendar.HOUR_OF_DAY,5);
		veh2=new Moto("XYZ105",600);
		veh2.setTipo("1");
		parq2=new Parqueadero(veh2,entrada);	
		parq2.setFechaSalida(salida);
		double respuesta=4500;
		
		Mockito.when(parqueaderoLogica.calcularMonto(parq2)).thenReturn(4500.0);
		
		Assert.assertEquals(respuesta,parqueaderoLogica.calcularMonto(parq2),0f);
	}

	@Test
	public void testSalidaParqueadero() throws ParqueaderoLogicaException {
		Double resultado=1000.0;
		
		Mockito.when(parqueaderoLogica.salidaParqueadero("XYZ105")).thenReturn(1000.0);
		
		Assert.assertEquals(resultado,parqueaderoLogica.salidaParqueadero("XYZ105"),0f);
	}

	

}
