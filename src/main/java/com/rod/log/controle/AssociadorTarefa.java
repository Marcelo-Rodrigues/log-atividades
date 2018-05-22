package com.rod.log.controle;

import java.util.List;

import com.rod.log.TarefaJira;

public interface AssociadorTarefa {

	List<TarefaJira> procurarTarefasRelacionadas(String descricaoAtividade, boolean caseSensitive);

}