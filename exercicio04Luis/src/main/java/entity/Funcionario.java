package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class Funcionario extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1973974833895952223L;
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "id_funcionario")
	@Type(type = "long")
	private long id;
	@Column(name = "matricula", nullable = false, unique = true)
	private String matricula;
	@Column(name = "departamento", nullable = false)
	private String departamento;
	@Column(name = "id_pessoa", nullable = false, unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	private long id_pessoa;
	
	public Funcionario(long id_pessoa, String nome, String cpf, String email, Endereco endereco,long id_func, String matricula, String departamento) {
		super(id_pessoa,nome, cpf, email,endereco);
		this.id = id_func;
		this.matricula = matricula;
		this.departamento = departamento;
		this.id_pessoa = id_pessoa;
	}
	public Funcionario(long id_pessoa,String nome, String cpf, String email, Endereco endereco,String matricula, String departamento) {
		super(id_pessoa,nome, cpf, email,endereco);
		this.matricula = matricula;
		this.departamento = departamento;
		this.id_pessoa = id_pessoa;
	}
	public Funcionario() {
		super();
	}
	
	@Override
	public String toString() {
		return "[Funcionario]\nMatricula: " + matricula + "\nDepartamento: " + matricula + super.toString();
	}
	public long getId_func() {
		return id;
	}
	public void setId_func(long id) {
		this.id = id;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public long getId_pessoa() {
		return id_pessoa;
	}
	public void setId_pessoa(long id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (id_pessoa ^ (id_pessoa >>> 32));
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id_pessoa != other.id_pessoa)
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	
}
