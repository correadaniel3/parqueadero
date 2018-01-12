package co.ceiba.parqueadero.repository;

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

import co.ceiba.parqueadero.modelo.Carro;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParqueaderoRepositoryImplTest {

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
	public void test8ObtenerPorVehiculoSinSalir() throws Exception{
		Assert.assertNotNull(parqueaderoRepository.obtenerPorVehiculoSinSalir("XYZ105"));
	}
	
	@Test
	public void test3CantidadHoras() throws Exception{
		salida.add(Calendar.HOUR_OF_DAY, 11);
		Assert.assertEquals(11,parqueaderoRepository.cantidadHoras(entrada, salida));
	}
	
	@Test
	public void test4CalcularMonto(){
		salida.add(Calendar.DATE,1);
		salida.add(Calendar.HOUR_OF_DAY,3);
		parq.setFechaSalida(salida);
		double monto=parqueaderoRepository.calcularMonto(parq);
		Assert.assertEquals(11000,monto,0f);
	}
	
	@Test
	public void test41CalcularMonto(){
		salida.add(Calendar.HOUR,11);
		parq.setFechaSalida(salida);
		double monto=parqueaderoRepository.calcularMonto(parq);
		Assert.assertEquals(8000,monto,0f);
	}
	
	@Test
	public void test42CalcularMonto(){
		salida.add(Calendar.HOUR_OF_DAY,5);
		parq.setFechaSalida(salida);
		double monto=parqueaderoRepository.calcularMonto(parq);
		Assert.assertEquals(5000,monto,0f);
	}
	
	
	public void test5Eliminar() throws Exception {
		Assert.assertTrue(parqueaderoRepository.eliminar("ACB105"));
	}
	
	@Test
	public void test6ObtenerCantidadVehiculos() throws Exception {
		int[] resultados=parqueaderoRepository.obtenerCantidadVehiculos();
		Assert.assertEquals(2, resultados[0]);
	}
	
	@Test
	public void test7IngresarVehiculo() throws Exception {
		Assert.assertTrue(parqueaderoRepository.ingresarVehiculo("XYZ105", 0));
	}
	
	@Test
	public void test8SalidaParqueadero() throws Exception {
		Assert.assertEquals(8000,parqueaderoRepository.salidaParqueadero("XYZ105"),0f);
	}
	
	@Test
	public void test9Eliminar() throws Exception {
		Assert.assertTrue(parqueaderoRepository.eliminar("XYZ105"));
	}
	
	
	

}
