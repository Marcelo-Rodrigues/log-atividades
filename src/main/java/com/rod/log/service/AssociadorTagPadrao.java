package com.rod.log.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rod.log.model.Tag;

public class AssociadorTagPadrao implements AssociadorTag {
	private final Pattern patternTag;
	private List<Tag> tagsFixas;

	public AssociadorTagPadrao(List<Tag> tagsFixas) {
		this.tagsFixas = tagsFixas;
		patternTag = Pattern.compile("@[\\w-]+");
	}

	private Optional<Tag> procurarTagFixa(String textoTag) {
		return tagsFixas.stream().filter(tag -> tag.getTag().equals(textoTag)).findFirst();
	}

	@Override
	public Optional<Tag> procurarTagRelacionada(String descricaoAtividade) {
		Matcher matcherTagLog = patternTag.matcher(descricaoAtividade);
		
		if (matcherTagLog.find()) {
			String textoTag = descricaoAtividade.substring(matcherTagLog.start(), matcherTagLog.end());
			Optional<Tag> tagFixaEncontrada = procurarTagFixa(textoTag);
			return Optional.of(tagFixaEncontrada.isPresent() ? tagFixaEncontrada.get() : new Tag(textoTag));
			
		} else {
			return Optional.empty();
		}
	}

}
