package br.com.model;
import java.sql.Date;
import java.time.LocalDate;

public class Item {
	
	  private  Integer id_item; 
	  private String nome_item;
	  private String descricao;
	  private LocalDate data_achado;
	  private String local_achado;
	  private String status_item; // True = disponivel false = já retirado/devolvido
	  
	public  Item() {
		
	}

	public Item(Integer id_item, String nome_item, String descricao, LocalDate data_achado, String local_achado,
			String status_item) {
		super();
		this.id_item = id_item;
		this.nome_item = nome_item;
		this.descricao = descricao;
		this.data_achado = data_achado;
		this.local_achado = local_achado;
		this.status_item = status_item;
	}

	public Integer getId_item() {
		return id_item;
	}

	public void setId_item(Integer id_item) {
		this.id_item = id_item;
	}

	public String getNome_item() {
		return nome_item;
	}

	public void setNome_item(String nome_item) {
		this.nome_item = nome_item;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData_achado() {
		return data_achado;
	}

	public void setData_achado(LocalDate data_achado) {
		this.data_achado = data_achado;
	}

	public String getLocal_achado() {
		return local_achado;
	}

	public void setLocal_achado(String local_achado) {
		this.local_achado = local_achado;
	}

	public String getStatus_item() {
	    return status_item;
	}

	public void setStatus_item(String status_item) {
	    this.status_item = status_item; 
	}

	public void setCategoria(String parameter) {
		// TODO Auto-generated method stub
		
	}

	public void setLocal(String parameter) {
		// TODO Auto-generated method stub
		
	}

	public void setDataEncontro(Date valueOf) {
		// TODO Auto-generated method stub
		
	}

	public void setObservacao(String parameter) {
		// TODO Auto-generated method stub
		
	}

	

	
	
	}