package co.ceiba.parqueadero.repository.impl;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.exception.ParqueaderoException;
import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;
import co.ceiba.parqueadero.repository.ParqueaderoRepository;
import co.ceiba.parqueadero.repository.VehiculoRepository;
import co.ceiba.parqueadero.utils.Mensajes;

@Transactional
@Repository
public class ParqueaderoRepositoryImpl implements ParqueaderoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Override
	public boolean insertar(Vehiculo vehiculo, Calendar fechaIngreso) throws ParqueaderoException {
		Parqueadero parq;
		try {
			parq=new Parqueadero(vehiculo, fechaIngreso);
			entityManager.persist(parq);
			return true;
		}catch(Exception e) {
			throw new ParqueaderoException(Mensajes.ERROR_REGISTRO,e);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Parqueadero obtenerPorVehiculoSinSalir(String placa) throws ParqueaderoException {
		try {
			List<Parqueadero> parq = null;
			Vehiculo test=new Vehiculo(placa); 
			String hql = "FROM Parqueadero as parq WHERE parq.vehiculo = ? AND parq.fechaSalida is null";
			parq = (List<Parqueadero>) entityManager.createQuery(hql).setParameter(1, test)
					.getResultList();
			if(parq.isEmpty()) {return null;}
			return parq.get(0);
		}catch (Exception e) {
			throw new ParqueaderoException(Mensajes.ERROR_OBTENER_SIN_SALIR,e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Parqueadero> obtenerVehiculos() throws ParqueaderoException {
		try {
			List<Parqueadero> parq = null;
			String hql = "FROM Parqueadero as parq WHERE parq.fechaSalida is null";
			parq = (List<Parqueadero>) entityManager.createQuery(hql).getResultList();
			return parq;
		}catch (Exception e) {
			throw new ParqueaderoException(Mensajes.ERROR_OBTENER_TODOS_SIN_SALIR,e);
		}
	}

	@Override
	public Parqueadero actualizar(String placa, Calendar fechaSalida) throws ParqueaderoException {
		try {
			Parqueadero parq= obtenerPorVehiculoSinSalir(placa);
			parq.setFechaSalida(fechaSalida);
			entityManager.flush();
			return parq;
		} catch (Exception e) {
			throw new ParqueaderoException(Mensajes.ERROR_ACTUALIZAR_VEHICULO, e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Parqueadero> obtenerCarros() throws ParqueaderoException {
		try {
			String hql = "SELECT parq FROM Parqueadero as parq INNER JOIN parq.vehiculo as carro WHERE parq.fechaSalida is null AND carro.tipo=2";
			return (List<Parqueadero>) entityManager.createQuery(hql).getResultList();
		}catch(Exception e) {
			throw new ParqueaderoException(Mensajes.ERROR_OBTENER_CARROS, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parqueadero> obtenerMotos() throws ParqueaderoException {
		try {
			String hql = "SELECT parq FROM Parqueadero as parq INNER JOIN parq.vehiculo as moto WHERE parq.fechaSalida is null AND moto.tipo=1";
			return (List<Parqueadero>) entityManager.createQuery(hql).getResultList();
		}catch(Exception e) {
			throw new ParqueaderoException(Mensajes.ERROR_OBTENER_MOTOS, e);
		}
	}
	

}
