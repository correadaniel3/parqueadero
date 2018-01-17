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
			throw new ParqueaderoException("No fue posible agregar el registro al parqueadero"
					+ " en la base de datos",e);
		}
	}

	@Override
	public boolean eliminar(String placa) throws ParqueaderoException {
		try{
			Parqueadero parq = obtenerPorVehiculo(placa);
			entityManager.remove(parq);
			return true;
		}catch(Exception e) {
			throw new ParqueaderoException("No fue posible eliminar el vehiculo del registro del"
					+ " parqueadero en la base de datos",e);
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
			throw new ParqueaderoException("No fue posible obtener el registro del vehiculo en el parqueadero "
					+ " de la base de datos",e);
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
			throw new ParqueaderoException("No fue posible obtener el registro de los"
					+ " vehiculos en el parqueadero de la base de datos",e);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Parqueadero obtenerPorVehiculo(String placa) throws ParqueaderoException {
		try {
			List<Parqueadero> parq = null;
			String hql = "FROM Parqueadero";
			parq = (List<Parqueadero>) entityManager.createQuery(hql).getResultList();
			
			for(Parqueadero parqueadero:parq) {
				if(parqueadero.getVehiculo().getPlaca().equals(placa)) {
					
					return parqueadero;
				}
			}
			return null;
		}catch (Exception e) {
			throw new ParqueaderoException("No fue posible obtener el vehiculo",e);
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
			throw new ParqueaderoException("No fue posible actualizar el registro del vehiculo"
					+ "en el parqueadero", e);
		}
	}
	

}
