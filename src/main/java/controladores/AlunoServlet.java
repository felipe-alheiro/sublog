package controladores;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import entidade.Aluno;
import entidade.Endereco;
import repositorio.RepositorioDAO;

public class AlunoServlet extends HttpServlet{
	private static final long serialVersionUID = -1882461465779892248L;
	Logger log = LogManager.getLogger(AlunoServlet.class);
	private RepositorioDAO conector = null;
	
	public AlunoServlet() {
		super();
		this.conector = new RepositorioDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getServletPath().equals("/listAluno")) {
			listarAluno(request, response);
		} else if (request.getServletPath().equals("/findAluno")) {
			buscarAluno(request, response);
		} else if (request.getServletPath().equals("/delAluno")) {
			excluirAluno(request, response);
		} else if (request.getServletPath().equals("/listAluno")) {
			listarAluno(request, response);
		} else if (request.getServletPath().equals("/findEndereco")) {
			buscarEndereco(request, response);
		} else if (request.getServletPath().equals("/delEndereco")) {
			excluirEndereco(request, response);
		} else if (request.getServletPath().equals("/listEndereco")) {
			listarEndereco(request, response);
		} else {
			response.sendRedirect("error.jsp");
		}		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getServletPath().equals("/addAluno")) {
			cadastrarAluno(request, response);
		} else if (request.getServletPath().equals("/edtAluno")) {
			editarAluno(request, response);
		} else if (request.getServletPath().equals("/addEndereco")) {
			cadastrarEndereco(request, response);
		} else if (request.getServletPath().equals("/edtEndereco")) {
			editarEndereco(request, response);
		} else {
			response.sendRedirect("error.jsp");
		}
	}
	
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log.info("Iniciando a servlet");
    }

    public void destroy() {
        super.destroy();
        log.info("Destruindo a servlet");
    }

	private void cadastrarAluno(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Aluno aluno = new Aluno();
		Endereco endereco = new Endereco();
		// dados básicos
		if(!request.getParameter("inputid").trim().isEmpty() || !request.getParameter("inputid").isEmpty()) {
			aluno.setId_aluno(Long.parseLong(request.getParameter("inputid")));
		}
		
		aluno.setNome(request.getParameter("inputnome"));
		aluno.setIdade(Integer.parseInt(request.getParameter("inputidade")));
		aluno.setEmail(request.getParameter("inputemail"));
		aluno.setCpf(request.getParameter("inputcpf"));
		aluno.setMatricula(request.getParameter("inputmatricula"));
		
		// dados de endereço
		endereco.setCEP(request.getParameter("inputcep"));
		endereco.setLogradouro(request.getParameter("inputlogradouro"));
		endereco.setComplemento(request.getParameter("inputcomplemento"));
		endereco.setCidade(request.getParameter("inputcidade"));
		endereco.setEstado(request.getParameter("inputestado"));
		endereco.setPais(request.getParameter("inputpais"));
		// relação Cliente x endereço
		aluno.setEndereco(endereco);
		
		conector.salvarAluno(aluno);

		listarAluno(request, response);
	}

	private void listarAluno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lista", conector.listarAlunos());
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	
	private void editarAluno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		cadastrarAluno(request, response);
	}
	
	private void buscarAluno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("Aluno", conector.procurarAlunoID(Long.parseLong(request.getParameter("id_aluno"))));
		listarAluno(request, response);
	
	}
	private void excluirAluno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		conector.apagarAluno(Long.parseLong(request.getParameter("id_aluno")));
		listarAluno(request, response);
	}
	
	private void cadastrarEndereco(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Endereco endereco = new Endereco();
		// dados básicos
		if(!request.getParameter("inputid").trim().isEmpty() || !request.getParameter("inputid").isEmpty()) {
			endereco.setId_endereco(Long.parseLong(request.getParameter("inputid")));
		}
		
		// dados de endereço
		endereco.setCEP(request.getParameter("inputcep"));
		endereco.setLogradouro(request.getParameter("inputlogradouro"));
		endereco.setComplemento(request.getParameter("inputcomplemento"));
		endereco.setCidade(request.getParameter("inputcidade"));
		endereco.setEstado(request.getParameter("inputestado"));
		endereco.setPais(request.getParameter("inputpais"));
		// relação Cliente x endereço
		
		conector.salvarEndereco(endereco);
	}
	
	private void listarEndereco(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("lista", conector.listarEnderecos());
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	
	private void editarEndereco(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		cadastrarAluno(request, response);
	}
	
	private void buscarEndereco(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("Endereco", conector.procurarEnderecoID(Long.parseLong(request.getParameter("id_endereco"))));
		listarAluno(request, response);
	
	}
	private void excluirEndereco(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		conector.apagarEndereco(Long.parseLong(request.getParameter("id_endereco")));
		listarEndereco(request, response);
	}

}