package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pessoa implements Serializable{
	private static final long serialVersionUID = -3200174883509780578L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_pessoa")
	private long id;
	@Column(nullable = false)
	private String nome;
	@Column(unique = true, nullable = false)
	private String cpf;
	@Column(nullable = false)
	private String email;
	@Column(nullable = true)
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco = new Endereco();
	
	public Pessoa() {
		super();
	}
	public Pessoa(String nome, String cpf, String email, Endereco endereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.endereco = endereco;
	}
	public Pessoa(long id, String nome, String cpf, String email, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.endereco = endereco;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder str =  new StringBuilder();
		str.append("\n[Pessoa]\nNome: ");
		str.append(nome);
		str.append("\nCPF: ");
		str.append(cpf);
		str.append("\nE-MAIL: ");
		str.append(email);
		str.append(endereco.toString());
		str.append("\n=========================");
		return str.toString();
	}
	
	
	
	
	
}
