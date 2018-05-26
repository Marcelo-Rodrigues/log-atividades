package com.rod.log.acoes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Function;

import com.rod.log.controle.AssociadorTarefaJira;
import com.rod.log.janelas.JanelaConfiguracoes;
import com.rod.log.janelas.JanelaLog;
import com.rod.utils.ArquivoConfiguracoes;
import com.rod.utils.ArquivoLog;

public class GravacaoLog implements Runnable {
	public static Function<String[], Runnable> obterInicializador() {
		return args -> args.length == 0 ? new GravacaoLog() : null;
	}
	
	public void run() {

		JanelaLog janela;
		try {
			janela = new JanelaLog(new AssociadorTarefaJira(ArquivoConfiguracoes.obterTarefas()));

			janela.addConfigurarListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JanelaConfiguracoes janela;
					try {
						janela = new JanelaConfiguracoes();
						janela.exibir();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});

			janela.addSalvarListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						ArquivoLog.gravarLog(janela.obterLog());
					} catch (IOException ex) {
						ex.printStackTrace();
					}

					janela.fecharJanela();
				}

			});
			janela.exibir();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
