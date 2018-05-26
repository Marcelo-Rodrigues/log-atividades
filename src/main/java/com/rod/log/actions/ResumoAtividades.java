package com.rod.log.actions;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;

import com.rod.log.ui.JanelaResumoDia;

public class ResumoAtividades implements Runnable {
	final int diasReferencia;

	public static Function<String[], Runnable> obterInicializador() {
		return args -> (args.length >= 1 && args.length <= 2) && "analisar".equals(args[0]) ? new ResumoAtividades(args)
				: null;
	}

	public ResumoAtividades() {
		diasReferencia = 0;
	}

	public ResumoAtividades(String[] args) {
		diasReferencia = (args.length > 1) ? Integer.parseInt(args[1]) : 0;
	}

	@Override
	public void run() {
		try {
			exibirResumoDia(diasReferencia);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void exibirResumoDia(int diasReferencia) throws IOException {
		Instant dataReferencia = Instant.now().plus(diasReferencia, ChronoUnit.DAYS);
		exibirResumoDia(dataReferencia);
	}

	public void exibirResumoDia(Instant dataReferencia) throws IOException {
		JanelaResumoDia janela = new JanelaResumoDia(Date.from(dataReferencia));
		janela.setVisible(true);
	}

}
