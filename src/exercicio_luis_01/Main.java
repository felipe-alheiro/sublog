package exercicio_luis_01;

import java.sql.SQLException;

import persist.DAOContato;

public class Main {

	public static void main(String[] args) throws SQLException {
		DAOContato dao = new DAOContato();
		//DAOClass daoteste = new DAOClass();
		dao.abrirConexao();
		dao.fecharConexao();
		
		

	}

}
