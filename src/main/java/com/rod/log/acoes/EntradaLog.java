package com.rod.log.acoes;

import java.util.Date;

import com.rod.log.Tarefa;

public class EntradaLog {
	public EntradaLog(String log, Tarefa tarefaAssociada) {
		this.data = new Date();
		this.log = log;
		this.tarefaAssociada = tarefaAssociada;
	}
	
	Date data;
	String log;
	Tarefa tarefaAssociada;
	public Date getData() {
		return data;
	}
	public String getLog() {
		return log;
	}
	public Tarefa getTarefaAssociada() {
		return tarefaAssociada;
	}
}
