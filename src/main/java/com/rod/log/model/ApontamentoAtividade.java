package com.rod.log.model;

import java.util.Optional;

public class ApontamentoAtividade {
	long totalMinutos;
	Optional<Tag> tagAssociada;
	String logSumarizado;

	public ApontamentoAtividade() {
		this.tagAssociada = Optional.empty();
		this.totalMinutos = 0;
		this.logSumarizado = null;
	}
	
	public ApontamentoAtividade(Optional<Tag> tagAssociada, long totalMinutos, String log) {
		this.tagAssociada = tagAssociada;
		this.totalMinutos = totalMinutos;
		this.logSumarizado = log;
	}

	public long getTotalMinutos() {
		return totalMinutos;
	}

	public Optional<Tag> getTagAssociada() {
		// Paliativo para funcionamento de serialização
		if (tagAssociada == null)
			tagAssociada = Optional.empty();
		return tagAssociada;
	}

	public String getLogSumarizado() {
		return logSumarizado;
	}

	public void setTotalMinutos(long totalMinutos) {
		this.totalMinutos = totalMinutos;
	}

	public void setTarefa(Optional<Tag> tagAssociada) {
		this.tagAssociada = tagAssociada;
	}

	public void setLogSumarizado(String logSumarizado) {
		this.logSumarizado = logSumarizado;
	}

	public void addLog(long minutos, String log) {
		this.totalMinutos += minutos;
		logSumarizado = String.format("%s\n%s", logSumarizado, log);
	}
}
