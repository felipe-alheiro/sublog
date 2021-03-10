package teste_01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class CRUDDAO {	
	//Statement stmt;
	//PreparedStatement pstmt;
	
	public void inserirClienteBD(Cliente c) throws SQLException{
		DAOteste crud_modulo = new DAOteste();
		Connection conexao=null;
				
		try {
			conexao = crud_modulo.criarConexao();
			if(conexao==null) {
				return;
			}
			String queryInsercao = "insert into Cliente(nome,cpf,email) values (?,?,?)";
			PreparedStatement pstmt = conexao.prepareStatement(queryInsercao);
			pstmt.setString(1, c.getNome());
			pstmt.setString(2, c.getCpf());
			pstmt.setString(3, c.getEmail());
			if(pstmt.executeUpdate()!=0) {
				System.out.println("Inserção bem sucedida!");
			}else {
				System.out.println("Não houve modificação!");
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally {
			crud_modulo.encerrarConexao(conexao);			
		}
	}
	
	public void inserirEnderecoBD(Endereco addr) throws SQLException{
		DAOteste crud_modulo = new DAOteste();
		Connection conexao=null;
		try {
			conexao = crud_modulo.criarConexao();
			if(conexao==null) {
				return;
			}
			String queryInsercao = "insert into Endereco(logradouro,endereco,complemento,bairro,cidade,estado,pais) values (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conexao.prepareStatement(queryInsercao);
			pstmt.setString(1, addr.getLogradouro());
			pstmt.setString(2, addr.getEndereco());
			pstmt.setString(3, addr.getComplemento());
			pstmt.setString(4, addr.getBairro());
			pstmt.setString(5, addr.getCidade());
			pstmt.setString(6, addr.getEstado());
			pstmt.setString(7, addr.getPais());
			
			if(pstmt.executeUpdate()!=0) {
				System.out.println("Inserção bem sucedida!");
			}else {
				System.out.println("Não houve modificação!");
				return;
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally {
			crud_modulo.encerrarConexao(conexao);
		}
	}
	
	public void deleteClienteBDId(long id_cliente) throws SQLException{
		DAOteste crud_modulo = new DAOteste();
		Connection conexao=null;
				
		try {
			conexao = crud_modulo.criarConexao();
			if(conexao==null) {
				return;
			}
			String queryRemocao = "delete from Cliente where id = ?";
			PreparedStatement pstmt = conexao.prepareStatement(queryRemocao);
			pstmt.setLong(1, id_cliente);
			if(pstmt.executeUpdate()!=0) {
				System.out.println("Remoção bem sucedida!");
			}else {
				System.out.println("Não houve modificação!");
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally {
			crud_modulo.encerrarConexao(conexao);			
		}
	}
	
	public void RemoverEnderecoBDId(long id_endereco) throws SQLException{
		DAOteste crud_modulo = new DAOteste();
		Connection conexao=null;
		try {
			conexao = crud_modulo.criarConexao();
			if(conexao==null) {
				return;
			}
			String queryRemocao = "delete from Endereco where id_endereco = ?";
			PreparedStatement pstmt = conexao.prepareStatement(queryRemocao);
			pstmt.setLong(1, id_endereco);			
			if(pstmt.executeUpdate()!=0) {
				System.out.println("Remoção bem sucedida!");
			}else {
				System.out.println("Não houve modificação!");
				return;
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally {
			crud_modulo.encerrarConexao(conexao);
		}
	}
	
	public Cliente buscarClienteBDId(long idC) throws SQLException{
		DAOteste crud_modulo = new DAOteste();
		Connection conexao=null;
		ResultSet resSet=null;		
		try {
			conexao = crud_modulo.criarConexao();
			if(conexao==null) {
				return null;
			}
			String queryBusca = "select * from Cliente where id=?";
			PreparedStatement pstmt = conexao.prepareStatement(queryBusca);
			pstmt.setLong(1, idC);
			//recuperar resultado
			if(pstmt.execute()) {
				Contato novo = new Contato();
				int i=0;
				resSet = pstmt.getResultSet();
				while(isAfterLast()) {
					i++;
					resSet.getArray(i)
				}
				
				
				
			}else {
				System.out.println("A busca não encontrou resultado!");
			}
				
			
			
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally {
			crud_modulo.encerrarConexao(conexao);			
		}
	}
	
	public void inserirEnderecoBD(Endereco addr) throws SQLException{
		DAOteste crud_modulo = new DAOteste();
		Connection conexao=null;
		try {
			conexao = crud_modulo.criarConexao();
			if(conexao==null) {
				return;
			}
			String queryInsercao = "insert into Endereco(logradouro,endereco,complemento,bairro,cidade,estado,pais) values (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conexao.prepareStatement(queryInsercao);
			pstmt.setString(1, addr.getLogradouro());
			pstmt.setString(2, addr.getEndereco());
			pstmt.setString(3, addr.getComplemento());
			pstmt.setString(4, addr.getBairro());
			pstmt.setString(5, addr.getCidade());
			pstmt.setString(6, addr.getEstado());
			pstmt.setString(7, addr.getPais());
			
			if(pstmt.executeUpdate()!=0) {
				System.out.println("Inserção bem sucedida!");
			}else {
				System.out.println("Não houve modificação!");
				return;
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally {
			crud_modulo.encerrarConexao(conexao);
		}
	}
	
}
