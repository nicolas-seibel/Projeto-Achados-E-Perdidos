package br.com.model;

import java.time.LocalDate;

public class Entregas {
private	Integer id_entrega;		   
private	Integer id_item;
private	String	cpf_reclamante;
private	String	cpf_devolutor;
private	LocalDate data_entrega;

public Entregas() {
	
}

public Entregas(Integer id_entrega, Integer id_item, String cpf_reclamante, String cpf_devolutor,
		LocalDate data_entrega) {
	super();
	this.id_entrega = id_entrega;
	this.id_item = id_item;
	this.cpf_reclamante = cpf_reclamante;
	this.cpf_devolutor = cpf_devolutor;
	this.data_entrega = data_entrega;
}

public Integer getId_entrega() {
	return id_entrega;
}

public void setId_entrega(Integer id_entrega) {
	this.id_entrega = id_entrega;
}

public Integer getId_item() {
	return id_item;
}

public void setId_item(Integer id_item) {
	this.id_item = id_item;
}

public String getCpf_reclamante() {
	return cpf_reclamante;
}

public void setCpf_reclamante(String cpf_reclamante) {
	this.cpf_reclamante = cpf_reclamante;
}

public String getCpf_devolutor() {
	return cpf_devolutor;
}

public void setCpf_devolutor(String cpf_devolutor) {
	this.cpf_devolutor = cpf_devolutor;
}

public LocalDate getData_entrega() {
	return data_entrega;
}

public void setData_entrega(LocalDate data_entrega) {
	this.data_entrega = data_entrega;
}









}