/*Copiado do exemplo do Luiz*/
package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "alunos") 
@Entity(name = "alunos")
public class Aluno implements Serializable{
	// @Transient - indica que não é persistido o campo
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//auto_increment
	@Column(name = "id_aluno")
	private long id;
	@Column(unique = true, nullable = false) //indica que o atributo será uma coluna do SGBD - as propriedades do campo são postas entre parenteses
	//posso colocar nome, tipo, constraints-restrições e tal
	private String matricula;
	@Column(nullable = false)
	private String nome;
	@Column(unique = true, nullable = false, length = 150)
	private String email;
	@Column(nullable = false)
	private String telefone;
	@Column(nullable = true)
	private Endereco endereco = new Endereco();
	public Aluno(long id, String matricula, String nome, String email, String telefone, Endereco endereco) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	public Aluno(String matricula, String nome, String email, String telefone, Endereco endereco) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	public Aluno() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone
				+ ", endereco=" + endereco + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
	//(alt+shift+s)
		//+o -> construtor
		//+r -> getters and settes
		//+s -> to string
		//+h -> hash code e equal
}
