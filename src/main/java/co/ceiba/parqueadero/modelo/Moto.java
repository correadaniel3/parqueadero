package co.ceiba.parqueadero.modelo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("1")
public class Moto extends Vehiculo {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="cilindraje")
	@NotNull
	private int cilindraje;

	public Moto(String placa, int cilindraje) {
		super(placa);
		this.cilindraje=cilindraje;
	}
	
	public Moto() {
	}
	
	public int getCilindraje() {
		return cilindraje;
	}
	
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

}
