package com.rod.log.model;

public class ApontamentoAtividade {
	long totalMinutos;
	TarefaJira tarefa;
	String logSumarizado;

	public ApontamentoAtividade() {
		
	}
	
	public ApontamentoAtividade(TarefaJira tarefa, long totalMinutos, String log) {
		this.tarefa = tarefa;
		this.totalMinutos = totalMinutos;
		this.logSumarizado = log;
	}

	public long getTotalMinutos() {
		return totalMinutos;
	}

	public TarefaJira getTarefa() {
		return tarefa;
	}

	public String getLogSumarizado() {
		return logSumarizado;
	}

	public void setTotalMinutos(long totalMinutos) {
		this.totalMinutos = totalMinutos;
	}

	public void setTarefa(TarefaJira tarefa) {
		this.tarefa = tarefa;
	}

	public void setLogSumarizado(String logSumarizado) {
		this.logSumarizado = logSumarizado;
	}

	public void addLog(long minutos, String log) {
		this.totalMinutos += minutos;
		logSumarizado = String.format("%s\n%s", logSumarizado, log);
	}
}
