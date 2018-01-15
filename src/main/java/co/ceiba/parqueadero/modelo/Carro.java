package co.ceiba.parqueadero.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("2")
public class Carro extends Vehiculo {

	public Carro(String placa) {
		super(placa);
	}
	
	public Carro() {
		
	}
	
}
