package co.ceiba.parqueadero.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.exception.VehiculoException;
import co.ceiba.parqueadero.modelo.Carro;
import co.ceiba.parqueadero.modelo.Moto;
import co.ceiba.parqueadero.modelo.Vehiculo;
import co.ceiba.parqueadero.repository.VehiculoRepository;
import co.ceiba.parqueadero.utils.Mensajes;

@Transactional
@Repository
public class VehiculoRepositoryImpl implements VehiculoRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Vehiculo> obtenerTodos() throws VehiculoException {
		try {
			String hql = "FROM Vehiculo";
			return (List<Vehiculo>) entityManager.createQuery(hql).getResultList();
		}catch(Exception e) {
			throw new VehiculoException(Mensajes.ERROR_TODOS_LOS_VEHICULOS, e);
		}
	}

	@Override
	public Vehiculo insertar(String placa, int cilindraje) throws VehiculoException {
		Vehiculo vehiculo;
		try {
			if(cilindraje==0) {
				vehiculo=new Carro(placa.toUpperCase()); 
			}else {
				vehiculo=new Moto(placa.toUpperCase(),cilindraje); 
			}
			entityManager.persist(vehiculo);
			return vehiculo;
		}catch(Exception e) {
			throw new VehiculoException(Mensajes.ERROR_INSERTAR,e); 
		}
	}

	@Override
	public boolean eliminar(String placa) throws VehiculoException {
		try{
			Vehiculo vehiculo = null;
			String hql = "FROM Vehiculo as veh WHERE veh.placa = ?";
			vehiculo = (Vehiculo) entityManager.createQuery(hql).setParameter(1, placa).getSingleResult();
			entityManager.remove(vehiculo); 
			return true;
		}catch(Exception e) {
			throw new VehiculoException(Mensajes.ERROR_ELIMINAR,e);
		}
	}

	

	@Override
	public Vehiculo obtenerPorPlaca(String placa) throws VehiculoException {
		try{
			List<Vehiculo> vehiculos=obtenerTodos();
			for(Vehiculo veh: vehiculos) {
				if(veh.getPlaca().equals(placa)) {
					return veh;
				}
			}
			return null;
		}catch(Exception e) {
			throw new VehiculoException(Mensajes.ERROR_OBTENER_VEHICULO,e);
		}
	}
	
	@Override
	public Moto obtenerMotoPorPlaca(String placa) throws VehiculoException {
		try {
			String hql = "FROM Moto moto WHERE moto.placa = ?";
			return (Moto) entityManager.createQuery(hql).setParameter(1, placa).getResultList().get(0);
		}catch(Exception e) {
			throw new VehiculoException(Mensajes.ERROR_OBTENER_MOTO, e);
		}
	}
}
		

