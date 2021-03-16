package exercicio_luis_01;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import crud.CRUDDao;
import model.Contato;

public class Main {

	public static void main(String[] args) throws SQLException {
		CRUDDao start = new CRUDDao();
		try {
			
			Contato a = new Contato(0, "Carlos Eduardo", "Santos Goes", "10101010123", Date.valueOf(LocalDate.of(2001, 11, 2)), "cadu@gmail.com","999876543");
			start.inserirContato(a);
			a = new Contato(0, "Vitor Hugo", "Santos Goes", "10101010124", Date.valueOf(LocalDate.of(2001, 11, 2)), "vito@gmail.com","999876542");
			start.inserirContato(a);
			a = new Contato(0, "Icaro Augusto", "Santos Goes", "10101010125", Date.valueOf(LocalDate.of(2001, 11, 2)), "ico@gmail.com","999876541");
			start.inserirContato(a);
			start.removerContato(12);
			
			a = new Contato(13, "Maria Eduarda", "Santos", "10101010120", Date.valueOf(LocalDate.of(2001, 11, 2)), "duda@gmail.com","999876547");
			start.atualizarContato(a);
			
			ArrayList<Contato> d = start.listarContatos();
			int i=0;
			for(Contato x : d) {
				System.out.println(""+(i+1)+".)"+x.getNome()+x.getSobrenome()+x.getCpf()+x.getEmail()+x.getTelefone()+x.getDt_nascimento());
				i++;
			}


			
			a = start.buscaContatoId(11L);
			if(a!=null) {
				System.out.println(a.toString());
			}else {
				System.out.println("Não encontrou o contato!");
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
			e.getMessage();
		}
		

	}

}
