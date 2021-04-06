package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco {
	@Id
	@Column(name="id_endereco", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_endereco;
	@Column(name="cep", nullable = false, length = 20)
	private String CEP;
	@Column(name="logradouro", nullable = false, length = 200)
	private String logradouro;
	@Column(name="complemento", nullable = false, length = 100)
	private String complemento;
	@Column(name="bairro", nullable = false, length = 100)
	private String bairro;
	@Column(name="cidade", nullable = false, length = 100)
	private String cidade;
	@Column(name="estado", nullable = false, length = 100)
	private String estado;
	@Column(name="pais", nullable = false, length = 100)
	private String pais;
		
	public Endereco() {
		super();
	}

	public Endereco(String cEP, String logradouro, String complemento, String bairro, String cidade, String estado, String pais) {
		super();
		CEP = cEP;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}

	public Endereco(long id_endereco, String cep, String logradouro, String bairro, String complemento, String cidade, String estado,
			String pais) {
		super();
		this.id_endereco = id_endereco;
		this.CEP = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CEP == null) ? 0 : CEP.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
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
		if (CEP == null) {
			if (other.CEP != null)
				return false;
		} else if (!CEP.equals(other.CEP))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder quickstr = new StringBuilder();
		quickstr.append("\n[Endereco] CEP: ");
		quickstr.append(CEP);
		quickstr.append("\nLogradouro: ");
		quickstr.append(logradouro);
		quickstr.append("\nComplemento: ");
		quickstr.append(complemento);
		quickstr.append("\nCBairro: ");
		quickstr.append(bairro);
		quickstr.append("\nCidade: ");
		quickstr.append(cidade);
		quickstr.append("\nEstado: ");
		quickstr.append(estado);
		quickstr.append("\nPais: ");
		quickstr.append(pais);
		quickstr.append("\n");	
		return quickstr.toString();
	}
	
	public long getId_endereco() {
		return id_endereco;
	}
	public void setId_endereco(long id_endereco) {
		this.id_endereco = id_endereco;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
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
	
	
}
