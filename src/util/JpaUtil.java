package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	public static EntityManagerFactory emf = null;

	static {
		iniciar();
	}
	
	private static void iniciar() {
		try {
			if (emf == null) {
				emf = Persistence.createEntityManagerFactory("QuickStart");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static EntityManager criarEntityManager() {
		return emf.createEntityManager();
	}

	
}
