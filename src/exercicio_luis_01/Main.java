package exercicio_luis_01;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import crud.CRUDdao;
import model.Contato;

public class Main {

	public static void main(String[] args) throws SQLException {
		CRUDdao start = new CRUDdao();
		try {
			/*
			Contato a = new Contato(0, "Carlos Eduardo", "Santos Goes", "10101010123", Date.valueOf(LocalDate.of(2001, 11, 2)), "cadu@gmail.com","999876543");
			start.inserirContato(a);
			a = new Contato(0, "Vitor Hugo", "Santos Goes", "10101010124", Date.valueOf(LocalDate.of(2001, 11, 2)), "vito@gmail.com","999876542");
			start.inserirContato(a);
			a = new Contato(0, "Icaro Augusto", "Santos Goes", "10101010125", Date.valueOf(LocalDate.of(2001, 11, 2)), "ico@gmail.com","999876541");
			start.inserirContato(a);
			*/
			System.out.println(start.listarContatos().toString());
			/*
			a = start.buscaContatoId(11L);
			if(a!=null) {
				System.out.println(a.toString());
			}else {
				System.out.println("Não encontrou o contato!");
			}
			*/
			
			
		}catch(SQLException e){
			e.printStackTrace();
			e.getMessage();
		}
		

	}

}
