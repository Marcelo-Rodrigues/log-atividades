package com.rod.log.acoes;

import java.util.Date;

import com.rod.log.TarefaJira;

public class EntradaLog {
	Date data;
	String log;
	TarefaJira tarefaAssociada;
	
	public EntradaLog(String log, TarefaJira tarefaAssociada) {
		this.data = new Date();
		this.log = log;
		this.tarefaAssociada = tarefaAssociada;
	}
	
	public Date getData() {
		return data;
	}
	public String getLog() {
		return log;
	}
	public TarefaJira getTarefaAssociada() {
		return tarefaAssociada;
	}
}
