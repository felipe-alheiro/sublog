package repositorio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import entidade.Aluno;
import entidade.Endereco;
import persistencia.Persistencia;


public class RepositorioDAO implements Serializable{
	private static final long serialVersionUID = -8223908760902148913L;
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(RepositorioDAO.class);	
	private static Persistencia persist;
	EntityManager em=null;
	
	public void salvarEndereco(Endereco addr) {
		em = persist.getEntityManager();
		em.persist(addr);
		persist.commit();
		persist.closeEntityManager();
	}
	
	public void atualizarEndereco(Endereco addr) {
		salvarEndereco(addr);
	}
	
	public void apagarEndereco(long idEndereco) {
		em = persist.getEntityManager();
		em.createQuery("delete from Endereco s where s.id_endereco = :idEndereco").setParameter("idEndereco", idEndereco).executeUpdate();
		persist.commit();
		persist.closeEntityManager();
	}
	
	public Endereco procurarEnderecoID(long id_addr) {
		Endereco buscado = new Endereco();
		em = persist.getEntityManager();		
		buscado = (Endereco) em.find(Endereco.class, id_addr);
		persist.closeEntityManager();
		return buscado;
	}
	
	@SuppressWarnings("unchecked")
	public List<Endereco> listarEnderecos(){
		List<Endereco> listagem = new ArrayList<>();
		em = persist.getEntityManager();		
		listagem = em.createQuery("select s from Endereco s").getResultList();
		persist.closeEntityManager();		
		return listagem;
	}
	
	public void salvarAluno(Aluno stdnt) {
		em = persist.getEntityManager();
		em.persist(stdnt);
		persist.commit();
		persist.closeEntityManager();
	}
	
	public void atualizarAluno(Aluno stdnt) {
		salvarAluno(stdnt);
	}
	
	public void apagarAluno(long idAluno) {
		em = persist.getEntityManager();
		em.createQuery("delete from Aluno a where a.id = :idAluno").setParameter("idAluno", idAluno).executeUpdate();
		persist.commit();
		persist.closeEntityManager();
	}
	
	public Aluno procurarAlunoID(long id_stdnt) {
		Aluno buscado = new Aluno();
		em = persist.getEntityManager();		
		buscado = (Aluno) em.find(Aluno.class, id_stdnt);
		persist.closeEntityManager();
		return buscado;
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> listarAlunos(){
		List<Aluno> listagem = new ArrayList<>();
		em = persist.getEntityManager();		
		listagem = em.createQuery("select a from Aluno a").getResultList();
		persist.closeEntityManager();		
		return listagem;
	}
}
