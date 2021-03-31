package application;

import java.util.List;

import entity.Cliente;
import entity.Endereco;
import repository.PessoaRepository;
//import util.HibernateUtil;

public class Principal {
	public static void main(String[] args) {
		PessoaRepository pr = new PessoaRepository();
		
		Endereco addr = new Endereco("Estadio do Corinthians","Bras","Sao Paulo","SP","Brasil");
		Cliente cliente = new Cliente("Ronaldo Fenomeno","17117117157","bola@gmail.com",addr,"171");
		
		pr.manterCliente(cliente);
		Cliente testec = pr.buscaCliente(1);
		System.out.println(testec.toString());
		
		cliente.setEmail("damasdoronaldo@gmail.com");
		pr.manterCliente(cliente);
		testec = pr.buscaCliente(1);
		System.out.println(testec.toString());
		
		cliente = new Cliente("Cassio","17117117190","cassio@gmail.com",addr,"172");
		pr.manterCliente(cliente);
		cliente = new Cliente("Lula","17117117666","molusco@gmail.com",addr,"170");
		pr.manterCliente(cliente);
		cliente = new Cliente("Aecio","17166666660","aecio@gmail.com",addr,"173");
		pr.manterCliente(cliente);
		
		List<Cliente> blabla = pr.listarClientes();
		long erase=0;
		for(Cliente lol : blabla) {
			erase = lol.getId();
			System.out.println("\n"+lol.toString());
		}
		
		pr.apagarCliente(erase);
		
		blabla = pr.listarClientes();
		for(Cliente lol : blabla) {
			System.out.println("\n"+lol.toString());
		}
		
		
	}
}
