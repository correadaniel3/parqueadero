package co.ceiba.parqueadero.logica;

import java.util.Calendar;

import co.ceiba.parqueadero.exception.ParqueaderoLogicaException;
import co.ceiba.parqueadero.modelo.Parqueadero;

public interface ParqueaderoLogica {
	double calcularMonto(Parqueadero parqueadero);
	double salidaParqueadero(String placa) throws ParqueaderoLogicaException;
	boolean ingresarVehiculo(String placa, int cilindraje) throws ParqueaderoLogicaException;
	long cantidadHoras(Calendar ingreso, Calendar salida);
}
