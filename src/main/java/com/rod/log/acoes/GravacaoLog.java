package com.rod.log.acoes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import com.rod.log.controle.AssociadorTarefaJira;
import com.rod.log.janelas.JanelaConfiguracoes;
import com.rod.log.janelas.JanelaLog;
import com.rod.utils.ArquivoConfiguracoes;
import com.rod.utils.ArquivoLog;
import com.rod.utils.Serializacao;

public class GravacaoLog implements Runnable {
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
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
