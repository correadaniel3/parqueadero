package co.ceiba.parqueadero.modelo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Generated;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("1")
public class Moto extends Vehiculo {
	
	@Column(name="cilindraje")
	@NotNull
	private int cilindraje;

	public Moto(String placa, int cilindraje) {
		super(placa);
		this.cilindraje=cilindraje;
	}
	
	@Generated
	public Moto() {
	}
	
	@Generated
	public int getCilindraje() {
		return cilindraje;
	}
	
	@Generated
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

}
