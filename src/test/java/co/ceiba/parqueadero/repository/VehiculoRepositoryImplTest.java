package co.ceiba.parqueadero.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.modelo.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoRepositoryImplTest {
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Test
	public void testObtenerTodos() throws Exception {
		List<Vehiculo> result;
		result=vehiculoRepository.obtenerTodos();
		Assert.assertTrue(result.size()>0);
	}
	
	@Test
	public void testInsertarCarro() throws Exception{
		Assert.assertTrue(vehiculoRepository.insertar("GBP568", 0));
	}
	
	@Test
	public void testInsertarMoto() throws Exception{
		Assert.assertTrue(vehiculoRepository.insertar("G6E504", 500));
	}

}
