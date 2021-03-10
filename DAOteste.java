package teste_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

public class DAOteste {
	private Connection conexaobd = null;
	final private String driver = "com.mysql.jdbc.Driver";
	final private String urlBd = "jdbc:mysql://localhost/teste01";
	final private String password = "sublog";
	final private String usuario = "sublog";
	
	public DAOteste(){}
		
	public Connection criarConexao() throws SQLException {
		//etapa 01 - carregar o driver para a classe DriverManager
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e0) {
			// TODO Auto-generated catch block
			e0.printStackTrace();
			System.out.println("Erro! Não consegue importar o driver!");
			return null;
		}		
		//etapa 02 - iniciar a conexão com o SGBD
		try {			
			conexaobd = DriverManager.getConnection(urlBd,usuario,password);
			System.out.println("Conexão estabelecida");
			
		}catch(SQLTimeoutException e2) {
			System.out.println("Tempo esgotado.\nNão conseguiu estabelecer a conexão com o banco de dados em tempo.");
		}catch(SQLException e1) {
			System.out.println("Erro. Não conseguiu estabelecer a conexão com o banco de dados.");
		}
		return conexaobd;		
	}
	
	public void encerrarConexao(Connection con) throws SQLException {
		if(con == null) {
			System.out.println("\nNão há objeto.");
			return;
		}		
		try {
			con.close();
		}catch(SQLException e){
			System.out.println("\nErro de SQL. Não consegue encerrar a sessão!");
		}		
	}
}
