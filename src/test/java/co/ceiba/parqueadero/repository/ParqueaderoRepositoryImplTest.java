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

import co.ceiba.parqueadero.exception.ParqueaderoException;
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
		veh=new Vehiculo("ACB105");
		veh.setTipo("2");
		parq=new Parqueadero(veh,Calendar.getInstance());
		entrada=Calendar.getInstance();
		salida=Calendar.getInstance();
	}
	
	@Test
	public void test1Insertar() {
		try {
			Assert.assertTrue(parqueaderoRepository.insertar(veh, entrada));
		} catch (ParqueaderoException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2ObtenerPorVehiculoSinSalir() throws Exception{
		Assert.assertNotNull(parqueaderoRepository.obtenerPorVehiculoSinSalir("ACB105"));
	}
	
	@Test
	public void test3ObtenerPorVehiculo() throws Exception{
		Assert.assertNotNull(parqueaderoRepository.obtenerPorVehiculo("ACB105"));
	}
	
	@Test
	public void test4Actualizar() {
		try {
			Assert.assertTrue(parqueaderoRepository.actualizar(parq));
		} catch (ParqueaderoException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test5ObtenerCantidadVehiculos() throws Exception {
		int[] resultados=parqueaderoRepository.obtenerCantidadVehiculos();
		Assert.assertEquals(4, resultados[0]);
	}
	
	@Test
	public void test6Eliminar() throws Exception {
		Assert.assertTrue(parqueaderoRepository.eliminar("ACB105"));
	}
	
	@Test
	public void testObtenerVehiculos() throws ParqueaderoException {
		Assert.assertNotNull(parqueaderoRepository.obtenerVehiculos());
	}
	
	

}
