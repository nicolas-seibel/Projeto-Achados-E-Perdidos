package br.com.model;

public class Reclamante {
private	 String cpf_reclamante; 
private	String	nome_reclamante;
private	String	telefone_reclamante;
private	String	email_reclamante;


public Reclamante() {
	
}


public Reclamante(String cpf_reclamante, String nome_reclamante, String telefone_reclamante, String email_reclamante) {
	super();
	this.cpf_reclamante = cpf_reclamante;
	this.nome_reclamante = nome_reclamante;
	this.telefone_reclamante = telefone_reclamante;
	this.email_reclamante = email_reclamante;
}


public String getCpf_reclamante() {
	return cpf_reclamante;
}


public void setCpf_reclamante(String cpf_reclamante) {
	this.cpf_reclamante = cpf_reclamante;
}


public String getNome_reclamante() {
	return nome_reclamante;
}


public void setNome_reclamante(String nome_reclamante) {
	this.nome_reclamante = nome_reclamante;
}


public String getTelefone_reclamante() {
	return telefone_reclamante;
}


public void setTelefone_reclamante(String telefone_reclamante) {
	this.telefone_reclamante = telefone_reclamante;
}


public String getEmail_reclamante() {
	return email_reclamante;
}


public void setEmail_reclamante(String email_reclamante) {
	this.email_reclamante = email_reclamante;
}



}