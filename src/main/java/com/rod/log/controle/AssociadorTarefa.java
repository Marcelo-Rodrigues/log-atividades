package com.rod.log.controle;

import java.util.List;

import com.rod.log.Tarefa;

public interface AssociadorTarefa {

	List<Tarefa> procurarTarefasRelacionadas(String descricaoAtividade, boolean caseSensitive);

}