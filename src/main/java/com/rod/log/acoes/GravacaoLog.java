package com.rod.log.acoes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.rod.log.TarefaJira;
import com.rod.log.controle.AssociadorTarefaJira;
import com.rod.log.janelas.JanelaLog;

public class GravacaoLog implements Runnable {
	public void run() {
		List<TarefaJira> tarefasJira = new ArrayList<>();

		TarefaJira overHead = new TarefaJira();
		overHead.setTitulo("[INCDV-111] Overhead");
		overHead.setDescricao("Overhead da iteração x");
		overHead.setPalavraChave("overhead");
		overHead.setNumeroTarefa(111);
		tarefasJira.add(overHead);
		
		TarefaJira daily = new TarefaJira();
		daily.setTitulo("[INCDV-112] Daily");
		daily.setDescricao("Daily e alinhamentos");
		daily.setPalavraChave("daily");
		daily.setNumeroTarefa(112);
		tarefasJira.add(daily);
		
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
				try {
				    final Path path = Paths.get("logAtividades.json");
				    Files.write(path, Arrays.asList(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create().toJson(janela.obterLog())), StandardCharsets.UTF_8,
				        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
				}catch (IOException ex) {
					ex.printStackTrace();
				}

				janela.fecharJanela();
			}
		});
		janela.exibir();
	}
}
