package services;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import crud.CRUDDao;
import model.Contato;

public class ContatoServico implements Serializable {
	private static final long serialVersionUID = 1L;
	private CRUDDao dao;

	public ContatoServico() {
		dao = new CRUDDao();
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
	
	public Contato buscaPorId(Long id) {
		try {
			return dao.buscaContatoId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de buscaPorId(). Error: " + e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Contato> lista() {
		try {
			return dao.listarContatos();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de lista(). Error: " + e.getMessage());
		}
		return new ArrayList<Contato>();
	}
	
	
	
}
