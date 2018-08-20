package com.rod.log.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.rod.log.model.ApontamentoAtividade;
import com.rod.log.model.EntradaLog;
import com.rod.log.model.TarefaJira;

public class AnaliseAtividades {
	public Collection<ApontamentoAtividade> analisar(List<EntradaLog> entradasLog) {
		HashMap<String, ApontamentoAtividade> apontamentosAtividade = new HashMap<>();
		ApontamentoAtividade apontamentoNaoRelacionado = null;

		for (int indiceAtividade = 0; indiceAtividade < entradasLog.size() - 1; indiceAtividade++) {
			EntradaLog log1 = entradasLog.get(indiceAtividade);
			EntradaLog log2 = entradasLog.get(indiceAtividade + 1);

			long minutos = calcularTempoMinutos(log1, log2);

			if (log2.getTarefaAssociada() == null) {
				if (apontamentoNaoRelacionado == null) {
					apontamentoNaoRelacionado = new ApontamentoAtividade(null, minutos, log2.getLog());
				} else {
					apontamentoNaoRelacionado.addLog(minutos, log2.getLog());
				}
			} else {
				
				if (apontamentosAtividade.containsKey(getIdEntradaLog(log2.getTarefaAssociada()))) {
					ApontamentoAtividade tarefaExistente = apontamentosAtividade.get(getIdEntradaLog(log2.getTarefaAssociada()));
					tarefaExistente.addLog(minutos, log2.getLog());
				} else {
					apontamentosAtividade.put(getIdEntradaLog(log2.getTarefaAssociada()),
							new ApontamentoAtividade(log2.getTarefaAssociada(), minutos, log2.getLog()));
				}
			}

		}
		List<ApontamentoAtividade> todosApontamentos = new ArrayList<>(apontamentosAtividade.values());

		if (apontamentoNaoRelacionado != null)
			todosApontamentos.add(apontamentoNaoRelacionado);

		return todosApontamentos;
	}
	
	private String getIdEntradaLog(TarefaJira tarefa) {
		return String.format("%s [%d]", tarefa.getPalavraChave(),tarefa.getNumeroTarefa());
	}

	private long calcularTempoMinutos(EntradaLog log1, EntradaLog log2) {
		long diffInMillies = log2.getData().getTime() - log1.getData().getTime();
		return TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}
