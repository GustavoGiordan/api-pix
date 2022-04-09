package com.example.demo.models;

public class Saida {

	private String mensagem;
	
	private int httpCode;

	public void cadastrar(String msg, int httpCode){
		this.mensagem = msg;
		this.httpCode = httpCode;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}
	
}
