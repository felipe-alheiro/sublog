package serviços;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import crud.CRUDdao;
import model.Endereco;

public class EnderecoServico implements Serializable {
	private static final long serialVersionUID = 1L;
	private CRUDdao dao;

	public EnderecoServico() {
		dao = new CRUDdao();
	}

	public boolean salvar(Endereco endereco) {
		try {
			dao.inserirEndereco(endereco);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de salvar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public boolean atualizar(Endereco endereco) {
		try {
			dao.atualizarEndereco(endereco);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de atualizar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public boolean apagar(Long id) {
		try {
			dao.removerEndereco(id);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de apagar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public Endereco buscaPorId(Long id) {
		try {
			return dao.buscaEnderecoId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de buscaPorId(). Error: " + e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Endereco> lista() {
		try {
			return dao.listarEnderecos();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de lista(). Error: " + e.getMessage());
		}
		return new ArrayList<Endereco>();
	}
	
	
	
}
