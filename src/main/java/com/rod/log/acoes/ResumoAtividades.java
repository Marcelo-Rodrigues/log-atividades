package com.rod.log.acoes;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.rod.utils.ArquivoLog;

public class ResumoAtividades implements Runnable {
	final int diasReferencia;

	public ResumoAtividades(String[] args) {
		diasReferencia = (args.length > 1) ? Integer.parseInt(args[1]) : 0;
	}

	@Override
	public void run() {
		try {
			new Date();
			new AnaliseAtividades().analisar(ArquivoLog.obterLogs(Date.from(Instant.now().plus(diasReferencia, ChronoUnit.DAYS))))
					.stream().forEach(log -> {
						String descTarefa = log.getTarefa() == null ? "<Sem tarefa no Jira>"
								: String.format("[%d] %s", log.tarefa.getNumeroTarefa(), log.tarefa.getTitulo());

						System.out.println(String.format("%d min - %s:\n%s", log.getTotalMinutos(), descTarefa,
								log.getLogSumarizado()));
					});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
