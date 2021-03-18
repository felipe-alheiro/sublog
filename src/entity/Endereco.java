/*
 * Exercício CRUD Luís
 * Autor: Felipe Alheiro
 * Objetivo do projeto : Criar um projeto de CRUD funcional
 * Objetivo: Declarar a classe Endereço
 * */

package entity;

import java.io.Serializable;

public class Endereco implements Serializable,Cloneable{
	private static final long serialVersionUID = 1L;
	private long id_endereco;
	private String tipo_logradouro;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private long id_contato_residente;
	
	public Endereco() {
		super();
	}
	public Endereco(long id_endereco, String tipo_logradouro, String logradouro, String complemento, String bairro, String cidade,
			String estado, String pais, long id_contato_residente) {
		super();
		this.id_endereco = id_endereco;
		this.tipo_logradouro = tipo_logradouro;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.id_contato_residente = id_contato_residente;
	}
	
	public Endereco(String tipo_logradouro, String logradouro, String complemento, String bairro, String cidade,
			String estado, String pais, long id_contato_residente) {
		super();
		this.tipo_logradouro = tipo_logradouro;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.id_contato_residente = id_contato_residente;
	}
	
	public Endereco(String tipo_logradouro, String logradouro, String complemento, String bairro, String cidade,
			String estado, String pais) {
		super();
		this.tipo_logradouro = tipo_logradouro;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getTipo_logradouro() {
		return tipo_logradouro;
	}
	public void setTipo_logradouro(String tipo_logradouro) {
		this.tipo_logradouro = tipo_logradouro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
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
	public long getId_endereco() {
		return id_endereco;
	}
	public void setId_endereco(long id_endereco) {
		this.id_endereco = id_endereco;
	}
	public long getId_contato_residente() {
		return id_contato_residente;
	}
	public void setId_contato_residente(long id_contato_residente) {
		this.id_contato_residente = id_contato_residente;
	}
	
	public void limparEndereco() {
		this.id_endereco = 0l;
		this.tipo_logradouro = "";
		this.logradouro = "";
		this.complemento = "";
		this.bairro = "";
		this.cidade = "";
		this.estado = "";
		this.pais = "";
		this.id_contato_residente = 0l;
	}
	
	public void preencherEndereco(long id_endereco, String tipo_logradouro, String logradouro, String complemento, String bairro, String cidade,
			String estado, String pais, long id_contato_residente) {
		this.id_endereco = id_endereco;
		this.tipo_logradouro = tipo_logradouro;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.id_contato_residente = id_contato_residente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + (int) (id_contato_residente ^ (id_contato_residente >>> 32));
		result = prime * result + (int) (id_endereco ^ (id_endereco >>> 32));
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((tipo_logradouro == null) ? 0 : tipo_logradouro.hashCode());
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
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
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
		if (id_contato_residente != other.id_contato_residente)
			return false;
		if (id_endereco != other.id_endereco)
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
		if (tipo_logradouro == null) {
			if (other.tipo_logradouro != null)
				return false;
		} else if (!tipo_logradouro.equals(other.tipo_logradouro))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Endereco [tipo_logradouro=" + tipo_logradouro + ", logradouro=" + logradouro + ", complemento="
				+ complemento + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", pais=" + pais
				+ "]";
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	public Endereco getClone() throws CloneNotSupportedException{
		Endereco retorno = null;
		try{
			retorno = (Endereco)super.clone();
		}catch (CloneNotSupportedException e) {
			System.err.println("A classe não suporta a clonagem de objetos");
		}
		return retorno;
	}
}
