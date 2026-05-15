package br.com.model;

public class Devolutor {
	private  String cpf_devolutor;
	private  String nome_devolutor;
	private  String telefone_devolutor;
	private  String email_devolutor;
	
	public Devolutor() {
		
	}

	public Devolutor(String cpf_devolutor, String nome_devolutor, String telefone_devolutor, String email_devolutor) {
		super();
		this.cpf_devolutor = cpf_devolutor;
		this.nome_devolutor = nome_devolutor;
		this.telefone_devolutor = telefone_devolutor;
		this.email_devolutor = email_devolutor;
	}

	public String getCpf_devolutor() {
		return cpf_devolutor;
	}

	public void setCpf_devolutor(String cpf_devolutor) {
		this.cpf_devolutor = cpf_devolutor;
	}

	public String getNome_devolutor() {
		return nome_devolutor;
	}

	public void setNome_devolutor(String nome_devolutor) {
		this.nome_devolutor = nome_devolutor;
	}

	public String getTelefone_devolutor() {
		return telefone_devolutor;
	}

	public void setTelefone_devolutor(String telefone_devolutor) {
		this.telefone_devolutor = telefone_devolutor;
	}

	public String getEmail_devolutor() {
		return email_devolutor;
	}

	public void setEmail_devolutor(String email_devolutor) {
		this.email_devolutor = email_devolutor;
	}
	
	
}