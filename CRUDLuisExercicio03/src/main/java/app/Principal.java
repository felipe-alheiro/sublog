package app;

import entity.Aluno;
import entity.Endereco;
import service.Servico;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Servico s = new Servico();
		s.abrirTransacao();
		s.fecharSessao();
		
		Endereco addr = new Endereco("rua couve do alface 123 casa 4","cuiabá","mato grosso","Brasil");
		Aluno al = new Aluno("0000000000","teste","teste00@gmail.com","999912345",addr);	
		s.inserirAluno(al);
		al = new Aluno("0000000001","teste","teste01@gmail.com","999912346",addr);
		s.inserirAluno(al);
		Aluno al2 = s.buscaAlunoMatricula("0000000001");
		if(al2!=null)
			System.out.println("Lido: \n"+al2.toString());
		
	}

}
