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
	public Funcionario(String matricula) {
		super();
		this.matricula = matricula;
	}
	@Override
	public String toString() {
		return "[Funcionario]\nMatricula: " + matricula + "\nDep\rtamento: " + matricula + super.toString();
	}
	public long getId_func() {
		return id_func;
	}
	public void setId_func(long id_func) {
		this.id_func = id_func;
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
	public Funcionario(long id_func, String matricula, String departamento, long id_pessoa) {
		super();
		this.id_func = id_func;
		this.matricula = matricula;
		this.departamento = departamento;
		this.id_pessoa = id_pessoa;
	}
	public Funcionario(String matricula, String departamento, long id_pessoa) {
		super();
		this.matricula = matricula;
		this.departamento = departamento;
		this.id_pessoa = id_pessoa;
	}
	public Funcionario() {
		super();
	}
	
}
