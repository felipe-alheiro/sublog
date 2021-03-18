package exercicio_luis_01;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Contato;
import entity.Endereco;
import entity.Telefone;
import services.ContatoServico;

public class Main {

	public static void main(String[] args) throws SQLException {
		ContatoServico teste = new ContatoServico();
		teste.iniciarServico();
		Contato a = new Contato();
		a.setCpf("1010101010010");
		a.setDt_nascimento(Date.valueOf("2001-10-14"));
		a.setEmail("fulano@gmail.com");
		a.setNome("Fulano");
		a.setSobrenome("Ciclano");
		a.setEndereco(new Endereco("rua","cardoso chagas","40 casa 7","Rocha","Rio de Janeiro","Rio de Janeiro","Brasil"));
		
		a.setTelefone(new ArrayList<Telefone>());
		a.getTelefone().add(new Telefone("55","21","999999999"));
		a.getTelefone().add(new Telefone("55","21","22266699"));
		a.getTelefone().add(new Telefone("55","21","972266699"));
		
		//teste.apagar(15L);
		teste.salvar(a);
		Endereco procurado = teste.buscaPorIdcontatoEndereco(16L);
		System.out.println(procurado.toString());
		ArrayList<Telefone> proctel = teste.buscaPorIdcontatoTelefone(16L);
		
		System.out.println(procurado.toString());
		
		for(Telefone r : proctel) {
			System.out.println("Tel: "+r.toString());
		}
		System.out.println(procurado.toString());		
		
		teste.encerrarServico();

	}

}
