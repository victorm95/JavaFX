package sipcoffee.models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion {
	
	public static final String PERSISTENCE_UNIT_DEV = "dev";
	
	public static EntityManagerFactory factory;
	public static EntityManager manager;
	
	//Constructor privado para aplicar patron Singleton
	private Conexion(){}
	private static void init(){
		if(factory == null){
			factory = Persistence.createEntityManagerFactory(Conexion.PERSISTENCE_UNIT_DEV);
		}
		if(manager == null){
			manager = factory.createEntityManager();
		}
	}
	
	public static boolean persist(Object obj){
		try{
			init();
			manager.getTransaction().begin();
			manager.persist(obj);
			manager.getTransaction().commit();
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
