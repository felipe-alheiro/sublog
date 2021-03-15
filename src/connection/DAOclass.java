package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOclass {	
	private String url = "jdbc:mysql://localhost/exercicio01_luis?createDatabaseIfNotExist=true";
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String user = "alheiro";
	private String password = "alheiro1qaz@WSX"; 
	
	public DAOclass() {	}
		
	public DAOclass(String driver, String url, String user, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	//inicia a conexão com o banco de dados
	protected Connection getConnection() throws SQLException{
		Connection conn=null;
		try {
			//carrega a classe localizada em parametro driver
			Class.forName(driver);
		}catch(ClassNotFoundException e1) {
			System.err.println("Não conseguiu carregar a classe do Driver!");
			throw new SQLException();
		}
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException e2){
			System.err.println("Não conseguiu estabelecer uma conexão!");
			throw new SQLException();
		}
		return conn;
	}
	
	//termina a conexão com o banco de dados
	protected void endConnection(Connection conn) throws SQLException{
		try {
			conn.close();
		}catch(SQLException e){
			System.err.println("Não conseguiu fechar a conexão!");
			throw new SQLException();
		}		
	}
}
