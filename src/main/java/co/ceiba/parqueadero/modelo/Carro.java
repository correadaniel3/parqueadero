package co.ceiba.parqueadero.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Generated;

/**
 * POJO y mapeo de carro, es un tipo de vehiculo
 * se añade el valor discriminante 2
 * @author daniel.correa
 *
 */
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
