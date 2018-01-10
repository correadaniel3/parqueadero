package co.ceiba.parqueadero.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.modelo.Vehiculo;
import co.ceiba.parqueadero.repository.VehiculoRepository;

@Transactional
@Repository
public class VehiculoRepositoryImpl implements VehiculoRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Vehiculo> obtenerTodos() throws Exception {
		try {
			String hql = "FROM Vehiculo";
			return (List<Vehiculo>) entityManager.createQuery(hql).getResultList();
		}catch(Exception e) {
			throw new Exception("Error al obtener todos los vehiculos de la BD", e);
		}
	}

}
