package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aluno {
	@Column(name = "id_aluno", unique = true,nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id_aluno;
	@Column(name = "nome", length=200, nullable = false)
	private String nome;
	@Column(name = "email", length=200, nullable = false)
	private String email;
	@Column(name = "CPF", length=15, nullable = false)
	private String cpf;
	@Column(name = "idade", nullable = false, columnDefinition = "int")
	private int idade;
	@Column(name = "matricula", length=20, nullable = false)
	private String matricula;
	@Column(name = "endereco", nullable = false)
	private Endereco endereco = new Endereco();
	
	public Aluno(String nome,String email, String cpf, int idade, String matricula, Endereco endereco) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.idade = idade;
		this.matricula = matricula;
		this.endereco = endereco;
	}
	public Aluno() {
		super();
	}
	public Aluno(long id_aluno, String nome, String email, String cpf, int idade, String matricula, Endereco endereco) {
		super();
		this.id_aluno = id_aluno;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.idade = idade;
		this.matricula = matricula;
		this.endereco = endereco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder quickstr = new StringBuilder("");
		quickstr.append("\n[Aluno]\nNome: ");
		quickstr.append(nome);
		quickstr.append("\nIdade: ");
		quickstr.append(idade);
		quickstr.append("\nEMAIL: ");
		quickstr.append(email);
		quickstr.append("\nCPF: ");
		quickstr.append(cpf);
		quickstr.append("\nMatrícula: ");
		quickstr.append(matricula);
		quickstr.append(endereco.toString());
		return quickstr.toString();
	}
	public long getId_aluno() {
		return id_aluno;
	}
	public void setId_aluno(long id_aluno) {
		this.id_aluno = id_aluno;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
}
