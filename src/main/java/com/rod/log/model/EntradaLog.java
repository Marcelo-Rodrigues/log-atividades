package com.rod.log.model;

import java.util.Date;
import java.util.Optional;

public class EntradaLog {
	Date data;
	String log;
	private Optional<Tag> tagAssociada;

	public EntradaLog() {
		this.tagAssociada = Optional.empty();
	}

	public EntradaLog(String log, Optional<Tag> tagAssociada) {
		this.data = new Date();
		this.log = log;
		this.tagAssociada = tagAssociada;
	}

	public Date getData() {
		return data;
	}

	public String getLog() {
		return log;
	}

	public Optional<Tag> getTagAssociada() {
		return tagAssociada;
	}

	public void setTagAssociada(Optional<Tag> tagAssociada) {
		this.tagAssociada = tagAssociada;
	}
}
