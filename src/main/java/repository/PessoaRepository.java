package repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import entity.Cliente;
import entity.Funcionario;

public class PessoaRepository implements Serializable {	
	private static final long serialVersionUID = 4479083206522329096L;
	private static final Logger logger = LogManager.getLogger(PessoaRepository.class);
	private EntityManager em;
	private EntityTransaction transaction;
	
	private EntityManagerFactory emf;
	private static final String PERSISTENCE_UNIT = "ex04luis";
	public PessoaRepository() {
		super();
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		setEntityManager();
	}

	public void setEntityManager() {
		this.em = emf.createEntityManager();
		openTransaction();
	}
	
	public EntityManager getEntityManager() {
		if(this.em==null) {
			setEntityManager();
		}
		if(!transaction.isActive())
			openTransaction();		
		return this.em;
	}
	
	private void openTransaction() {
		transaction = this.em.getTransaction();
		transaction.begin();
	}
	
	public void manterCliente(Cliente cliente) {
		try {

			this.getEntityManager().merge(cliente);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			//caso autocommit = false
			transaction.rollback();
			logger.error("Error ao executar o persist: " + e.getMessage());
		}
	}

	public Cliente buscaCliente(long id_buscado) {
		return this.getEntityManager().find(Cliente.class, id_buscado);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listarClientes() {
		return this.getEntityManager().createQuery("SELECT a FROM Cliente a").getResultList();
	}

	public void apagarCliente(long ClienteId) {
		//this.em.remove(cliente);
		this.getEntityManager().createQuery("DELETE FROM Cliente a where a.id = :ClienteID").setParameter("ClienteID", ClienteId).executeUpdate();
	}

	public void manterFuncionario(Funcionario funcionario) {
		try {

			this.getEntityManager().merge(funcionario);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			//caso autocommit = false
			transaction.rollback();
			logger.error("Error ao executar o persist: " + e.getMessage());
		}
	}

	public Funcionario buscaFuncionario(long id_buscado) {
		return this.getEntityManager().find(Funcionario.class, id_buscado);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listarFuncionarios() {
		return this.getEntityManager().createQuery("SELECT a FROM Funcionario a").getResultList();
	}

	public void apagarFuncionario(long FuncionarioId) {
		//this.em.remove(funcionario);
		this.getEntityManager().createQuery("DELETE FROM Funcionario a where a.id = :FuncionarioID").setParameter("FuncionarioID", FuncionarioId).executeUpdate();
	}

}
