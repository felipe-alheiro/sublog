package model;

public class Endereco {
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
	
}
