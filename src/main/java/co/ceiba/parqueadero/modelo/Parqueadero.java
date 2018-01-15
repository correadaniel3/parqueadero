package co.ceiba.parqueadero.modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@SuppressWarnings("serial")
@Entity 
@Table(name="parqueadero")
public class Parqueadero implements Serializable {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="placa")
	@NotNull
	private Vehiculo vehiculo;
	
	@Column(name="fecha_ingreso")
	@NotNull
	private Calendar fechaIngreso;
	
	@Column(name="fecha_salida")
	private Calendar fechaSalida;

	public Parqueadero() {}
	
	public Parqueadero(Vehiculo vehiculo, Calendar fechaIngreso) {
		super();
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
	}

	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}
	
	

}
