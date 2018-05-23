package com.rod.log;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.rod.log.acoes.GravacaoLog;
import com.rod.log.acoes.ResumoAtividades;

public class Fabrica {
	List<Function<String[], Runnable>> acoes;

	public Fabrica() {
		inicializarAcoes();
	}

	private void inicializarAcoes() {
		acoes = new ArrayList<>();
		acoes.add(args -> args.length == 0 ? new GravacaoLog() : null);
		acoes.add(args -> (args.length >= 1 && args.length <= 2) && "analisar".equals(args[0])
				? new ResumoAtividades(args) : null);
	}

	public Runnable criar(String[] args) {
		for (Function<String[], Runnable> acao : acoes) {
			Runnable executor = acao.apply(args);
			if (executor != null)
				return executor;
		}
		throw new InvalidParameterException("Parametro invalido");
	}
}
