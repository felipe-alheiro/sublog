package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class Persistencia{
	/*Eu tenho que criar Entity Manager através do Factory.
	 * O EntityManagerFactory usa a classe "Persistence" do pacote "javax.persistence" e o método
	 * createEntityManagerFactory() vai criar a factory.  
	 * A API de Entity Manager vai me prover com métodos para as operaçõ~es de persistência
	 * */
	private final static String PERSISTENCE_UNIT = "ex4luiz"; 
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	private EntityManager em=null;	
	private EntityTransaction etrans=null;	
	
	public Persistencia() {
		super();
		openEntityManager();
		getTransaction();
	}
	
	public EntityManager getEntityManager() {
		if(this.em == null) openEntityManager();
		if(!this.em.isOpen()) openEntityManager();
		return this.em;
	}
	
	public void openEntityManager() {
		if(emf==null) emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		this.em = emf.createEntityManager();
		getTransaction();
	}
	
	public void closeEntityManager() {
		this.closeEntityManager();
	}
	
	public void getTransaction() {
		if(this.em == null) openEntityManager();
		if(!this.em.isOpen()) openEntityManager();
		this.etrans = this.em.getTransaction();
	}
	
	public void encerrarPersistencia() {
		if(this.em!=null)this.em.close();
		if(emf!=null) emf.close();
	}
	
	public void commit() {
		if(etrans!=null) {
			if(etrans.isActive()) this.etrans.commit();
		}		
	}
}
