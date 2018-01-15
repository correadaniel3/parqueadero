package co.ceiba.parqueadero.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public final class Constantes {
	
	public static final int CANTIDAD_MAXIMA_CARROS=20;
	public static final int CANTIDAD_MAXIMA_MOTOS=10;
	public static final double HORA_CARRO=1000;
	public static final double HORA_MOTO=500;
	public static final double DIA_CARRO=8000;
	public static final double DIA_MOTO=4000;
	public static final double CILINDRAJE=500;
	public static final int MINIMO_HORAS_DIA=9;
	public static final String LETRA_PLACA="A";
	public static List<Integer> DIAS_RESTRINGIDOS=new ArrayList<>();
	public static final String PATRON_PLACA="^[A-Z]{3}\\d{3}";
	
	static {
		DIAS_RESTRINGIDOS.add(1);
		DIAS_RESTRINGIDOS.add(2);
	}
	private Constantes() {}
	
	

}