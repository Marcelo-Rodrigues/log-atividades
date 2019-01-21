package com.rod.log.model;

public class Tag {
	private String tag;
	private String descricao;
	
	public Tag() {
		this.tag = null;
		this.descricao = null;
	}
	
	public Tag(String tag, String descricao) {
		this.tag = tag;
		this.descricao = descricao;
	}
	
	public Tag(String tag) {
		this.tag = tag;
		this.descricao = null;
	}
	
	public String getTag() {
		return tag;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public boolean possuiDescricao() {
		return this.descricao != null && !this.descricao.trim().equals("");
	}
	
	@Override
	public String toString() {
		return possuiDescricao() ? String.format("%s - %s", getTag(), getDescricao()) : getTag(); 
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
