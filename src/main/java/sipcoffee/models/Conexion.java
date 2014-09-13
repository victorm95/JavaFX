package sipcoffee.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion {

	public static final String PERSISTENCE_UNIT_DEV = "dev";

	public static EntityManagerFactory factory;
	public static EntityManager manager;

	// Constructor privado para aplicar patron Singleton
	private Conexion() {
	}

	public static void init() {
		if (factory == null) {
			factory = Persistence
					.createEntityManagerFactory(Conexion.PERSISTENCE_UNIT_DEV);
		}
		if (manager == null) {
			manager = factory.createEntityManager();
		}
	}

	public static boolean persist(Object obj) {
		try {
			init();
			manager.getTransaction().begin();
			manager.persist(obj);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Error Persist: " + e.toString());
			return false;
		}
	}

	public static List<Object> namedQuery(String queryName) {
		init();
		return manager.createNamedQuery(queryName).getResultList();
	}

	public static boolean delete(Object obj) {
		try {
			init();
			manager.getTransaction().begin();
			manager.remove(obj);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
