package teste_01;

import java.util.ArrayList;

public class Endereco {
	long id_endereco;
	private String logradouro;
	private String endereco;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	ArrayList<?> id_cliente_residente;
	
	public Endereco() {
		
	}
	
	public Endereco(long id_endereco, String logradouro, String endereco, String complemento, String bairro, String cidade, String estado,
			String pais) {
		this.logradouro = logradouro;
		this.id_endereco = id_endereco;
		this.endereco = endereco;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}
	//getters and setters
	
	public long getId_endereco() {
		return id_endereco;
	}
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setId_endereco(long id_endereco) {
		this.id_endereco = id_endereco;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
	public ArrayList<?> getId_cliente_residente() {
		return id_cliente_residente;
	}
	public void setId_cliente_residente(ArrayList<?> id_cliente_residente) {
		this.id_cliente_residente = id_cliente_residente;
	}
	
	
}
