package sipcoffee.models;

import javax.persistence.*;

/**
 * Created by victorm
 * */

public class Connection {

	/* Contants */
	public static final String DEV_UNIT = "development";
	public static final String TEST_UNIT = "test";

	/* Attrs */
	private static String persistenceUnit;
	private static EntityManager entityManager;

	/* Constructs */
	private Connection() { }

	/* Init attrs */
	private static void init() {
		if(Connection.entityManager == null){
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(Connection.persistenceUnit);
			Connection.entityManager = factory.createEntityManager();
		}
	}


	/* Setter & Getters */

	public static void setPersistenceUnit(String persistenceUnit) {
		Connection.persistenceUnit = persistenceUnit;
	}
	public static String getPersistenceUnit() { return Connection.persistenceUnit; }

	public static EntityManager getInstance() {
		Connection.init();
		return Connection.entityManager;
	}

	public static EntityManager getInstance(String persistenceUnit) {
		Connection.persistenceUnit = persistenceUnit;
		Connection.init();
		return Connection.entityManager;
	}

}
