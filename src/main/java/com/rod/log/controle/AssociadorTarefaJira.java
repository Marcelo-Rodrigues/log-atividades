package com.rod.log.controle;

import java.util.List;
import java.util.stream.Collectors;

import com.rod.log.Tarefa;
import com.rod.log.TarefaJira;

public class AssociadorTarefaJira implements AssociadorTarefa {
	final List<TarefaJira> tarefasJira;

	public AssociadorTarefaJira(List<TarefaJira> tarefasJira) {
		this.tarefasJira = tarefasJira;
	}
	
	/* (non-Javadoc)
	 * @see com.rod.log.controle.AssociadorTarefas#procurarTarefasRelacionadas(java.lang.String, boolean)
	 */
	@Override
	public List<TarefaJira> procurarTarefasRelacionadas(String descricaoAtividade, boolean caseSensitive) {
		String comparacao = caseSensitive ? descricaoAtividade : descricaoAtividade.toLowerCase();
		
		return tarefasJira.stream().filter(tarefa->
		comparacao.contains(caseSensitive ? tarefa.getPalavraChave() : tarefa.getPalavraChave().toLowerCase()))
				.collect(Collectors.toList());
	}
}
