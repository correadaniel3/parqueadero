package co.ceiba.parqueadero.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.exception.VehiculoException;
import co.ceiba.parqueadero.modelo.Carro;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoRepositoryImplTest {
	
	@Mock
	private VehiculoRepository vehiculoRepo;
	
	@Test
	public void testObtenerTodos() throws Exception {
		//Arrange
		List<Vehiculo> resultado= new ArrayList<Vehiculo>();
		Vehiculo veh1, veh2;
		veh1=new Vehiculo("ABC123");
		veh2=new Vehiculo("XYZ987");
		resultado.add(veh1); resultado.add(veh2);
		
		//Act
		Mockito.when(vehiculoRepo.obtenerTodos()).thenReturn(resultado);

		//Assert
		Assert.assertEquals(resultado, vehiculoRepo.obtenerTodos());
	}
	
	@Test
	public void testObtenerPorPlaca() throws VehiculoException {
		Vehiculo test=new Vehiculo("SUV123");
		
		Mockito.when(vehiculoRepo.obtenerPorPlaca("SUV123")).thenReturn(test);
		
		Assert.assertEquals(test, vehiculoRepo.obtenerPorPlaca("SUV123"));
	}
	
	@Test
	public void testInsertarCarro() throws Exception{
		Vehiculo resultado=new Vehiculo("SUV123");
		
		Mockito.when(vehiculoRepo.insertar("SUV123",0)).thenReturn(resultado);
		
		Assert.assertEquals(resultado,vehiculoRepo.insertar("SUV123",0));
	}
	
	
	@Test
	public void testInsertarMoto() throws Exception{
		Vehiculo resultado=new Vehiculo("SUV123");
		
		Mockito.when(vehiculoRepo.insertar("SUV123",500)).thenReturn(resultado);
		
		Assert.assertEquals(resultado,vehiculoRepo.insertar("SUV123",500));
	}
	
	@Test
	public void testEliminar() throws Exception{
		boolean resultado=true;
		
		Mockito.when(vehiculoRepo.eliminar("SUV123")).thenReturn(resultado);
		
		Assert.assertTrue(vehiculoRepo.eliminar("SUV123"));
	}
	
	
	@Test
	public void testObtenerCarros()throws Exception{
		List<Carro> resultado= new ArrayList<Carro>();
		Carro carro1, carro2;
		carro1=new Carro("ABC123");
		carro2=new Carro("XYZ987");
		resultado.add(carro1); resultado.add(carro2);
		
		Mockito.when(vehiculoRepo.obtenerCarros()).thenReturn(resultado);

		Assert.assertEquals(resultado, vehiculoRepo.obtenerCarros());
	}
	
	@Test
	public void testObtenerMotos()throws Exception{
		List<Moto> resultado= new ArrayList<Moto>();
		Moto moto1, moto2;
		moto1=new Moto("ABC123",250);
		moto2=new Moto("XYZ987",300);
		resultado.add(moto1); resultado.add(moto2);
		
		Mockito.when(vehiculoRepo.obtenerMotos()).thenReturn(resultado);

		Assert.assertEquals(resultado, vehiculoRepo.obtenerMotos());
	}
	

}
