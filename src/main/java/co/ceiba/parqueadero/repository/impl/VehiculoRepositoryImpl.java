package co.ceiba.parqueadero.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.modelo.Carro;
import co.ceiba.parqueadero.modelo.Moto;
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

	@Override
	public boolean insertar(String placa, int cilindraje) throws Exception {
		Vehiculo vehiculo;
		try {
			if(cilindraje==0) {
				vehiculo=new Carro(placa.toUpperCase());
			}else {
				vehiculo=new Moto(placa.toUpperCase(),cilindraje);
			}
			entityManager.persist(vehiculo);
			return true;
		}catch(Exception e) {
			throw new Exception("No fue posible agregar el vehiculo en la base de datos",e);
		}
	}

	@Override
	public boolean eliminar(String placa) throws Exception {
		try{
			Vehiculo vehiculo = null;
			String hql = "FROM Vehiculo as veh WHERE veh.placa = ?";
			vehiculo = (Vehiculo) entityManager.createQuery(hql).setParameter(1, placa).getSingleResult();
			entityManager.remove(vehiculo);
			return true;
		}catch(Exception e) {
			throw new Exception("No fue posible eliminar el vehiculo de la base de datos",e);
		}
	}


}
