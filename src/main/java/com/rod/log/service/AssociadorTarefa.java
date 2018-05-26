package com.rod.log.service;

import java.util.List;

import com.rod.log.model.TarefaJira;

public interface AssociadorTarefa {

	List<TarefaJira> procurarTarefasRelacionadas(String descricaoAtividade, boolean caseSensitive);

}