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
public class Cliente extends Pessoa implements Serializable{
	private static final long serialVersionUID = 8743583764805695998L;
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "id_cliente")
	@Type(type = "long")
	private long id;
	@Column(name = "cadastro", nullable = false, unique = true)
	private String cadastro;
	@Column(name = "id_pessoa", nullable = false, unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	private long id_pessoa;
	
	public Cliente() {
		super();
	}
	
	public Cliente(long id_pessoa, String nome, String cpf, String email, Endereco endereco, String cadastro, long id) {
		super(id_pessoa, nome, cpf, email, endereco);
		this.id = id;
		this.cadastro = cadastro;
		this.id_pessoa = id_pessoa;
	}

	public Cliente(long id_pessoa, String nome, String cpf, String email, Endereco endereco, String cadastro) {
		super(id_pessoa, nome, cpf, email, endereco);
		this.cadastro = cadastro;
		this.id_pessoa = id_pessoa;
	}

	@Override
	public String toString() {
		return "[Cliente]\ncadastro: " + cadastro + super.toString();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCadastro() {
		return cadastro;
	}
	public void setCadastro(String cadastro) {
		this.cadastro = cadastro;
	}
	public long getId_pessoa() {
		return id_pessoa;
	}
	public void setId_pessoa(long id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	
	
}