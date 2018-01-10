package co.ceiba.parqueadero.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Carro extends Vehiculo {

	private static final long serialVersionUID = 1L;

	public Carro(String placa) {
		super(placa);
	}
	
	public Carro() {
		
	}

}
