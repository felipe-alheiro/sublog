package exercicio_luis_01;

import java.sql.SQLException;
import java.time.Period;

import crud.CRUDdao;
import model.Contato;

public class Main {

	public static void main(String[] args) throws SQLException {
		CRUDdao start = new CRUDdao();
		try {
			Contato a = new Contato(0, "Carlos Eduardo", "Santos Goes", "10101010123", Period.of(2001, 11, 2), "cadu@gmail.com","999876543");
			start.inserirContato(a);
			a = new Contato(0, "Vitor Hugo", "Santos Goes", "10101010124", Period.of(2001, 11, 2), "vito@gmail.com","999876542");
			start.inserirContato(a);
			a = new Contato(0, "Icaro Augusto", "Santos Goes", "10101010125", Period.of(2001, 11, 2), "ico@gmail.com","999876541");
			start.inserirContato(a);
			
			a = start.buscaContatoId(1);
			if(a!=null) {
				System.out.println(a.toString());
			}else {
				System.out.println("Não encontrou o contato!");
			}
			
			System.out.println(start.listarContatos().toString());
			
		}catch(SQLException e){
			e.printStackTrace();
			e.getMessage();
		}
		

	}

}
