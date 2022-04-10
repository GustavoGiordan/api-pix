package com.example.demo.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="TB_CHAVE")
public class Chave implements Serializable{

	private static final long serialVersionUID = 1L;

	public Chave(String id, String tipoChave, String valorChave, Cliente idCliente) {
		super();
		this.id = id;
		this.tipoChave = tipoChave;
		this.valorChave = valorChave;
		this.idCliente = idCliente;
		this.dataAlteracao = new Date();
		this.status = true;
	}
	
	public Chave() {};
	
	@Id
	private String id;
	
	private String tipoChave;
	
	private String valorChave;

	@Type(type = "date")
	private Date dataAlteracao;

	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente idCliente;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipoChave() {
		return tipoChave;
	}

	public void setTipoChave(String tipoChave) {
		this.tipoChave = tipoChave;
	}

	public String getValorChave() {
		return valorChave;
	}

	public void setValorChave(String valorChave) {
		this.valorChave = valorChave;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	

}
