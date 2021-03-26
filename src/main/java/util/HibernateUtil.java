package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class HibernateUtil {
	private static final Logger logger = LogManager.getLogger(HibernateUtil.class);
	private static final EntityManager entityManager = buildEntityManagerFactory();
	
	private static final String PERSISTENCE_UNIT = "ex04luis";
	
	/*
	 * O buildEntityManagerFactory() retorna null para em.
	 * ou Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	 * ou outro erro estão impedindo isto pelo que entendo Luis.
	 * 
	 * Estou tentando fazer variações
	 * tentei criar uma instância da classe HibernateUtil mas os métodos são estáticos então é desnecessário.
	 * 
	 * A persistence unit pode ser uma fonte de erro?
	 * 
	 * 
	 * */
	
	
	private static EntityManager buildEntityManagerFactory() {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			EntityManager em = emf.createEntityManager();
			return em;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error ao gerar o em: " + e.getMessage());
		}
		return null;
	}

	public static EntityManager getEntitymanager() {
		
		return entityManager;
	}
}