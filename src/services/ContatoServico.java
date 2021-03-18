package services;
import java.sql.SQLException;
import java.util.ArrayList;

import persist.DAOContato;
import entity.Contato;
import entity.Endereco;
import entity.Telefone;

public class ContatoServico{
	private DAOContato dao;

	public ContatoServico() {
		dao = new DAOContato();
	}

	public boolean salvar(Contato contato) {
		try {
			dao.inserirContato(contato);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de salvar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public boolean atualizar(Contato contato) {
		try {
			dao.atualizarContato(contato);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de atualizar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public boolean apagar(Long id) {
		try {
			dao.removerContato(id);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de apagar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public Contato buscaPorId(long id) {
		try {
			return dao.buscaContatoId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de buscaPorId(). Error: " + e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Contato> listaContatos() {
		try {
			return dao.listarContatos();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de lista(). Error: " + e.getMessage());
		}
		return new ArrayList<Contato>();
	}
	
	public ArrayList<Endereco> listaEnderecos() {
		try {
			return dao.listarEnderecos();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de lista(). Error: " + e.getMessage());
		}
		return new ArrayList<Endereco>();
	}
	
	public ArrayList<Telefone> listaTelefones() {
		try {
			return dao.listarTelefones();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de lista(). Error: " + e.getMessage());
		}
		return new ArrayList<Telefone>();
	}
	
	
	public boolean salvarEndereco(Endereco endereco) {
		try {
			dao.inserirEndereco(endereco);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de salvar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public boolean atualizarEndereco(Endereco endereco) {
		try {
			dao.atualizarEndereco(endereco);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de atualizar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public boolean apagarEndereco(long id) {
		try {
			dao.removerEndereco(id);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de apagar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public Endereco buscaPorIdEndereco(long id) {
		try {
			return dao.buscaEnderecoId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de buscaPorId(). Error: " + e.getMessage());
		}
		return null;
	}
	
	public Endereco buscaPorIdcontatoEndereco(long idC) {
		try {
			return dao.buscaEnderecoIdContato(idC);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de buscaPorId(). Error: " + e.getMessage());
		}
		return null;
	}
	
	public boolean salvarTelefone(Telefone telefone, long idContato) {
		try {
			dao.inserirTelefone(telefone,idContato);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de salvar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public boolean atualizarTelefone(Telefone telefone) {
		try {
			dao.atualizarTelefone(telefone);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de atualizar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public boolean apagarTelefone(long idTelefone) {
		try {
			dao.removerTelefone(idTelefone);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de apagar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public Telefone buscaPorIdTeledone(long idTelefone) {
		try {
			return dao.buscaTelefoneId(idTelefone);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de buscaPorId(). Error: " + e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Telefone> buscaPorIdcontatoTelefone(Long idC) {
		try {
			return dao.buscaTelefonesIdContato(idC);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de buscaPorId(). Error: " + e.getMessage());
		}
		return null;
	}
}
