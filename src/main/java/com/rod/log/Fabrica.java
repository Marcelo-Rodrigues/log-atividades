package com.rod.log;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.rod.log.actions.GravacaoLog;
import com.rod.log.actions.ResumoAtividades;

public class Fabrica {
	List<Function<String[], Runnable>> acoes;

	public Fabrica() {
		inicializarAcoes();
	}

	private void inicializarAcoes() {
		acoes = new ArrayList<>();
		acoes.add(GravacaoLog.obterInicializador());
		acoes.add(ResumoAtividades.obterInicializador());
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
