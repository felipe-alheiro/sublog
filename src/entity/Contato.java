/*
 * Exercício CRUD Luís
 * Autor: Felipe Alheiro
 * Objetivo do projeto : Criar um projeto de CRUD funcional
 * Objetivo: Declarar a classe Contato
 * */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Contato implements Serializable {

	private final static long serialVersionUID = 1L;
	private long id_contato;
	private String nome;
	private String sobrenome;
	private String cpf;
	private String email;
	private Date dt_nascimento;
	private ArrayList<Telefone> telefone;
	private Endereco endereco;

	/*
	 * Luís usa a classe StringBuilder. Testar ela noutro método
	 * 
	 * @Override public String toString() { StringBuilder builder = new
	 * StringBuilder(); builder.append("Contato [id="); builder.append(id);
	 * builder.append(", nome="); builder.append(nome); builder.append(", email=");
	 * builder.append(email); builder.append(", telefone=");
	 * builder.append(telefone); builder.append(", endereco=\n\t[");
	 * builder.append("\t\t" + endereco.toString()); builder.append("\t]\n");
	 * builder.append("]"); return builder.toString(); }
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	public Contato(long id_contato, String nome, String sobrenome, String cpf, String email, Date dt_nascimento,
			ArrayList<Telefone> telefone, Endereco endereco) {
		super();
		this.id_contato = id_contato;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.email = email;
		this.dt_nascimento = dt_nascimento;
		this.setTelefone(telefone);
		this.setEndereco(endereco);
	}
	
	public Contato(String nome, String sobrenome, String cpf, String email, Date dt_nascimento) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.email = email;
		this.dt_nascimento = dt_nascimento;
		this.setTelefone(null);
		this.setEndereco(null);
	}
	
	public Contato(long id_contato, String nome, String sobrenome, String cpf, String email, Date dt_nascimento) {
		super();
		this.id_contato = id_contato;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.email = email;
		this.dt_nascimento = dt_nascimento;
		this.setTelefone(null);
		this.setEndereco(null);
	}
	
	public Contato() {
		super();
	}

	public Contato(String nome, String sobrenome, String cpf, String email, Date dt_nascimento,
			ArrayList<Telefone> telefone, Endereco endereco) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.email = email;
		this.dt_nascimento = dt_nascimento;
		this.setTelefone(telefone);
		this.setEndereco(endereco);
	}

	public long getId_contato() {
		return id_contato;
	}	

	public void setId_contato(long id_contato) {
		this.id_contato = id_contato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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

	public Date getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public ArrayList<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(ArrayList<Telefone> telefone) {
		this.telefone = new ArrayList<Telefone>();
		if(telefone != null) {
			this.telefone.addAll(telefone);
		}
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco){
		if(endereco == null) {
			this.endereco = new Endereco();
		}else {
			try {
				this.endereco = endereco.getClone();
			}catch(CloneNotSupportedException e){
				this.endereco = new Endereco();
			}
		}		
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dt_nascimento == null) ? 0 : dt_nascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		Contato other = (Contato) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dt_nascimento == null) {
			if (other.dt_nascimento != null)
				return false;
		} else if (!dt_nascimento.equals(other.dt_nascimento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sobrenome == null) {
			if (other.sobrenome != null)
				return false;
		} else if (!sobrenome.equals(other.sobrenome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Contato [nome=" + nome + ", sobrenome=" + sobrenome + ", cpf=" + cpf + ", email=" + email
				+ ", dt_nascimento=" + dt_nascimento + ", telefone=" + telefone.toString() + ",\n " + endereco.toString() + "]";
	}
//Implementei o método clone
	@Override
	protected Contato clone() throws CloneNotSupportedException {
		Contato clonado = null;
		try {
			clonado = (Contato)super.clone();
		}catch(CloneNotSupportedException e) {
			System.err.println("A classe não suporta a clonagem de objetos");
		}
		return clonado;
	}
}
