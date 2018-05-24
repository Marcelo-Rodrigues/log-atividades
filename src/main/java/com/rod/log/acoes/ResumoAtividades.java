package com.rod.log.acoes;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.rod.log.janelas.JanelaResumoDia;
import com.rod.utils.ArquivoLog;

public class ResumoAtividades implements Runnable {
	final int diasReferencia;

	public ResumoAtividades(String[] args) {
		diasReferencia = (args.length > 1) ? Integer.parseInt(args[1]) : 0;
	}

	@Override
	public void run() {
		try {
			StringBuilder strB = new StringBuilder();
			Instant dataReferencia = Instant.now().plus(diasReferencia, ChronoUnit.DAYS);
			strB.append(String.format("Atividades do dia %s:\n",
					DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.systemDefault()).format(dataReferencia)));

			new AnaliseAtividades().analisar(ArquivoLog.obterLogs(Date.from(dataReferencia))).stream().forEach(log -> {
				String descTarefa = log.getTarefa() == null ? "<Sem tarefa no Jira>"
						: String.format("[%d] %s", log.tarefa.getNumeroTarefa(), log.tarefa.getTitulo());

				strB.append(String.format("%s - %s:\n%s\n", descreverTempo(log.getTotalMinutos()), descTarefa,
						log.getLogSumarizado()));
			});
			JanelaResumoDia janela = new JanelaResumoDia();
			janela.setResumoDia(strB.toString());
			janela.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String descreverTempo(long time) {
		long horas = time / 60 % 24;
		long minutos = time % 60;
		return (horas == 0 ? "" : horas + "h ") + (minutos == 0 ? "" : minutos + "m ")
				+ (horas == 0 && minutos == 0 ? "<1min " : "");
	}

}
