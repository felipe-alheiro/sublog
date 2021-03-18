/*
 * Exercício CRUD Luís
 * Autor: Felipe Alheiro
 * Objetivo do projeto : Criar um projeto de CRUD funcional
 * Objetivo: Declarar a classe de persitÊncia do modelo, ou seja, aquela que se comunica com o banco de dados
 * 		também chamada de Data Acess Object (DAO) Class
 * */
package persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOClass {	
	private String url = "jdbc:mysql://localhost/exercicio02_luis?createDatabaseIfNotExist=true";
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String user = "alheiro";
	private String password = "alheiro1qaz@WSX"; 
	
	public DAOClass() {	}
		
	public DAOClass(String driver, String url, String user, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	//inicia a conexão com o banco de dados
	protected Connection getConnection(){
		Connection conn=null;
		try {
			//carrega a classe localizada em parametro driver
			Class.forName(driver);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Não conseguiu carregar a classe do Driver!");
		}
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println("Não conseguiu estabelecer uma conexão!");
		}
		return conn;
	}
	
	//termina a conexão com o banco de dados
	protected void endConnection(Connection conn){
		try {
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println("Não conseguiu fechar a conexão!");
		}		
	}
}
