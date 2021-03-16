package controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Endereco;
import services.EnderecoServico;



@WebServlet({ "/adicionarendereco", "/editarendereco", "/listarenderecos", "/buscarendereco", "/removerendereco" })
public class EnderecoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private EnderecoServico enderecoServico;

	public EnderecoServlet() {
		enderecoServico = new EnderecoServico();
	}

	// atende o método HTTP GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/listarcontato")) {
			listaDeEnderecos(request, response);
		} else if (request.getServletPath().equals("/buscarcontato")) {
			localizarEndereco(request, response);
		} else if (request.getServletPath().equals("/removercontato")) {
			excluirEndereco(request, response);
		} else {
			response.sendRedirect("error.jsp?status=404");
		}
	}

	// atende o método HTTP POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/adicionarcontatos")) {
			gravarEndereco(request, response);
		} else if (request.getServletPath().equals("/editarcontato")) {
			editarEndereco(request, response);
		} else {
			response.sendRedirect("error.jsp?status=404");
		}
	}

	private void gravarEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Endereco endereco = new Endereco();
		
		endereco.setTipo_logradouro(request.getParameter("txtTipoLogradouro"));
		endereco.setLogradouro(request.getParameter("txtLogradouro"));
		endereco.setComplemento(request.getParameter("txtComplemento"));
		endereco.setBairro(request.getParameter("txtBairro"));
		endereco.setCidade(request.getParameter("txtCidade"));
		endereco.setEstado(request.getParameter("txtEstado"));
		endereco.setPais(request.getParameter("txtPais"));		
		endereco.setId_endereco(Long.parseLong(request.getParameter("txtIdE")));
		endereco.setId_contato_residente(Long.parseLong(request.getParameter("txtIdC")));
		if(enderecoServico.salvar(endereco)) {
			response.getWriter().append("Dados gravados com sucesso");
		} else {
			response.getWriter().append("Falha ao gravar");
		}
	}

	private void editarEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Endereco endereco = new Endereco();
		endereco.setTipo_logradouro(request.getParameter("txtTipoLogradouro"));
		endereco.setLogradouro(request.getParameter("txtLogradouro"));
		endereco.setComplemento(request.getParameter("txtComplemento"));
		endereco.setBairro(request.getParameter("txtBairro"));
		endereco.setCidade(request.getParameter("txtCidade"));
		endereco.setEstado(request.getParameter("txtEstado"));
		endereco.setPais(request.getParameter("txtPais"));		
		endereco.setId_endereco(Long.parseLong(request.getParameter("txtIdE")));
		endereco.setId_contato_residente(Long.parseLong(request.getParameter("txtIdC")));
		if(enderecoServico.atualizar(endereco)) {
			response.getWriter().append("Dados atualizados com sucesso");
		} else {
			response.getWriter().append("Falha ao atualizar");
		}
	}
	
	private void listaDeEnderecos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			ArrayList<Endereco> lista = enderecoServico.lista();
			
			request.setAttribute("lista", lista);
			request.getRequestDispatcher("/listar.jsp").forward(request, response);
	}

	private void localizarEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Endereco endereco = enderecoServico.buscaPorId(Long.parseLong(request.getParameter("txtIdE")));
		
		request.setAttribute("endereco", endereco);
		request.getRequestDispatcher("/edtform.jsp").forward(request, response);
	}

	private void excluirEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		enderecoServico.apagar(Long.parseLong(request.getParameter("txtIdE")));
		response.sendRedirect("/listar.jsp");
	}
}
