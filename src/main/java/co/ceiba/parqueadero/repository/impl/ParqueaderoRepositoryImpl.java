package co.ceiba.parqueadero.repository.impl;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.modelo.Parqueadero;
import co.ceiba.parqueadero.modelo.Vehiculo;
import co.ceiba.parqueadero.repository.ParqueaderoRepository;

@Transactional
@Repository
public class ParqueaderoRepositoryImpl implements ParqueaderoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean insertar(Vehiculo vehiculo, Calendar fechaIngreso) throws Exception {
		Parqueadero parq;
		try {
			parq=new Parqueadero(vehiculo, fechaIngreso);
			entityManager.persist(parq);
			return true;
		}catch(Exception e) {
			throw new Exception("No fue posible agregar el registro al parqueadero"
					+ " en la base de datos",e);
		}
	}

	@Override
	public boolean eliminar(String placa) throws Exception {
		try{
			Parqueadero parq = null;
			String hql = "FROM Parqueadero as parq WHERE parq.vehiculo = ? AND parq.fechaSalida is null";
			parq = (Parqueadero) entityManager.createQuery(hql).setParameter(1, new Vehiculo(placa))
					.getSingleResult();
			entityManager.remove(parq);
			return true;
		}catch(Exception e) {
			throw new Exception("No fue posible eliminar el vehiculo del registro del parqueadero "
					+ " en la base de datos",e);
		}
	}

}
