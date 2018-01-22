package co.ceiba.parqueadero.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Generated;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("2")
public class Carro extends Vehiculo {

	@Generated
	public Carro(String placa) {
		super(placa);
	}
	
	@Generated
	public Carro() {
		
	}
	
}
