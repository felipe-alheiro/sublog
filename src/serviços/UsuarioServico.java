package serviços;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import crud.CRUDdao;
import model.Usuario;

public class UsuarioServico implements Serializable {
	private static final long serialVersionUID = 1L;
	private CRUDdao dao;

	public UsuarioServico() {
		dao = new CRUDdao();
	}

	public boolean salvar(Usuario usuario) {
		try {
			dao.inserirUsuario(usuario);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de salvar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public boolean atualizar(Usuario usuario) {
		try {
			dao.atualizarUsuario(usuario);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de atualizar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public boolean apagar(Long id) {
		try {
			dao.removerUsuario(id);
			return Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de apagar(). Error: " + e.getMessage());
		}
		return Boolean.FALSE;
	}
	
	public Usuario buscaPorId(Long id) {
		try {
			return dao.buscaUsuarioId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de buscaPorId(). Error: " + e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Usuario> lista() {
		try {
			return dao.listarUsuarios();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de lista(). Error: " + e.getMessage());
		}
		return new ArrayList<Usuario>();
	}
	/*
	public boolean login(String usuario, String senha) {
		try {
			return dao.buscaUsuarioId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Ocorreu uma falha ao efetuar o bypass de buscaPorId(). Error: " + e.getMessage());
		}
		return null;
	}
	*/
}
