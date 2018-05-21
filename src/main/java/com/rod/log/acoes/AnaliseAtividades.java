package com.rod.log.acoes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.rod.log.Tarefa;

public class AnaliseAtividades {
	public Collection<ApontamentoAtividade> analisar(List<EntradaLog> entradasLog) {
		HashMap<Integer, ApontamentoAtividade> apontamentosAtividade = new HashMap<>();
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
				
				if (apontamentosAtividade.containsKey(log2.getTarefaAssociada().getNumeroTarefa())) {
					ApontamentoAtividade tarefaExistente = apontamentosAtividade.get(log2.getTarefaAssociada().getNumeroTarefa());
					tarefaExistente.addLog(minutos, log2.getLog());
				} else {
					apontamentosAtividade.put(log2.getTarefaAssociada().getNumeroTarefa(),
							new ApontamentoAtividade(log2.getTarefaAssociada(), minutos, log2.getLog()));
				}
			}

		}
		Collection<ApontamentoAtividade> todosApontamentos = apontamentosAtividade.values();

		if (apontamentoNaoRelacionado != null)
			todosApontamentos.add(apontamentoNaoRelacionado);

		return todosApontamentos;
	}

	private long calcularTempoMinutos(EntradaLog log1, EntradaLog log2) {
		long diffInMillies = log2.data.getTime() - log1.data.getTime();
		return TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}
