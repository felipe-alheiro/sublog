package controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contato;
import serviços.ContatoServico;



@WebServlet({ "/adicionarcontato", "/editarcontato", "/listarcontato", "/buscarcontato", "/removercontato" })
public class ContatoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private ContatoServico contatoService;

	public ContatoServlet() {
		contatoService = new ContatoServico();
	}

	// atende o método HTTP GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/listarcontato")) {
			listaDeContatos(request, response);
		} else if (request.getServletPath().equals("/buscarcontato")) {
			localizarContato(request, response);
		} else if (request.getServletPath().equals("/removercontato")) {
			excluirContato(request, response);
		} else {
			response.sendRedirect("error.jsp?status=404");
		}
	}

	// atende o método HTTP POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/adicionarcontato")) {
			gravarContato(request, response);
		} else if (request.getServletPath().equals("/editarcontato")) {
			editarContato(request, response);
		} else {
			response.sendRedirect("error.jsp?status=404");
		}
	}

	private void gravarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Contato contato = new Contato();
		
		contato.setCpf(request.getParameter("txtCPF"));
		contato.setTelefone(request.getParameter("txtTelefone"));
		contato.setNome(request.getParameter("txtNome"));
		contato.setSobrenome(request.getParameter("txtSobrenome"));
		contato.setEmail(request.getParameter("txtEmail"));
		contato.setDt_nascimentoYMD(Integer.parseInt(request.getParameter("txtAno")), Integer.parseInt(request.getParameter("txtMes")),Integer.parseInt(request.getParameter("txtDia")));
		
		if(contatoService.salvar(contato)) {
			response.getWriter().append("Dados gravados com sucesso");
		} else {
			response.getWriter().append("Falha ao gravar");
		}
	}

	private void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Contato contato = new Contato();
		contato.setId_contato(Integer.parseInt(request.getParameter("txtId")));
		contato.setCpf(request.getParameter("txtCPF"));
		contato.setTelefone(request.getParameter("txtTelefone"));
		contato.setNome(request.getParameter("txtNome"));
		contato.setSobrenome(request.getParameter("txtSobrenome"));
		contato.setEmail(request.getParameter("txtEmail"));
		contato.setDt_nascimentoYMD(Integer.parseInt(request.getParameter("txtAno")), Integer.parseInt(request.getParameter("txtMes")),Integer.parseInt(request.getParameter("txtDia")));
		
		if(contatoService.atualizar(contato)) {
			response.getWriter().append("Dados atualizados com sucesso");
		} else {
			response.getWriter().append("Falha ao atualizar");
		}
	}

	private void listaDeContatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			ArrayList<Contato> lista = contatoService.lista();
			
			request.setAttribute("lista", lista);
			request.getRequestDispatcher("/listar.jsp").forward(request, response);
	}

	private void localizarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Contato contato = contatoService.buscaPorId(Long.parseLong(request.getParameter("id")));
		
		request.setAttribute("contato", contato);
		request.getRequestDispatcher("/edtform.jsp").forward(request, response);
	}

	private void excluirContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		contatoService.apagar(Long.parseLong(request.getParameter("id")));
		response.sendRedirect("/listar.jsp");
	}
	
	
	
}
