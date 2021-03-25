package entity;

import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.ColumnDefault;


@Entity
public class Endereco implements Serializable{	
	private static final long serialVersionUID = -3531801092444405066L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_endereco")
	private long id;
	@Column(nullable = false)
	private String logradouro;
	@Column(nullable = false)
	private String cidade;
	@Column(nullable = false)
	private String bairro;
	@Column(nullable = false)
	private String estado;
	@Column(nullable = false)
	@ColumnDefault("Brasil")
	private String pais;
	
	public Endereco(String logradouro, String cidade, String bairro, String estado, String pais) {
		super();
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.bairro = bairro;
		this.estado = estado;
		this.pais = pais;
	}
	public Endereco(long id, String logradouro, String cidade, String bairro, String estado, String pais) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.bairro = bairro;
		this.estado = estado;
		this.pais = pais;
	}
	public Endereco() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Endereco other = (Endereco) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\n[Endereco]\nLogradouro: ");
		str.append(logradouro);
		str.append(", ");
		str.append(bairro);
		str.append(", ");
		str.append(cidade);
		str.append(",  ");
		str.append(estado);
		str.append(" - ");
		str.append(pais);
		str.append("\n---------------\n");
		return str.toString();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	
	
}
