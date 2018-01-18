package co.ceiba.parqueadero.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.modelo.Carro;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoRepositoryImplTest {
	
	@Mock
	private ParqueaderoRepository parqueaderoRepo;
	
	@Test
	public void testInsertar() throws ParqueaderoException {
		Vehiculo veh=new Vehiculo("ABS123");
		Calendar entrada=Calendar.getInstance();
		boolean resultado=true;
		
		Mockito.when(parqueaderoRepo.insertar(veh, entrada)).thenReturn(resultado);
		
		Assert.assertEquals(resultado, parqueaderoRepo.insertar(veh,entrada));
	}
	
	@Test(expected=TransactionSystemException.class)
	public void testInsertarFallido() throws ParqueaderoException {
		Vehiculo veh=new Vehiculo("");
		Calendar entrada=Calendar.getInstance();
		boolean resultado=true;
		
		Mockito.when(parqueaderoRepo.insertar(veh, entrada)).thenThrow(new TransactionSystemException(""));

		Assert.assertEquals(resultado, parqueaderoRepo.insertar(veh,entrada));
	}
	
	@Test
	public void testObtenerPorVehiculoSinSalir() throws Exception{
		Vehiculo veh=new Vehiculo("ACB105");
		Calendar entrada=Calendar.getInstance();
		Parqueadero resultado=new Parqueadero(veh, entrada);

		
		Mockito.when(parqueaderoRepo.obtenerPorVehiculoSinSalir(veh.getPlaca())).thenReturn(resultado);
		
		Assert.assertEquals(resultado, parqueaderoRepo.obtenerPorVehiculoSinSalir("ACB105"));
	}	
	
	@Test
	public void test4Actualizar() throws ParqueaderoException {
		Vehiculo veh=new Vehiculo("ACB105");
		Calendar entrada=Calendar.getInstance();
		Calendar salida=Calendar.getInstance();
		Parqueadero inicial=new Parqueadero(veh, entrada);
		Parqueadero resultado=inicial;
		resultado.setFechaSalida(salida);
		
		Mockito.when(parqueaderoRepo.actualizar(veh.getPlaca(), salida)).thenReturn(resultado);
		
		Assert.assertEquals(resultado, parqueaderoRepo.actualizar("ACB105",salida));
	}
	
	@Test
	public void testObtenerVehiculos() throws ParqueaderoException {
		List<Parqueadero> resultado=new ArrayList<Parqueadero>();
		Calendar entrada=Calendar.getInstance();
		Vehiculo veh1=new Vehiculo("ABX123");
		Vehiculo veh2=new Vehiculo("VBN567");
		Parqueadero parq1=new Parqueadero(veh1,entrada);
		Parqueadero parq2=new Parqueadero(veh2,entrada);
		resultado.add(parq1); resultado.add(parq2);
		
		Mockito.when(parqueaderoRepo.obtenerVehiculos()).thenReturn(resultado);
		
		Assert.assertEquals(resultado,parqueaderoRepo.obtenerVehiculos());
	}
	
	@Test
	public void testObtenerCarros()throws Exception{
		List<Parqueadero> resultado= new ArrayList<Parqueadero>();
		Calendar entrada=Calendar.getInstance();
		Parqueadero parq1, parq2;
		parq1=new Parqueadero(new Carro("ABC123"),entrada);
		parq2=new  Parqueadero(new Carro("XYZ987"),entrada);
		resultado.add(parq1); resultado.add(parq2);
		
		Mockito.when(parqueaderoRepo.obtenerCarros()).thenReturn(resultado);

		Assert.assertEquals(resultado, parqueaderoRepo.obtenerCarros());
	}
	
	@Test
	public void testObtenerMotos()throws Exception{
		List<Parqueadero> resultado= new ArrayList<Parqueadero>();
		Calendar entrada=Calendar.getInstance();
		Parqueadero parq1, parq2;
		parq1=new Parqueadero(new Moto("ABC123",200),entrada);
		parq2=new  Parqueadero(new Moto("XYZ987",250),entrada);
		resultado.add(parq1); resultado.add(parq2);
		
		Mockito.when(parqueaderoRepo.obtenerMotos()).thenReturn(resultado);

		Assert.assertEquals(resultado, parqueaderoRepo.obtenerMotos());
	}
	
	

}
