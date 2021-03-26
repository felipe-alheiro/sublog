package repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import entity.Cliente;
import entity.Funcionario;
import util.HibernateUtil;

public class PessoaRepository implements Serializable {
	private static final Logger logger = LogManager.getLogger(PessoaRepository.class);
	private static final long serialVersionUID = 4479083206522329096L;
	private EntityManager em;
	private EntityTransaction transaction;

	public PessoaRepository() {}

	public void setEntityManager(EntityManager em) {
		this.em = em;
		openTransaction();
	}
	public EntityManager getEmEntityManager() {
		if(this.em==null) {
			this.em = HibernateUtil.getEntitymanager();
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

			this.getEmEntityManager().merge(cliente);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			//caso autocommit = false
			transaction.rollback();
			logger.error("Error ao executar o persist: " + e.getMessage());
		}
	}

	public Cliente buscaCliente(long id_buscado) {
		return this.getEmEntityManager().find(Cliente.class, id_buscado);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listarClientes() {
		return this.getEmEntityManager().createQuery("SELECT a FROM Cliente a").getResultList();
	}

	public void apagarCliente(long ClienteId) {
		//this.em.remove(cliente);
		this.getEmEntityManager().createQuery("DELETE FROM Cliente a where a.id = :ClienteID").setParameter("ClienteID", ClienteId).executeUpdate();
	}

	public void manterFuncionario(Funcionario funcionario) {
		try {

			this.getEmEntityManager().merge(funcionario);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			//caso autocommit = false
			transaction.rollback();
			logger.error("Error ao executar o persist: " + e.getMessage());
		}
	}

	public Funcionario buscaFuncionario(long id_buscado) {
		return this.getEmEntityManager().find(Funcionario.class, id_buscado);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listarFuncionarios() {
		return this.getEmEntityManager().createQuery("SELECT a FROM Funcionario a").getResultList();
	}

	public void apagarFuncionario(long FuncionarioId) {
		//this.em.remove(funcionario);
		this.getEmEntityManager().createQuery("DELETE FROM Funcionario a where a.id = :FuncionarioID").setParameter("FuncionarioID", FuncionarioId).executeUpdate();
	}

}
