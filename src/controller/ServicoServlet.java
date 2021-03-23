package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contato;
import entity.Endereco;
import entity.Telefone;
import services.ContatoServico;


@WebServlet({ "/adicionarcontato", "/editarcontato", "/listarcontatos", "/buscarcontato", "/removercontato",
	"/adicionarendereco", "/editarendereco", "/listarenderecos", "/buscarendereco", "/removerendereco",
	"/adicionartelefone", "/editartelefone", "/listartelefones", "/buscartelefone", "/removertelefone",
})

public class ServicoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ContatoServico contatoServico=null;
		
	public ServicoServlet() {
		contatoServico = new ContatoServico();
	}

	// atende o método HTTP GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/listarcontatos")) {
			listaDeContatos(request, response);
		} else if (request.getServletPath().equals("/buscarcontato")) {
			localizarContato(request, response);
		} else if (request.getServletPath().equals("/removercontato")) {
			excluirContato(request, response);
		} else if (request.getServletPath().equals("/listarenderecos")) {
			listaDeEnderecos(request, response);
		} else if (request.getServletPath().equals("/buscarendereco")) {
			localizarEndereco(request, response);
		} else if (request.getServletPath().equals("/removerendereco")) {
			excluirEndereco(request, response);
		} else if (request.getServletPath().equals("/listartelefones")) {
			listaDeEnderecos(request, response);
		} else if (request.getServletPath().equals("/buscartelefone")) {
			localizarEndereco(request, response);
		} else if (request.getServletPath().equals("/removertelefone")) {
			excluirEndereco(request, response);	
		}else {
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
		} else if (request.getServletPath().equals("/adicionarendereco")) {
			gravarEndereco(request, response);
		} else if (request.getServletPath().equals("/editarendereco")) {
			editarEndereco(request, response);
		} else if (request.getServletPath().equals("/adicionartelefone")) {
			gravarEndereco(request, response);
		} else if (request.getServletPath().equals("/editartelefone")) {
			editarEndereco(request, response);	
		} else {
			response.sendRedirect("error.jsp?status=404");
		}
	}

	private void gravarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Contato contato = new Contato();
		Telefone tel = new Telefone();
		Endereco end = new Endereco();
		
		contato.setNome(request.getParameter("inputNome"));
		contato.setSobrenome(request.getParameter("inputSobrenome"));
		contato.setCpf(request.getParameter("inputCPF"));
		contato.setEmail(request.getParameter("inputEmail"));
		contato.setDt_nascimento(Date.valueOf(request.getParameter("inputAno")+"-"+request.getParameter("inputMes")+"-"+request.getParameter("inputDia")));
				
		tel.setDdi(request.getParameter("inputddi"));
		tel.setDdd(request.getParameter("inputddd"));
		tel.setNumero_telefone(request.getParameter("inputTelefone"));
		
		end.setTipo_logradouro(request.getParameter("inputTipo_logradouro"));
		end.setTipo_logradouro(request.getParameter("inputLogradouro"));
		end.setTipo_logradouro(request.getParameter("inputComplemento"));
		end.setTipo_logradouro(request.getParameter("inputBairro"));
		end.setTipo_logradouro(request.getParameter("inputCidade"));
		end.setTipo_logradouro(request.getParameter("inputEstado"));
		end.setTipo_logradouro(request.getParameter("inputPais"));
		
		contato.setEndereco(end);
		contato.getTelefone().add(tel);
		
		if(contatoServico.salvar(contato)) {
			response.getWriter().append("Dados gravados com sucesso");
		} else {
			response.getWriter().append("Falha ao gravar");
		}
	}

	private void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int numtem =0;
		Contato contato = new Contato();
		ArrayList<Telefone> tellst = new ArrayList<Telefone>();
		Endereco end = new Endereco();
		
		contato.setNome(request.getParameter("inputNome"));
		contato.setSobrenome(request.getParameter("inputSobrenome"));
		contato.setCpf(request.getParameter("inputCPF"));
		contato.setEmail(request.getParameter("inputEmail"));
		contato.setDt_nascimento(Date.valueOf(request.getParameter("inputAno")+"-"+request.getParameter("inputMes")+"-"+request.getParameter("inputDia")));
		
		Integer.valueOf(request.getParameter("inputnumtel"));
		
/*Perguntar como fazer a recuperação de valores*/
		
		tel.setDdi(request.getParameter("inputddi"));
		tel.setDdd(request.getParameter("inputddd"));
		tel.setNumero_telefone(request.getParameter("inputTelefone"));
		
		end.setTipo_logradouro(request.getParameter("inputTipo_logradouro"));
		end.setTipo_logradouro(request.getParameter("inputLogradouro"));
		end.setTipo_logradouro(request.getParameter("inputComplemento"));
		end.setTipo_logradouro(request.getParameter("inputBairro"));
		end.setTipo_logradouro(request.getParameter("inputCidade"));
		end.setTipo_logradouro(request.getParameter("inputEstado"));
		end.setTipo_logradouro(request.getParameter("inputPais"));
		
		contato.setEndereco(end);
		contato.getTelefone().add(tel);
		
		if(contatoServico.atualizar(contato)) {
			response.getWriter().append("Dados atualizados com sucesso");
		} else {
			response.getWriter().append("Falha ao atualizar");
		}
	}

	private void listaDeContatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			ArrayList<Contato> lista = contatoServico.listaContatos();			
			request.setAttribute("lista", lista);
			request.getRequestDispatcher("/listar.jsp").forward(request, response);
	}

	private void localizarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Contato contato = contatoServico.buscaPorId(Long.parseLong(request.getParameter("id")));		
		request.setAttribute("contato", contato);
		request.getRequestDispatcher("/editform.jsp").forward(request, response);
	}

	private void excluirContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		contatoServico.apagar(Long.parseLong(request.getParameter("id")));
		response.sendRedirect("/listar.jsp");
	}
	
	private void gravarEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Endereco endereco = new Endereco();
		
		end.setTipo_logradouro(request.getParameter("inputTipo_logradouro"));
		end.setTipo_logradouro(request.getParameter("inputLogradouro"));
		end.setTipo_logradouro(request.getParameter("inputComplemento"));
		end.setTipo_logradouro(request.getParameter("inputBairro"));
		end.setTipo_logradouro(request.getParameter("inputCidade"));
		end.setTipo_logradouro(request.getParameter("inputEstado"));
		end.setTipo_logradouro(request.getParameter("inputPais"));
		
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
			ArrayList<Endereco> lista = contatoServico.listaEnderecos();
			
			request.setAttribute("lista", lista);
			request.getRequestDispatcher("/listar.jsp").forward(request, response);
	}

	private void localizarEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Endereco endereco = contatoServico.buscaPorId(Long.parseLong(request.getParameter("txtIdE")));
		
		request.setAttribute("endereco", endereco);
		request.getRequestDispatcher("/edtform.jsp").forward(request, response);
	}

	private void excluirEndereco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		enderecoServico.apagar(Long.parseLong(request.getParameter("txtIdE")));
		response.sendRedirect("/listar.jsp");
	}
	
}
