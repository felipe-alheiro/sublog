package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.sql.Date;

public class Contato {
	private long id_contato;
	private String nome;
	private String sobrenome;
	private String cpf;
	private String email;
	private Period dt_nascimento = Period.of(0, 0, 0);
	private String telefone;
			
	public Contato() {
		super();
	}	
	public Contato(long id_contato, String nome, String sobrenome, String cpf, Period dt_nascimento, String email, String telefone) {
		super();
		this.id_contato = id_contato;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.dt_nascimento = dt_nascimento;
		this.email = email;
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getId_contato() {
		return id_contato;
	}
	public void setId_contato(long id_contato) {
		this.id_contato = id_contato;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Period getDt_nascimento() {
		return dt_nascimento;
	}
	public void setDt_nascimentoYMD(int ano, int mes, int dia) {
		this.dt_nascimento = Period.of(ano, mes, dia);
	}
	
	public void setDt_nascimento(Period data_YMD) {
		this.dt_nascimento = data_YMD;
	}
	public void limparContato() {
		this.id_contato = 0l;
		this.nome ="";
		this.sobrenome = "";
		this.cpf = "";
		this.dt_nascimento = null;
		this.email = "";
		this.telefone="";
	}
	public void preencherContato(long id_contato, String nome, String sobrenome, String cpf, Period dt_nascimento, String email,String telefone) {
		this.id_contato = id_contato;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.dt_nascimento = dt_nascimento;
		this.email = email;
		this.telefone = telefone;
	}
	
	public static String periodToDateFormateString(Period p1) {
		String texto="";
		texto+=p1.getYears()+"-"+p1.getMonths()+"-"+p1.getDays();
		return texto;
	}
	
	public static Date converterPeriodToSQLdate(Period p1) {
		String texto = periodToDateFormateString(p1);
		java.util.Date dt_util = null;
		try {
			dt_util = new SimpleDateFormat("yyyy-mm-dd").parse(texto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date dt_sql = new java.sql.Date(dt_util.getTime());
		return dt_sql;
	}
	
	public static void getDataFromDB(Object o) {
		String parser = o.toString();
		System.out.println(parser);
	}
	
	public static Period converterSQLdateToPeriod(Date d1) {
		Period p1 = Period.of(d1.getYear(),d1.getMonth(),d1.getDay());		
		return p1;
	}
	
	@Override
	protected Contato clone() throws CloneNotSupportedException {
		Contato clonado = new Contato();
		clonado.id_contato = this.id_contato;
		clonado.nome = this.nome;
		clonado.sobrenome = this.sobrenome;
		clonado.cpf = this.cpf;
		clonado.dt_nascimento = this.dt_nascimento;
		clonado.email = this.email;
		clonado.telefone = this.telefone;
		return clonado;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
		
}
