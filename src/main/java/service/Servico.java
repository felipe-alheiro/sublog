package service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import app.Principal;
import entity.Aluno;
import entity.Endereco;

public class Servico {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	private EntityTransaction transaction = null;
	private static final Logger logger = LogManager.getLogger(Principal.class);
	public Servico(){}
	
	
	private boolean abrirSessao() {				
		try {
			//define a persistance unit que vamos trabalhar
			emf = Persistence.createEntityManagerFactory("dbsublog");
			// cria a instância / sessão
			em = emf.createEntityManager();
			logger.info("Transação aberta com sucesso.");
		}catch(IllegalStateException e) {
			e.printStackTrace();
			System.err.println(String.format("Não consegue abrir a transação. Error: %s",e.getMessage()));
			logger.error(String.format("Não consegue abrir a transação. Error: %s",e.getMessage()));
			fecharSessao();
			return false;
		}
		return true;
	}
	
	private void fecharSessao() {
		if(em.isOpen())
			em.close();
		if(emf.isOpen())
			emf.close();
	}
	
	private void abrirTransacao() {
		if(emf == null) {
			if(!abrirSessao())
				return;
		}else {
			if(!emf.isOpen()) {
				if(!abrirSessao())
					return;
			}else {
				if(!em.isOpen()) {
					em = emf.createEntityManager();
				}
			}
		}			
		transaction = em.getTransaction();
	}
	
	public boolean inserirAluno(String nome,String email,String matricula, String telefone, Endereco addr) {
		abrirTransacao();
		transaction.begin();
		Aluno aluno = new Aluno();
		boolean retorno= Boolean.FALSE;
		
		aluno.setNome(nome);
		aluno.setEmail(email);
		aluno.setMatricula(matricula);
		aluno.setTelefone(telefone);
		if( addr!=null) {
			inserirEndereco(addr.getLogradouro(),addr.getCidade(),addr.getEstado(),addr.getPais());
		}
		
		try {
			//persistencia no BD
			em.persist(aluno);
			//persistÊncia da transação
			transaction.commit();
			retorno = Boolean.TRUE;
		}catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
			System.err.println("Não conseguiu efetuar a operação!");
		}finally {
			fecharSessao();
		}
		return retorno;
	}
	
	public void manterAluno(Aluno std) {
		abrirTransacao();
		transaction.begin();				
		//persistencia no BD
		em.persist(std);		
		/* sem auto commit tem que colocar
		 * transaction.commit(); 
		 * */
		fecharSessao();
	}
	
	public boolean inserirEndereco(String logradouro,String cidade,String estado, String pais) {
		abrirTransacao();
		transaction.begin();
		Endereco endereco = new Endereco();
		boolean retorno=false;
		
		endereco.setLogradouro(logradouro);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		endereco.setPais(pais);
		//persistencia no BD
		
		//persistÊncia da transação
		try {
			em.persist(endereco);
			transaction.commit();
			retorno = true;
		}catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
			System.err.println("Não conseguiu efetuar a operação!");
		}finally {
			fecharSessao();
		}
		return retorno;
	}
	
	public boolean inserirEndereco(Endereco addr) {
		abrirTransacao();
		transaction.begin();
		boolean retorno=false;
		
		if(addr == null) {
			System.out.println("Parâmetro endereço está vazio.");
			return Boolean.TRUE;
		}
		//persistencia no BD
		
		//persistÊncia da transação
		try {
			em.persist(addr);
			transaction.commit();
			retorno = true;
		}catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
			System.err.println("Não conseguiu efetuar a operação!");
		}finally {
			fecharSessao();
		}
		return retorno;
	}
	@SuppressWarnings("unchecked")
	public List<Aluno> listarAlunos() {	
		abrirTransacao();
		transaction.begin();
		Query query  = em.createNativeQuery("select * from aluno");
		fecharSessao();
		return query.getResultList();
	}
	
	public Aluno buscaAluno(long id_buscado) {	
		abrirSessao();
		return em.find(Aluno.class,id_buscado);
	}
	
	public Aluno buscaAlunoMatricula(String matricula) {	
		abrirTransacao();
		transaction.begin();		
		Query query  = em.createNativeQuery("select * from aluno where matricula = ?");
		query.setParameter(1,matricula);
		return (Aluno) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Endereco> listarEnderecos() {	
		abrirTransacao();
		transaction.begin();
		Query query  = em.createNativeQuery("select * from endereco");	
		fecharSessao();		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Endereco buscaEndereco(long id_buscado) {	
		abrirTransacao();
		transaction.begin();
		List<Endereco> enderecos = null;
		Endereco endereco = null;
		try {
			Query query  = em.createNativeQuery("select * from endereco where id_endereco = ?");
			query.setParameter(1,id_buscado);
			enderecos = query.getResultList();
			try {
				endereco = enderecos.get(1);
			}catch(IndexOutOfBoundsException e) {
				endereco = null;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Não conseguiu efetuar a operação!");
		}finally {
			fecharSessao();
		}		
		return endereco;
	}
	
	
	public void apagarAluno(long id_buscado) {
		abrirTransacao();
		transaction.begin();
		Query query  = em.createNativeQuery("delete from aluno where id_aluno = ?");
		query.setParameter(1,id_buscado);
		query.executeUpdate();
		fecharSessao();	
	}
	public void apagarEndereco(Endereco addr) {
		abrirTransacao();
		em.remove(addr);
		//redundância - fecharSessao();
	}
	
	public void atualizarAluno(Aluno aluno) {
		abrirTransacao();
		transaction.begin();
		Query query  = em.createNativeQuery("update aluno set matricula=?, nome=? , email=?, telefone =? where id_aluno = ?");
		query.setParameter(1,aluno.getNome());
		query.setParameter(1,aluno.getEmail());
		query.setParameter(1,aluno.getEmail());
		query.setParameter(1,aluno.getTelefone());
		atualizarEndereco(aluno.getEndereco());
		query.setParameter(1,aluno.getId());
		query.executeUpdate();
		fecharSessao();	
	}
	public void atualizarEndereco(Endereco endereco) {
		abrirTransacao();
		transaction.begin();
		try {
			Query query  = em.createNativeQuery("update endereco set logradouro =?, cidade=?,estado=?,pais=? where id_endereco = ?");
			query.setParameter(1,endereco.getLogradouro());
			query.setParameter(1,endereco.getCidade());
			query.setParameter(1,endereco.getEstado());
			query.setParameter(1,endereco.getPais());
			query.setParameter(1,endereco.getId());
			query.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Não conseguiu efetuar a operação!");
		}finally {
			fecharSessao();
		}	
	}
	
}
