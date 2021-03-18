/*
 * Exerc�cio CRUD Lu�s
 * Autor: Felipe Alheiro
 * Objetivo do projeto : Criar um projeto de CRUD funcional
 * Objetivo: Declarar a classe de persit�ncia DAO com os m�todos do CRUD que herda DAO Class
 * */
package persist;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Contato;
import entity.Endereco;
import entity.Telefone;

public class DAOContato extends DAOClass{
	Connection conn = null;
	PreparedStatement pstmt = null;
	//Statement stmt = null;
	String querysql = "";
	
	//Construtores
	public DAOContato() {
		super();
	}
	
	public DAOContato(String driver,String url,String login,String senha) {
		super(driver,url,login,senha);
	}
	
	public void abrirConexao() {
		conn = super.getConnection();//exce��o tratada no m�todo - meu super � redundante
	}
	public void fecharConexao() {
		endConnection(conn);
		conn = null;
	}
	
	
	//Alguns m�todos precisaram de confirma��o de realiza��o de tarefas.
	//Devo me atentar para generated_key que vem com os ResultSet
	
	//M�todos de Contato
	//Inser��o
	public void inserirContato(Contato contato) throws SQLException {
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("Conex�o n�o est� aberta!");
		}
		querysql = "insert into contato(nome,sobrenome,cpf,dt_nascimento,email) values (?,?,?,?,?) ";
		try {			
			//primeira etapa inserir o que depende s� de Contato - preparar o preparedStatement
			try {
				pstmt = conn.prepareStatement(querysql);
				pstmt.setString(1, contato.getNome());
				pstmt.setString(2, contato.getSobrenome());
				pstmt.setString(3, contato.getCpf());
				pstmt.setDate(4, (Date)contato.getDt_nascimento());
				pstmt.setString(5, contato.getEmail());
			}catch(SQLException e) {
				pstmt.close();
				throw new SQLException("Erro na passagem dos Biding Parameters de Contato.");
			}
			//executar a query
			if(pstmt.executeUpdate()==0) {
				throw new SQLException("Erro na opera��o de inser��o de Contato!");
			}
			
			//inserir o telefone				
			long id_contato=0L;
			//aqui o uso novo de try que o Lu�s apresentou
			try(ResultSet key = pstmt.getGeneratedKeys()){
				if(key.next()) {
					id_contato = key.getLong(1);
					pstmt.close();
				}else {
					throw new SQLException("O ResultSet n�o recuperou dados!");
				}
			}			
			//preparar o endere�o
			try {
				Endereco tempaddr = contato.getEndereco();
				if(tempaddr != null) {
					inserirEndereco(tempaddr);
				}
			}catch(SQLException e) {
				// Estou tentando jogar o erro pra cima!
				throw new SQLException("Erro na opera��o de inser��o de Endere�o!");
			}	
				
			//preparar pr�xima consulta para cada telefone da lista de telefones inserir um telefone e associar o id					
			ArrayList<Telefone> templist = contato.getTelefone();
			try {
				if(templist.size() > 0) {
					for(Telefone temptel : templist) {
						inserirTelefone(temptel,id_contato);
					}
				}				
			}catch(SQLException e) {
				// Estou tentando jogar o erro pra cima!
				throw new SQLException("Erro na opera��o de inser��o de Lista de Telefone!");
			}						
			System.out.println("Inser��o bem sucedida!");
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("N�o conseguiu inserir o contato!");
			conn.rollback();//para o caso de algo ter sido modificado
		}finally {
			if(!pstmt.isClosed())
				pstmt.close();			
		}
	}
	
	//Inser��o de Endere�o - A conex�o tem de estar aberta neste m�todo
	public void inserirEndereco(Endereco endereco) throws SQLException {
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("Conex�o n�o est� aberta!");
		}
		querysql = "insert into endereco(tipo_logradouro,logradouro,complemento,bairro,cidade,estado,pais,id_contato_residente) values (?,?,?,?,?,?,?,?)";
		
		try {
			//preparando o Statement
			try {
				pstmt = conn.prepareStatement(querysql);
				pstmt.setString(1, endereco.getTipo_logradouro());
				pstmt.setString(2, endereco.getLogradouro());
				pstmt.setString(3, endereco.getComplemento());
				pstmt.setString(4, endereco.getBairro());
				pstmt.setString(5, endereco.getCidade());
				pstmt.setString(6, endereco.getEstado());
				pstmt.setString(7, endereco.getPais());
				pstmt.setLong(8, endereco.getId_contato_residente());
			}catch(SQLException e) {
				pstmt.close();
				//Quero jogar a exce��o para o m�todo chamador
				throw new SQLException("Erro na passagem dos Biding Parameters.");	
			}
		//preparar o endere�o e executar a query
			if(pstmt.executeUpdate()==0) {
				throw new SQLException("Erro na opera��o de inser��o!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("N�o conseguiu inserir o Endere�o!");
			conn.rollback();//para o caso de algo ter sido modificado			
		}finally {
			if(!pstmt.isClosed())
				pstmt.close();	
		}
		System.out.println("Inser��o bem sucedida!");
	}
		
	//Inser��o de Telefone - A conex�o tem de estar aberta neste m�todo
	public void inserirTelefone(Telefone telefone, long id_contato) throws SQLException {
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("Conex�o n�o est� aberta!");
		}
		querysql = "insert into telefone(ddi,ddd,numero_telefone,id_cliente_associado) values (?,?,?,?)";
		//preparando o Statement
		try {
			try {		
				pstmt = conn.prepareStatement(querysql);
				pstmt.setString(1, telefone.getDdi());
				pstmt.setString(2, telefone.getDdd());
				pstmt.setString(3, telefone.getNumero_telefone());
				pstmt.setLong(4, id_contato);
			}catch(SQLException e) {
				//Quero jogar a exce��o para o m�todo chamador
				throw new SQLException("Erro na passagem dos Biding Parameters.");	
			}
			//preparar o endere�o e executar a query
			if(pstmt.executeUpdate()==0) {
				throw new SQLException("Erro na opera��o de inser��o!");
			}
		}catch(SQLException e) {
			conn.rollback();
			throw new SQLException("N�o conseguiu inserir o Endere�o!");
			//para o caso de algo ter sido modificado
		}finally {
			if(!pstmt.isClosed())
				pstmt.close();
		}
		System.out.println("Inser��o bem sucedida!");
	}	
	
	//Busca por ID em contato
	public Contato buscaContatoId(long idC) throws SQLException {
		Contato resultado = new Contato();
		ResultSet respostaSQL = null;
		
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("N�o conseguiu realizar a consulta!");
		}
		querysql = "select a.nome,a.sobrenome,a.cpf,a.email,a.dt_nascimento,e.tipo_logradouro,e.complemento,e.logradouro,e.bairro,e.cidade,e.estado,e.pais,t.ddi,t.ddd,t.numero_telefone"
				+" from contato a"
				+" inner join endereco e on e.id_contato_residente = a.id_contato"
				+" inner join telefone t on t.id_cliente_associado = a.id_contato"
				+ " where id_contato = ?";
		try {			
			pstmt = conn.prepareStatement(querysql);
			pstmt.setLong(1, idC);		
			respostaSQL = pstmt.executeQuery();
			if(respostaSQL.next()) {
				resultado = new Contato(respostaSQL.getString(1),respostaSQL.getString(2),respostaSQL.getString(3),respostaSQL.getString(4),(Date)respostaSQL.getDate(5));
				Endereco tempaddr = new Endereco(respostaSQL.getString(6),respostaSQL.getString(7),respostaSQL.getString(8),respostaSQL.getString(9),respostaSQL.getString(10),respostaSQL.getString(11),respostaSQL.getString(12),idC);
				ArrayList<Telefone> tellist = new ArrayList<Telefone>();
				tellist.add(new Telefone(respostaSQL.getString(13),respostaSQL.getString(14),respostaSQL.getString(15)));
				while(respostaSQL.next()) {
					tellist.add(new Telefone(respostaSQL.getString(13),respostaSQL.getString(14),respostaSQL.getString(15)));
				}
				resultado.setEndereco(tempaddr);
				resultado.setTelefone(tellist);
			}else {
				System.out.println("Tabela est� vazia!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("N�o conseguiu realizar a consulta!");
			conn.rollback();//para o caso de algo ter sido modificado
		}finally {
			if(!respostaSQL.isClosed())
				respostaSQL.close();
			if(!pstmt.isClosed())
				pstmt.close();	
		}		
		return resultado;
	}
	
	//Busca por ID em Endereco
	public Endereco buscaEnderecoId(long idE) throws SQLException {
		Endereco resultado = new Endereco();
		ResultSet respostaSQL = null;
		
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("N�o conseguiu realizar a consulta!");
		}
		querysql = "select e.tipo_logradouro,e.complemento,e.logradouro,e.bairro,e.cidade,e.estado,e.pais,e.id_contato_residente"
				+" from endereco e"
				+ " where id_endereco = ?";
		try {			
			pstmt = conn.prepareStatement(querysql);
			pstmt.setLong(1, idE);		
			respostaSQL = pstmt.executeQuery();
			if(respostaSQL.next()) {					
				resultado = new Endereco(idE,respostaSQL.getString(1),respostaSQL.getString(2),respostaSQL.getString(3),respostaSQL.getString(4),respostaSQL.getString(5),respostaSQL.getString(6),respostaSQL.getString(7),respostaSQL.getLong(8));
				
			}else {
				System.out.println("Tabela est� vazia!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("N�o conseguiu realizar a consulta!");
			conn.rollback();//para o caso de algo ter sido modificado			
		}finally {
			if(!respostaSQL.isClosed())
				respostaSQL.close();
			if(!pstmt.isClosed())
				pstmt.close();	
		}		
		return resultado;
	}
	
	//Busca por ID em Telefone
	public Telefone buscaTelefoneId(long idT) throws SQLException {
		Telefone resultado = new Telefone();
		ResultSet respostaSQL = null;
		
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("N�o conseguiu realizar a consulta!");
		}
		querysql = "select t.ddi,t.ddd,t.telefone"
				+" from telefone t"
				+ " where id_telefone = ?";
		try {			
			pstmt = conn.prepareStatement(querysql);
			pstmt.setLong(1, idT);		
			respostaSQL = pstmt.executeQuery();
			if(respostaSQL.next()) {					
				resultado = new Telefone(idT,respostaSQL.getString(1),respostaSQL.getString(2),respostaSQL.getString(3));
				
			}else {
				System.out.println("Tabela est� vazia!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("N�o conseguiu realizar a consulta!");
			conn.rollback();//para o caso de algo ter sido modificado			
		}finally {
			if(!respostaSQL.isClosed())
				respostaSQL.close();
			if(!pstmt.isClosed())
				pstmt.close();	
		}		
		return resultado;
	}	
	//Busca por ID de contato em Endereco
	public Endereco buscaEnderecoIdContato(long idC) throws SQLException {
		Endereco resultado = new Endereco();
		ResultSet respostaSQL = null;
		
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("N�o conseguiu realizar a consulta!");
		}
		querysql = "select e.id_endereco,e.tipo_logradouro,e.complemento,e.logradouro,e.bairro,e.cidade,e.estado,e.pais,e.id_contato_residente"
				+" from endereco e"
				+ " where id_contato_residente = ?";
		try {			
			pstmt = conn.prepareStatement(querysql);
			pstmt.setLong(1, idC);		
			respostaSQL = pstmt.executeQuery();
			if(respostaSQL.next()) {					
				resultado = new Endereco(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3),respostaSQL.getString(4),respostaSQL.getString(5),respostaSQL.getString(6),respostaSQL.getString(7),respostaSQL.getString(8),idC);
			}else {
				System.out.println("Tabela est� vazia!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("N�o conseguiu realizar a consulta!");
			conn.rollback();//para o caso de algo ter sido modificado			
		}finally {
			if(!respostaSQL.isClosed())
				respostaSQL.close();
			if(!pstmt.isClosed())
				pstmt.close();	
		}		
		return resultado;
	}
	
	//Busca por ID de contato em Telefone
	public ArrayList<Telefone> buscaTelefonesIdContato(long idC) throws SQLException {
		ArrayList<Telefone> resultado = new ArrayList<Telefone>();
		Telefone temptel = new Telefone();
		ResultSet respostaSQL = null;
		
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("N�o conseguiu realizar a consulta!");
		}
		querysql = "select t.ddi,t.ddd,t.telefone"
				+" from telefone t "
				+ " where id_cliente_associado = ?";
		try {			
			pstmt = conn.prepareStatement(querysql);
			pstmt.setLong(1, idC);		
			respostaSQL = pstmt.executeQuery();
			while(respostaSQL.next()) {					
				temptel = new Telefone(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3),respostaSQL.getString(4));
				resultado.add(temptel);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("N�o conseguiu realizar a consulta!");
			conn.rollback();//para o caso de algo ter sido modificado			
		}finally {
			if(!respostaSQL.isClosed())
				respostaSQL.close();
			if(!pstmt.isClosed())
				pstmt.close();	
		}		
		return resultado;
	}
	
	//Listar Elementos
	public ArrayList<Contato> listarContatos() throws SQLException {
		ArrayList<Contato> resultado = new ArrayList<Contato>();
		ResultSet respostaSQL = null;
		
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("Conex�o n�o est� aberta!");
		}
		querysql = "select a.nome,a.sobrenome,a.cpf,a.email,a.dt_nascimento,e.tipo_logradouro,e.complemento,e.logradouro,e.bairro,e.cidade,e.estado,e.pais,t.ddi,t.ddd,t.numero_telefone,a.id_contato"
				+" from contato a"
				+" inner join endereco e on e.id_contato_residente = a.id_contato"
				+" inner join telefone t on t.id_cliente_associado = a.id_contato";
		pstmt = conn.prepareStatement(querysql);
		
		try {
			respostaSQL = pstmt.executeQuery();			
		}catch(SQLException e) {
			System.err.println("Erro ao receber a Query!");
			return resultado;
		}
		//recuperando o Contato			
		try {
			Date dt = null;
			Contato tempcontato=null;
			Endereco addrtemp=null;
			Telefone teltemp = null;
			ArrayList<Telefone> tmptellist = null;
			while(respostaSQL.next()) {
				if(tempcontato!=null) {
					if(tempcontato.getId_contato() == respostaSQL.getLong(15)) {
						teltemp = new Telefone(respostaSQL.getString(12),respostaSQL.getString(13),respostaSQL.getString(14));
						tempcontato.getTelefone().add(teltemp);
						continue;
					}						
				}
				tmptellist = new ArrayList<Telefone>();
				dt = (Date)respostaSQL.getDate(6);
				tempcontato = new Contato(respostaSQL.getLong(15),respostaSQL.getString(1),respostaSQL.getString(2),respostaSQL.getString(3),respostaSQL.getString(4),dt);
				addrtemp = new Endereco(respostaSQL.getString(5),respostaSQL.getString(6),respostaSQL.getString(7),respostaSQL.getString(8),respostaSQL.getString(9),respostaSQL.getString(10),respostaSQL.getString(11),respostaSQL.getLong(15));
				teltemp = new Telefone(respostaSQL.getString(12),respostaSQL.getString(13),respostaSQL.getString(14));
				//preenche
				tmptellist.add(teltemp);
				tempcontato.setTelefone(tmptellist);
				tempcontato.setEndereco(addrtemp);					
				resultado.add(tempcontato);
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println("Erro ao manipular os par�metros do ResultSet!");
		}finally {
			if(!respostaSQL.isClosed())
				respostaSQL.close();
			if(!pstmt.isClosed())
				pstmt.close();
		}		
		return resultado;
	}
	//Atualiza��o
	public void atualizarEndereco(Endereco endereco) throws SQLException {
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("Conex�o n�o est� aberta!");
		}
		//atualiza
		querysql = "update endereco set tipo_logradouro=?,logradouro=?,complemento= ?,bairro=?,cidade=? ,estado=?,pais=? ,id_contato_residente=? where id_endereco = ?";
		try {
			try {
				pstmt.setString(1, endereco.getTipo_logradouro());
				pstmt.setString(2, endereco.getLogradouro());
				pstmt.setString(3, endereco.getComplemento());
				pstmt.setString(4, endereco.getBairro());
				pstmt.setString(5, endereco.getCidade());
				pstmt.setString(6, endereco.getEstado());
				pstmt.setString(7,endereco.getPais());
				pstmt.setLong(8, endereco.getId_contato_residente());
			}catch(SQLException e){
				throw new SQLException("Erro na passagem dos Biding Parameters.");
			}
			//preparar o endere�o e executar a query
			if(pstmt.executeUpdate()==0)
				throw new SQLException("Erro na opera��o de atualiza��o!");
			System.out.println("Atualiza��o bem sucedida!");
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("N�o conseguiu atualizar o Endere�o!");
		}finally {
			if(!pstmt.isClosed())
				pstmt.close();
		}		
	}
	//Atualiza��o
	public void atualizarTelefone(Telefone telefone) throws SQLException {
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("Conex�o n�o est� aberta!");
		}
		//atualiza
		querysql = "update telefone set ddi=?,ddd=?,numero_telefone= ? where id_telefone = ?";
		try {				
			//perguntar ao Lu�s quanto a visibilidade dos m�todos de get e set ids
			try {
				pstmt.setString(1, telefone.getDdi());
				pstmt.setString(2, telefone.getDdd());
				pstmt.setString(3, telefone.getNumero_telefone());					
				pstmt.setLong(8, telefone.getId_telefone());
			}catch(SQLException e){
				throw new SQLException("Erro na passagem dos Biding Parameters.");
			}
			//preparar o endere�o e executar a query
			if(pstmt.executeUpdate()==0)
				throw new SQLException("Erro na opera��o de atualiza��o!");
			System.out.println("Atualiza��o bem sucedida!");
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("N�o conseguiu atualizar o telefone!");
		}finally {
			if(!pstmt.isClosed())
				pstmt.close();
		}		
	}	
	//Atualiza��o
	public void atualizarContato(Contato contato) throws SQLException {
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("Conex�o n�o est� aberta!");
		}
		//atualiza o contato depois o endere�o e o telefone	
		querysql = "update contato set nome=?,sobrenome=?,cpf=?,dt_nascimento=?,email=? where id_contato = ?";		
		
		/*perguntar ao Lu�s quanto a visibilidade dos m�todos de get e set dos ids*/
		try {				
			//primeiro atualiza o contato
			try {
				pstmt = conn.prepareStatement(querysql);
				pstmt.setString(1, contato.getNome());
				pstmt.setString(2, contato.getSobrenome());
				pstmt.setString(3, contato.getCpf());
				pstmt.setDate(4, (Date)contato.getDt_nascimento());			
				pstmt.setString(5, contato.getEmail());
			}catch(SQLException e){
				throw new SQLException("Erro na passagem dos Biding Parameters.");
			}
			//preparar e executar a query
			if(pstmt.executeUpdate()==0)
				throw new SQLException("Erro na opera��o de atualiza��o!");
			//vamos para Endereco e lista de telefone
			pstmt.close();
			
			try {
				Endereco tempaddr = contato.getEndereco();
				if(tempaddr !=null && tempaddr.getId_contato_residente()==contato.getId_contato()) {
					atualizarEndereco(tempaddr);
				}
			}catch(SQLException e) {
				throw new SQLException("Erro ao atualizar Endere�o do Contato!");
			}
			
			try {	
				ArrayList<Telefone> tellsttemp = contato.getTelefone();
				for(Telefone teltemp : tellsttemp) {
					atualizarTelefone(teltemp);
				}
				System.out.println("Atualiza��o bem sucedida!");
			}catch(SQLException e) {
				throw new SQLException("Erro ao atualizar Telefone do Contato!");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("N�o conseguiu atualizar o Contato!");
			conn.rollback();//desfaz atualiza��es feitas
		}finally {
			if(!pstmt.isClosed())
				pstmt.close();
		}		
	}
	//Listar Elementos	
	public ArrayList<Endereco> listarEnderecos() throws SQLException {
		ArrayList<Endereco> resultado = new ArrayList<Endereco>();
		Endereco addrtemp = new Endereco();
		ResultSet respostaSQL = null;
		
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("Conex�o n�o est� aberta!");
		}
		querysql = "select * from endereco";
		try {
			pstmt = conn.prepareStatement(querysql);			
			try {
				respostaSQL = pstmt.executeQuery();			
			}catch(SQLException e) {
				throw new SQLException("Erro ao receber a Query!");
			}		
			while(respostaSQL.next()) {			
				addrtemp = new Endereco(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3),respostaSQL.getString(4),respostaSQL.getString(5),respostaSQL.getString(6),respostaSQL.getString(7),respostaSQL.getString(8),respostaSQL.getLong(9));
				resultado.add(addrtemp);
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println("Erro ao manipular os par�metros do ResultSet!");
		}finally {
			if(!respostaSQL.isClosed())
				respostaSQL.close();
			if(!pstmt.isClosed())
				pstmt.close();
		}		
		return resultado;		
	}
	//Listar Elementos	
	public ArrayList<Telefone> listarTelefones() throws SQLException {
		ArrayList<Telefone> resultado = new ArrayList<Telefone>();
		Telefone addrtemp = new Telefone();
		ResultSet respostaSQL = null;
		
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("Conex�o n�o est� aberta!");
		}
		querysql = "select * from telefone";
		try {
			pstmt = conn.prepareStatement(querysql);			
			try {
				respostaSQL = pstmt.executeQuery();			
			}catch(SQLException e) {
				throw new SQLException("Erro ao receber a Query!");
			}		
			while(respostaSQL.next()) {			
				addrtemp = new Telefone(respostaSQL.getLong(1),respostaSQL.getString(2),respostaSQL.getString(3),respostaSQL.getString(4));
				resultado.add(addrtemp);
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println("Erro ao manipular os par�metros do ResultSet!");
		}finally {
			if(!respostaSQL.isClosed())
				respostaSQL.close();
			if(!pstmt.isClosed())
				pstmt.close();
		}		
		return resultado;		
	}
	
	//Remo��o
	public void removerContato(long idC) throws SQLException {
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("Conex�o n�o est� aberta!");
		}
		querysql = "delete from contato where id_contato = ?";	
		try {
			pstmt = conn.prepareStatement(querysql);
			pstmt.setLong(1, idC);
			pstmt.executeUpdate();
			System.out.println("Remo��o bem sucedida!");
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("Erro na opera��o de remo��o!");
		}finally {
			pstmt.close();
		}
	}
	
	public void removerEndereco(long idE) throws SQLException {
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("Conex�o n�o est� aberta!");
		}
		querysql = "delete from endereco where id_endereco = ?";
		try {
			pstmt = conn.prepareStatement(querysql);	
			pstmt.setLong(1, idE);
			pstmt.executeUpdate();
			System.out.println("Remo��o bem sucedida!");
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("Erro na opera��o de remo��o!");
		}finally {
			pstmt.close();
		}
	}
	public void removerTelefone(long idT) throws SQLException {
		//checar se tem uma conex�o aberta
		try {
			if(conn.isClosed()) {
				abrirConexao();
			}
		}catch(SQLException e) {
			throw new SQLException("Conex�o n�o est� aberta!");
		}
		querysql = "delete from telefone where id_telefone = ?";
		try {
			pstmt = conn.prepareStatement(querysql);	
			pstmt.setLong(1, idT);
			pstmt.executeUpdate();
			System.out.println("Remo��o bem sucedida!");
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println("Erro na opera��o de remo��o!");
		}finally {
			pstmt.close();
		}
	}	
}