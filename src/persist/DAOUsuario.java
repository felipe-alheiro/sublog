/*
 * Exerc�cio CRUD Lu�s
 * Autor: Felipe Alheiro
 * Objetivo do projeto : Criar um projeto de CRUD funcional
 * Objetivo: Declarar a classe de persit�ncia DAO Usu�rio com os m�todos do CRUD que herda DAO Class
 * Classe incompleta
 * */
package persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Usuario;

public class DAOUsuario extends DAOClass{
	Connection conn = null;
	PreparedStatement pstmt = null;
	//Statement stmt = null;
	String querysql = "";
	
	//Construtores
	public DAOUsuario() {
		super();
	}
	
	public DAOUsuario(String driver,String url,String login,String senha) {
		super(driver,url,login,senha);
	}
	
	public void abrirConexao() {
		conn = super.getConnection();//exce��o tratada no m�todo - meu super � redundante
	}
	public void fecharConexao() {
		endConnection(conn);
		conn = null;
	}
	
	///Busca por ID
	public Usuario buscaUsuarioId(long idU) throws SQLException {
		Usuario resultado = null;
		ResultSet respostaSQL = null;
		
		conn = super.getConnection();
		if(conn == null) {
			System.err.println("N�o consegue criar a conex�o!");
			throw new SQLException();
		}
		querysql = "select * from usuario where id_contato = ?";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setLong(1, idU);
		
		try {
			respostaSQL = pstmt.executeQuery();
			if(respostaSQL.next()) {
				resultado = new Usuario(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3));
			}else {
				System.out.println("Tabela est� vazia!");
			}
		}catch(SQLException e) {
			System.err.println("Erro ao receber a Query!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}		
		return resultado;
	}
	
	
	//Listar Elementos
	public ArrayList<Usuario> listarUsuarios() throws SQLException {
		ArrayList<Usuario> resultado = new ArrayList<Usuario>();
		Usuario tempUsuario = new Usuario();
		ResultSet respostaSQL = null;
		
		conn = super.getConnection();
		querysql = "select * from usuario";
		pstmt = conn.prepareStatement(querysql);
		
		try {
			respostaSQL = pstmt.executeQuery();
			while(respostaSQL.next()) {
				tempUsuario.preencherUsuario(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3));
				resultado.add(tempUsuario);
				tempUsuario.limparUsuario();
			}			
		}catch(SQLException e) {
			System.err.println("Erro ao receber a Query!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}		
		return resultado;
	}
	
	
	//Inser��o
	public void inserirUsuario(Usuario usuario) throws SQLException {
		conn = super.getConnection();
		querysql = "insert into usuario(usuario,senha) values (?,?) ";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setString(1, usuario.getUsuario());
		pstmt.setString(2, usuario.getSenha());
		
		try {
			pstmt.executeUpdate();
			System.out.println("Inser��o bem sucedida!");
		}catch(SQLException e) {
			System.err.println("Erro na opera��o de inser��o!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}
	}
		
	//Atualiza��o
	public void atualizarUsuario(Usuario usuario) throws SQLException {
		conn = super.getConnection();
		querysql = "update contato set usuario=?,senha=? where id_usuario = ?";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setString(1, usuario.getUsuario());
		pstmt.setString(2, usuario.getSenha());
		pstmt.setLong(3, usuario.getId_usuario());
		
		try {
			pstmt.executeUpdate();
			System.out.println("Atualiza��o bem sucedida!");
		}catch(SQLException e) {
			System.err.println("Erro na opera��o de atualiza��o!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}
	}
	
	//Remo��o
	public void removerUsuario(long idU) throws SQLException {
		conn = super.getConnection();
		querysql = "remove from usuario where id_usuario = ?";
		pstmt = conn.prepareStatement(querysql);
		
		pstmt.setLong(1, idU);		
		try {
			pstmt.executeUpdate();
			System.out.println("Remo��o bem sucedida!");
		}catch(SQLException e) {
			System.err.println("Erro na opera��o de inser��o!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}
	}
	
	//Login
	public boolean loginUsuario(String login, String senha) throws SQLException {
		ResultSet sqlResult=null;
		conn = super.getConnection();
		querysql = "select * from usuario where login = ?";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setString(1, login);
		pstmt.setString(2, senha);
		boolean return_bool= Boolean.FALSE;
		
		try {
			conn = super.getConnection();				
			querysql = "select * from usuario where login = ?";
			pstmt = conn.prepareStatement(querysql);
			pstmt.setString(1, login);
			sqlResult = pstmt.executeQuery();				
			if(sqlResult.next()) {				
			
				if(senha.compareTo(sqlResult.getString("senha")) == 0) {
					return_bool = Boolean.TRUE;
				}else {
					System.out.println("A senha n�o confere!");
					return_bool = Boolean.FALSE;
				}						
			}else {					
				System.out.println("O login n�o confere!");
				return_bool = Boolean.FALSE;
			}
			System.out.println("Atualiza��o bem sucedida!");				
		}catch(SQLException e) {
			System.err.println("Erro na opera��o de Login!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}
		return return_bool;
	}
	
	//Busca por EMAIL
	public Usuario buscaUsuarioEmail(String login) throws SQLException {
		Usuario resultado = null;
		ResultSet respostaSQL = null;
		
		conn = super.getConnection();
		if(conn == null) {
			System.err.println("N�o consegue criar a conex�o!");
			throw new SQLException();
		}
		querysql = "select * from usuario where login = ?";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setString(1, login);
		
		try {
			respostaSQL = pstmt.executeQuery();
			if(respostaSQL.next()) {
				resultado = new Usuario(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3));
			}else {
				System.out.println("O usu�rio n�o existe!");
			}
		}catch(SQLException e) {
			System.err.println("Erro ao receber a Query!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}		
		return resultado;
	}
}

