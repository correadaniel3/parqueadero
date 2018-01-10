package co.ceiba.parqueadero.repository;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.modelo.Carro;
import co.ceiba.parqueadero.modelo.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParqueaderoRepositoryImplTest {

	@Autowired
	ParqueaderoRepository parqueaderoRepository;

	@Test
	public void test1Insertar() throws Exception {
		Vehiculo test= new Carro("ACB105");
		Calendar date= Calendar.getInstance();
		Assert.assertTrue(parqueaderoRepository.insertar(test, date));
	}
	
	@Test
	public void test2Eliminar() throws Exception {
		Assert.assertTrue(parqueaderoRepository.eliminar("ACB105"));
	}

}
