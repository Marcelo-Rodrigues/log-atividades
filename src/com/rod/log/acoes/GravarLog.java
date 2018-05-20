package com.rod.log.acoes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.rod.log.TarefaJira;
import com.rod.log.controle.AssociadorTarefaJira;
import com.rod.log.janelas.JanelaLog;

public class GravarLog implements Runnable {
	public void run() {
		List<TarefaJira> tarefasJira = new ArrayList<>();
				
		TarefaJira overHead = new TarefaJira();
		overHead.setTitulo("[INCDV-111] Overhead");
		overHead.setDescricao("Overhead da iteração x");
		overHead.setPalavraChave("overhead");
		tarefasJira.add(overHead);
		
		JanelaLog janela = new JanelaLog(new AssociadorTarefaJira(tarefasJira));
		
		janela.addConfigurarListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Configurar");
			}
		});
		
		janela.addSalvarListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Salvar");
				System.out.println(janela.obterLog());
				janela.fecharJanela();
			}
		});
		janela.exibir();
	}
}
