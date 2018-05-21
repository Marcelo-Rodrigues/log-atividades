package com.rod.log.acoes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rod.utils.ArquivoLog;

public class ResumoAtividades implements Runnable {
	public ResumoAtividades(String[] args) {
		// Filtrar data
	}

	@Override
	public void run() {
		try {

			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

			new AnaliseAtividades().analisar(ArquivoLog.obterLogs(new Date())).stream().forEach(log -> {
				String descTarefa = log.getTarefa() == null ? "<Sem tarefa no Jira>"
						: String.format("[%d] %s", log.tarefa.getNumeroTarefa(), log.tarefa.getTitulo());

				System.out.println(String.format("%d min - %s:\n%s", log.getTotalMinutos(), descTarefa, log.getLogSumarizado()));
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
