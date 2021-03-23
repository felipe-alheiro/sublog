package service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Aluno;
import entity.Endereco;

public class Servico {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	private EntityTransaction transaction = null;
	
	public Servico(){}
	
	
	public boolean abrirSessao() {				
		try {
			//define a persistance unit que vamos trabalhar
			emf = Persistence.createEntityManagerFactory("dbsublog");
			// cria a instância / sessão
			em = emf.createEntityManager();
			
		}catch(IllegalStateException e) {
			e.printStackTrace();
			System.err.println("Não consegue abrir a transação.");
			fecharSessao();
			return false;
		}
		return true;
	}
	
	public void fecharSessao() {
		if(em.isOpen())
			em.close();
		if(emf.isOpen())
			emf.close();
	}
	
	public void abrirTransacao() {
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
	
	public boolean inserirAluno(Aluno std) {
		abrirTransacao();
		transaction.begin();
		boolean retorno= Boolean.FALSE;
		
		if(std==null) {
			System.out.println("Parâmetro para aluno está vazio.");
			return Boolean.TRUE;
		}		
		try {
			//persistencia no BD
			em.persist(std);
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
	
	public List<Aluno> listarAlunos() {	
		abrirTransacao();
		transaction.begin();
		List<Aluno> alunos = null;
		try {
			Query query  = em.createNativeQuery("select * from aluno");
			alunos = query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Não conseguiu efetuar a operação!");
		}finally {
			fecharSessao();
		}
		return alunos;
	}
	
	public Aluno buscaAluno(long id_buscado) {	
		abrirTransacao();
		transaction.begin();	
		Aluno aluno = null;
		List<Aluno> alunos = null;
		try {
			Query query  = em.createNativeQuery("select * from aluno where id_aluno = ?");
			query.setParameter(1,id_buscado);
			alunos =  query.getResultList();
			try {
				aluno = alunos.get(1);
			}catch(IndexOutOfBoundsException e) {
				aluno = null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Não conseguiu efetuar a operação!");
		}finally {
			fecharSessao();
		}			
		return aluno;
	}
	
	public Aluno buscaAlunoMatricula(String matricula) {	
		abrirTransacao();
		transaction.begin();	
		Aluno aluno = null;
		List<Aluno> alunos = null;
		try {
			Query query  = em.createNativeQuery("select * from aluno where matricula = ?");
			query.setParameter(1,matricula);
			alunos =  query.getResultList();
			try {
				aluno = alunos.get(1);
			}catch(IndexOutOfBoundsException e) {
				aluno = null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Não conseguiu efetuar a operação!");
		}finally {
			fecharSessao();
		}			
		return aluno;
	}
	
	public List<Endereco> listarEnderecos() {	
		abrirTransacao();
		transaction.begin();
		List<Endereco> enderecos = null;
		try {
			Query query  = em.createNativeQuery("select * from endereco");
			enderecos = query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Não conseguiu efetuar a operação!");
		}finally {
			fecharSessao();
		}		
		return enderecos;
	}
	
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
		try {
			Query query  = em.createNativeQuery("delete from aluno where id_aluno = ?");
			query.setParameter(1,id_buscado);
			query.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Não conseguiu efetuar a operação!");
		}finally {
			fecharSessao();
		}	
	}
	public void apagarEndereco(long id_buscado) {
		abrirTransacao();
		transaction.begin();
		try {
			Query query  = em.createNativeQuery("delete from endereco where id_endereco = ?");
			query.setParameter(1,id_buscado);
			query.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Não conseguiu efetuar a operação!");
		}finally {
			fecharSessao();
		}	
	}
	
	public void atualizarAluno(Aluno aluno) {
		abrirTransacao();
		transaction.begin();
		try {
			Query query  = em.createNativeQuery("update aluno set matricula=?, nome=? , email=?, telefone =? where id_aluno = ?");
			query.setParameter(1,aluno.getNome());
			query.setParameter(1,aluno.getEmail());
			query.setParameter(1,aluno.getEmail());
			query.setParameter(1,aluno.getTelefone());
			atualizarEndereco(aluno.getEndereco());
			query.setParameter(1,aluno.getId());
			query.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("Não conseguiu efetuar a operação!");
		}finally {
			fecharSessao();
		}	
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
