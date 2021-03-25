package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import entity.Cliente;
import entity.Endereco;
import entity.Funcionario;
import repository.PessoaRepository;
import util.HibernateUtil;

@WebServlet({ "/addFunc", "/edtFunc", "/listFunc", "/findFunc", "/delFunc","/addCliente", "/edtCliente", "/listCliente", "/findCliente", "/delCliente" })
public class PessoaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Logger log = LogManager.getLogger(PessoaController.class);
	private PessoaRepository repository;
	
	public PessoaController() {
		this.repository = new PessoaRepository();
		repository.setEntityManager(HibernateUtil.getEntitymanager());
	}

	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log.info("Iniciando a servlet");
    }

    public void destroy() {
        super.destroy();
        log.info("Destruindo a servlet");
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/listCliente")) {
			listarCliente(request, response);
		} else if (request.getServletPath().equals("/findCliente")) {
			buscarCliente(request, response);
		} else if (request.getServletPath().equals("/delCliente")) {
			excluirCliente(request, response);
		} else if (request.getServletPath().equals("/listFunc")) {
			listarFuncionario(request, response);
		} else if (request.getServletPath().equals("/findFunc")) {
			buscarFuncionario(request, response);
		} else if (request.getServletPath().equals("/delFunc")) {
			excluirFuncionario(request, response);
		} else {
			response.sendRedirect("error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/addCliente")) {
			cadastrarCliente(request, response);
		} else if (request.getServletPath().equals("/edtCliente")) {
			editarCliente(request, response);
		} else if (request.getServletPath().equals("/addFunc")) {
			cadastrarFuncionario(request, response);
		} else if (request.getServletPath().equals("/edtFunc")) {
			editarFuncionario(request, response);
		} else {
			response.sendRedirect("error.jsp");
		}
	}

	private void cadastrarCliente(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();
		// dados básicos

		if(!request.getParameter("inputid").trim().isEmpty() || !request.getParameter("inputid").isEmpty()) {
			cliente.setId(Long.parseLong(request.getParameter("inputid")));
		}
		
		cliente.setNome(request.getParameter("inputnome"));
		cliente.setCadastro(request.getParameter("inputmatricula"));
		cliente.setEmail(request.getParameter("inputemail"));
		cliente.setCpf(request.getParameter("inputcpf"));
		// dados de endereço
		endereco.setLogradouro(request.getParameter("inputlogradouro"));
		endereco.setBairro(request.getParameter("inputbairro"));
		endereco.setCidade(request.getParameter("inputcidade"));
		endereco.setEstado(request.getParameter("inputestado"));
		endereco.setPais(request.getParameter("inputpais"));
		// relação Cliente x endereço
		cliente.setEndereco(endereco);
		
		repository.manterCliente(cliente);

		listarCliente(request, response);
	}

	private void listarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lista", repository.listarClientes());
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	
	private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		cadastrarCliente(request, response);
	}
	
	private void buscarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("Cliente", repository.buscaCliente(Long.parseLong(request.getParameter("id"))));
		listarCliente(request, response);
	
	}
	private void excluirCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		repository.apagarCliente(Long.parseLong(request.getParameter("id")));
		listarCliente(request, response);
	}
	
	
	private void cadastrarFuncionario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Funcionario funcionario = new Funcionario();
		Endereco endereco = new Endereco();
		// dados básicos

		if(!request.getParameter("inputid").trim().isEmpty()|| !request.getParameter("inputid").isEmpty()) {
			funcionario.setId(Long.parseLong(request.getParameter("inputid")));
		}
		
		funcionario.setNome(request.getParameter("inputnome"));
		funcionario.setEmail(request.getParameter("inputemail"));
		funcionario.setCpf(request.getParameter("inputcpf"));
		funcionario.setMatricula(request.getParameter("inputmatricula"));
		funcionario.setDepartamento(request.getParameter("inputdepto"));
		// dados de endereço
		endereco.setLogradouro(request.getParameter("inputlogradouro"));
		endereco.setBairro(request.getParameter("inputbairro"));
		endereco.setCidade(request.getParameter("inputcidade"));
		endereco.setEstado(request.getParameter("inputestado"));
		endereco.setPais(request.getParameter("inputpais"));
		// relação Funcionario x endereço
		funcionario.setEndereco(endereco);
		
		repository.manterFuncionario(funcionario);

		listarFuncionario(request, response);
	}

	private void listarFuncionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lista", repository.listarFuncionarios());
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	
	private void editarFuncionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		cadastrarFuncionario(request, response);
	}
	
	private void buscarFuncionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("Funcionario", repository.buscaFuncionario(Long.parseLong(request.getParameter("id"))));
		listarFuncionario(request, response);
	
	}
	private void excluirFuncionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		repository.apagarFuncionario(Long.parseLong(request.getParameter("id")));
		listarFuncionario(request, response);
	}
}
