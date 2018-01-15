package co.ceiba.parqueadero.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorColumn(name="vehiculo_tipo")
@Table(name="vehiculo")
public class Vehiculo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="placa")
	private String placa;
	
	public Vehiculo(String placa) {
		super();
		this.placa=placa;
	}
	
	@Column(name="vehiculo_tipo",insertable = false, updatable = false)
	private String tipo;
	
	public Vehiculo() {
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
