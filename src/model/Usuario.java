package model;

public class Usuario {
	private long id_usuario;
	private String usuario;
	private String senha;
	
	public Usuario() {
		super();
	}
	
	public Usuario(long id_usuario, String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
		this.id_usuario = id_usuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public void preencherUsuario(long id_usuario, String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
		this.id_usuario = id_usuario;
	}
	public void limparUsuario() {
		this.usuario = "";
		this.senha = "";
		this.id_usuario = 0L;
	}
	
}
