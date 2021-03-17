package entity;

import java.io.Serializable;

public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", senha=" + senha + "]";
	}
	
}
