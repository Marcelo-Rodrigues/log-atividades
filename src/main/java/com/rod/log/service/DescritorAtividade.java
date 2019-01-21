package com.rod.log.service;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import com.rod.utils.ArquivoLog;

public class DescritorAtividade {
	private final AnaliseAtividades analisadorAtividades;

	public DescritorAtividade(AnaliseAtividades analisadorAtividades) {
		this.analisadorAtividades = analisadorAtividades;
	}

	public String obterDetalhes(Instant dataReferencia) throws IOException {
		StringBuilder strB = new StringBuilder();

		analisadorAtividades.analisar(ArquivoLog.obterLogs(Date.from(dataReferencia))).stream().forEach(log -> {
			String descTarefa = log.getTagAssociada().isPresent() ? log.getTagAssociada().get().toString()
					: "<Sem tag associada>";

			strB.append(String.format("%s - %s:\n%s\n", descreverTempo(log.getTotalMinutos()), descTarefa,
					log.getLogSumarizado()));
		});
		return strB.toString();
	}

	private String descreverTempo(long time) {
		long horas = time / 60 % 24;
		long minutos = time % 60;
		return (horas == 0 ? "" : horas + "h ") + (minutos == 0 ? "" : minutos + "m ")
				+ (horas == 0 && minutos == 0 ? "<1min " : "");
	}
}
