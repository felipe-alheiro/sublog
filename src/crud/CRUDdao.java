package crud;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.DAOclass;
import model.Contato;
import model.Endereco;
import model.Usuario;

public class CRUDdao extends DAOclass{
	Connection conn = null;
	PreparedStatement pstmt = null;
	//Statement stmt = null;
	String querysql = "";
	
	public CRUDdao() {
		super();
	}
	
	public CRUDdao(String driver,String url,String login,String senha) {
		super(driver,url,login,senha);
	}
	
	//Busca por ID
	public Contato buscaContatoId(long idC) throws SQLException {
		Contato resultado = null;
		ResultSet respostaSQL = null;
		
		conn = super.getConnection();
		if(conn == null) {
			System.err.println("Não consegue criar a conexão!");
			throw new RuntimeException();
		}
			
		
		try {
			querysql = "select * from contato where id_contato = ?";
			pstmt = conn.prepareStatement(querysql);
			pstmt.setLong(1, idC);
			respostaSQL = pstmt.executeQuery();
			if(respostaSQL.next()) {
				resultado = new Contato(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3),respostaSQL.getString(4),(Date)(respostaSQL.getDate(5)), respostaSQL.getString(6), respostaSQL.getString(7));
			}else {
				System.out.println("Tabela está vazia!");
			}
		}catch(SQLException e) {
			System.err.println("Erro ao receber a Query!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}		
		return resultado;
	}
	
	public Endereco buscaEnderecoId(long idE) throws SQLException {
		Endereco resultado = null;
		ResultSet respostaSQL = null;
		
		conn = super.getConnection();
		querysql = "select * from endereco where id_endereco = ?";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setLong(1, idE);
		
		try {
			respostaSQL = pstmt.executeQuery();
			if(respostaSQL.next()) {
				resultado = new Endereco(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3),respostaSQL.getString(4),respostaSQL.getString(5),respostaSQL.getString(6),respostaSQL.getString(7),respostaSQL.getString(8),respostaSQL.getLong(9));
			}else {
				System.out.println("Tabela está vazia!");
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
	public ArrayList<Contato> listarContatos() throws SQLException {
		ArrayList<Contato> resultado = new ArrayList<Contato>();
		Contato tempContato = new Contato();
		ResultSet respostaSQL = null;
		
		conn = super.getConnection();
		querysql = "select * from contato";
		pstmt = conn.prepareStatement(querysql);
		
		try {
			respostaSQL = pstmt.executeQuery();			
		}catch(SQLException e) {
			System.err.println("Erro ao receber a Query!");
			super.endConnection(conn);
			conn = null;
			return null;
		}
		
		while(respostaSQL.next()) {				
			tempContato.preencherContato(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3),respostaSQL.getString(4),(Date)respostaSQL.getDate(5),respostaSQL.getString(6),respostaSQL.getString(7));
			resultado.add(tempContato);
			tempContato.limparContato();
		}
		super.endConnection(conn);
		conn = null;		
		return resultado;
	}
	
	public ArrayList<Endereco> listarEnderecos() throws SQLException {
		ArrayList<Endereco> resultado = new ArrayList<Endereco>();
		Endereco tempEndereco = new Endereco();
		ResultSet respostaSQL = null;
		
		conn = super.getConnection();
		querysql = "select * from endereco";
		pstmt = conn.prepareStatement(querysql);
		try {
			respostaSQL = pstmt.executeQuery();
			while(respostaSQL.next()) {
				tempEndereco.preencherEndereco(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3),respostaSQL.getString(4),respostaSQL.getString(5),respostaSQL.getString(6),respostaSQL.getString(7),respostaSQL.getString(8),respostaSQL.getLong(9));
				resultado.add(tempEndereco);
				tempEndereco.limparEndereco();
			}			
		}catch(SQLException e) {
			System.err.println("Erro ao receber a Query!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}		
		return resultado;
	}
	//Inserção
	public void inserirContato(Contato contato) throws SQLException {
		conn = super.getConnection();
		querysql = "insert into contato(nome,sobrenome,cpf,dt_nascimento,email,telefone) values (?,?,?,?,?,?) ";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setString(1, contato.getNome());
		pstmt.setString(2, contato.getSobrenome());
		pstmt.setString(3, contato.getCpf());
		pstmt.setDate(4, (Date)contato.getDt_nascimento());
		pstmt.setString(5, contato.getEmail());
		pstmt.setString(6, contato.getTelefone());
		try {
			pstmt.executeUpdate();
			System.out.println("Inserção bem sucedida!");
		}catch(SQLException e) {
			System.err.println("Erro na operação de inserção!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}
	}
	
	public void inserirEndereco(Endereco endereco) throws SQLException {
		conn = super.getConnection();		
		querysql = "insert into endereco(tipo_logradouro,logradouro,bairro,cidade ,estado,pais ,id_contato_residente) values (?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setString(1, endereco.getTipo_logradouro());
		pstmt.setString(2, endereco.getLogradouro());
		pstmt.setString(3, endereco.getComplemento());
		pstmt.setString(4, endereco.getBairro());
		pstmt.setString(5, endereco.getCidade());
		pstmt.setString(6, endereco.getEstado());
		pstmt.setString(7,endereco.getPais());
		pstmt.setLong(8, endereco.getId_contato_residente());
		
		try {
			pstmt.executeUpdate();
			System.out.println("Inserção bem sucedida!");
		}catch(SQLException e) {
			System.err.println("Erro na operação de inserção!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}
	}
	//Atualização
	public void atualizarContato(Contato contato) throws SQLException {
		conn = super.getConnection();
		querysql = "update contato set nome=?,sobrenome=?,cpf=?,dt_nascimento=?,email=?, telefone =? where id_contato = ?";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setString(1, contato.getNome());
		pstmt.setString(2, contato.getSobrenome());
		pstmt.setString(3, contato.getCpf());
		pstmt.setDate(4, (Date)contato.getDt_nascimento());
		
		pstmt.setString(5, contato.getEmail());
		pstmt.setString(6, contato.getTelefone());
		pstmt.setLong(7, contato.getId_contato());
		try {
			pstmt.executeUpdate();
			System.out.println("Atualização bem sucedida!");
		}catch(SQLException e) {
			System.err.println("Erro na operação de atualização!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}
	}
	
	public void atualizarEndereco(Endereco endereco) throws SQLException {
		conn = super.getConnection();		
		querysql = "update endereco set tipo_logradouro=?,logradouro=?,bairro=?,cidade=? ,estado=?,pais=? ,id_contato_residente=? where id_endereco = ?";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setString(1, endereco.getTipo_logradouro());
		pstmt.setString(2, endereco.getLogradouro());
		pstmt.setString(3, endereco.getComplemento());
		pstmt.setString(4, endereco.getBairro());
		pstmt.setString(5, endereco.getCidade());
		pstmt.setString(6, endereco.getEstado());
		pstmt.setString(7,endereco.getPais());
		pstmt.setLong(8, endereco.getId_contato_residente());
		pstmt.setLong(9, endereco.getId_endereco());
		try {
			pstmt.executeUpdate();
			System.out.println("Atualização bem sucedida!");
		}catch(SQLException e) {
			System.err.println("Erro na operação de atualização!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}
	}
	//Remoção
	public void removerContato(long idC) throws SQLException {
		conn = super.getConnection();
		querysql = "remove from contato where id_contato = ?";
		pstmt = conn.prepareStatement(querysql);
		
		pstmt.setLong(1, idC);		
		try {
			pstmt.executeUpdate();
			System.out.println("Remoção bem sucedida!");
		}catch(SQLException e) {
			System.err.println("Erro na operação de inserção!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}
	}
	
	public void removerEndereco(long idE) throws SQLException {
		conn = super.getConnection();		
		querysql = "delete from endereco where id_endereco = ?";
		pstmt = conn.prepareStatement(querysql);
	
		pstmt.setLong(1, idE);
		
		try {
			pstmt.executeUpdate();
			System.out.println("Remoção bem sucedida!");
		}catch(SQLException e) {
			System.err.println("Erro na operação de remoção!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}
	}
	
	
	///Busca por ID
	public Usuario buscaUsuarioId(long idU) throws SQLException {
		Usuario resultado = null;
		ResultSet respostaSQL = null;
		
		conn = super.getConnection();
		if(conn == null) {
			System.err.println("Não consegue criar a conexão!");
			throw new RuntimeException();
		}
		querysql = "select * from usuario where id_contato = ?";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setLong(1, idU);
		
		try {
			respostaSQL = pstmt.executeQuery();
			if(respostaSQL.next()) {
				resultado = new Usuario(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3));
			}else {
				System.out.println("Tabela está vazia!");
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
	
	
	//Inserção
	public void inserirUsuario(Usuario usuario) throws SQLException {
		conn = super.getConnection();
		querysql = "insert into usuario(usuario,senha) values (?,?) ";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setString(1, usuario.getUsuario());
		pstmt.setString(2, usuario.getSenha());
		
		try {
			pstmt.executeUpdate();
			System.out.println("Inserção bem sucedida!");
		}catch(SQLException e) {
			System.err.println("Erro na operação de inserção!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}
	}
		
	//Atualização
	public void atualizarUsuario(Usuario usuario) throws SQLException {
		conn = super.getConnection();
		querysql = "update contato set usuario=?,senha=? where id_usuario = ?";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setString(1, usuario.getUsuario());
		pstmt.setString(2, usuario.getSenha());
		pstmt.setLong(3, usuario.getId_usuario());
		
		try {
			pstmt.executeUpdate();
			System.out.println("Atualização bem sucedida!");
		}catch(SQLException e) {
			System.err.println("Erro na operação de atualização!");
		}finally {
			super.endConnection(conn);
			conn = null;
		}
	}
	
	//Remoção
	public void removerUsuario(long idU) throws SQLException {
		conn = super.getConnection();
		querysql = "remove from usuario where id_usuario = ?";
		pstmt = conn.prepareStatement(querysql);
		
		pstmt.setLong(1, idU);		
		try {
			pstmt.executeUpdate();
			System.out.println("Remoção bem sucedida!");
		}catch(SQLException e) {
			System.err.println("Erro na operação de inserção!");
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
					System.out.println("A senha não confere!");
					return_bool = Boolean.FALSE;
				}						
			}else {					
				System.out.println("O login não confere!");
				return_bool = Boolean.FALSE;
			}
			System.out.println("Atualização bem sucedida!");				
		}catch(SQLException e) {
			System.err.println("Erro na operação de Login!");
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
			System.err.println("Não consegue criar a conexão!");
			throw new RuntimeException();
		}
		querysql = "select * from usuario where login = ?";
		pstmt = conn.prepareStatement(querysql);
		pstmt.setString(1, login);
		
		try {
			respostaSQL = pstmt.executeQuery();
			if(respostaSQL.next()) {
				resultado = new Usuario(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3));
			}else {
				System.out.println("O usuário não existe!");
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
