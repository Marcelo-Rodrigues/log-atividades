package com.rod.log;

public class TarefaJira extends Tarefa {
	int numeroTarefa;
	final String tipoAtividade = "Jira";

	public int getNumeroTarefa() {
		return numeroTarefa;
	}

	public void setNumeroTarefa(int numeroTarefa) {
		this.numeroTarefa = numeroTarefa;
	}

	@Override
	public String getTipoAtividade() {
		return tipoAtividade;
	}
}
