package co.ceiba.parqueadero.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.modelo.Carro;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VehiculoRepositoryImplTest {
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Test
	public void test1ObtenerTodos() throws Exception {
		List<Vehiculo> result;
		result=vehiculoRepository.obtenerTodos();
		Assert.assertTrue(result.size()>0);
	}
	
	@Test
	public void test2InsertarCarro() throws Exception{
		Vehiculo veh= vehiculoRepository.insertar("GBP568", 0);
		Assert.assertNotEquals(null, veh);
	}
	
	@Test
	public void test21ObtenerPorPlaca() throws Exception{
		Assert.assertNotNull(vehiculoRepository.obtenerPorPlaca("ACB105"));
	}
	
	@Test
	public void test3InsertarMoto() throws Exception{
		Vehiculo veh= vehiculoRepository.insertar("G6E504", 500);
		Assert.assertNotEquals(null, veh);
	}
	
	@Test
	public void test4EliminarMoto() throws Exception{
		Assert.assertTrue(vehiculoRepository.eliminar("G6E504"));
	}
	
	@Test
	public void test5EliminarCarro() throws Exception{
		Assert.assertTrue(vehiculoRepository.eliminar("GBP568"));
	}
	
	@Test
	public void test6ObtenerCarros()throws Exception{
		List<Carro> result= vehiculoRepository.obtenerCarros();
		Assert.assertTrue(result.size()>0);
	}
	
	@Test
	public void test7ObtenerMotos()throws Exception{
		List<Moto> result= vehiculoRepository.obtenerMotos();
		Assert.assertTrue(result.size()>0);
	}
	

}
