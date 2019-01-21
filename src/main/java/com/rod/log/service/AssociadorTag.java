package com.rod.log.service;

import java.util.Optional;

import com.rod.log.model.Tag;

public interface AssociadorTag {

	Optional<Tag> procurarTagRelacionada(String descricaoAtividade);

}