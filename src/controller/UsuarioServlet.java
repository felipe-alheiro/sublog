package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Usuario;
import services.UsuarioServico;

@WebServlet({"/adicionarusuario", "/removerusuario", "/listarusuario", "/buscarusuario", "/atualizarusuarios", "/loginusuario"})
public class UsuarioServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UsuarioServico usuarioServico;
	
	public UsuarioServlet() {
		usuarioServico = new UsuarioServico();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/listarusuarios")) {
			listaDeUsuarios(request, response);
		} else if (request.getServletPath().equals("/buscarusuario")) {
			localizarUsuario(request, response);
		} else if (request.getServletPath().equals("/removerusuario")) {
			excluirUsuario(request, response);
		} else {
			response.sendRedirect("error.jsp?status=404");
		}
	}

	// atende o método HTTP POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/adicionarusuario")) {
			gravarUsuario(request, response);
		} else if (request.getServletPath().equals("/editarusuario")) {
			editarUsuario(request, response);
		} else if (request.getServletPath().equals("/loginusuario")) {
			loginUsuario(request, response);
		} else {
			response.sendRedirect("error.jsp?status=404");
		}
	}

	private void gravarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usuario = new Usuario();		
		
		usuario.setUsuario(request.getParameter("txtUsuario"));
		usuario.setSenha(request.getParameter("txtSenha"));
		if(usuarioServico.salvar(usuario)) {
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}

	private void editarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = new Usuario();
		usuario.setSenha(request.getParameter("txtSenha"));
		usuario.setUsuario(request.getParameter("txtUsuario"));
		if(usuarioServico.atualizar(usuario)) {
			response.getWriter().append("Dados atualizados com sucesso");
		} else {
			response.getWriter().append("Falha ao atualizar");
		}
	}
	
	private void listaDeUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			ArrayList<Usuario> lista = usuarioServico.lista();
			
			request.setAttribute("lista", lista);
			request.getRequestDispatcher("/listar.jsp").forward(request, response);
	}

	private void localizarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usuario = usuarioServico.buscaPorEmail(request.getParameter("txtLogin"));
		
		request.setAttribute("usuario", usuario);
		request.getRequestDispatcher("/edtform.jsp").forward(request, response);
	}

	private void excluirUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		usuarioServico.apagar(Long.parseLong(request.getParameter("txtIdE")));
		response.sendRedirect("/listar.jsp");
	}
	
	private void loginUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(usuarioServico.login(request.getParameter("txtLogin"), request.getParameter("txtSenha"))) {
			response.sendRedirect("/start.jsp");
		}else {
			response.sendRedirect("/index.jsp");
		}
	}
}

		

