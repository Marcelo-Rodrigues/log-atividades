package com.rod.log.model;

public abstract class Tarefa {
	String titulo;
	String palavraChave;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPalavraChave() {
		return palavraChave;
	}
	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}
	
	public abstract String getTipoAtividade();
}
