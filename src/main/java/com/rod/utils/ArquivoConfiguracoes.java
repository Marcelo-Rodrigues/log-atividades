package com.rod.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.rod.log.model.Configuracoes;
import com.rod.log.model.Tag;

public class ArquivoConfiguracoes {
	private static Configuracoes config = null;
	private static final String NOME_ARQUIVO = "atividadesJira.json";
	private static final Path DIRETORIO_ARQUIVO = Paths.get(NOME_ARQUIVO);

	public static Configuracoes obterConfiguracoes() throws FileNotFoundException, IOException {
		if (config == null) {
			lerConfig();
		}
		return config;
	}

	public static List<Tag> obterTarefas() throws FileNotFoundException, IOException {
		return obterConfiguracoes().getTagsFixas();
	}

	public static void salvar(Configuracoes configuracoes) throws IOException {

		Files.write(DIRETORIO_ARQUIVO, Arrays.asList(Serializacao.serializar(configuracoes)), StandardCharsets.UTF_8,
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING );
	}

	public static void salvarTarefas(List<Tag> novasTarefas) throws IOException {
		config = new Configuracoes();
		config.setTagsFixas(novasTarefas);
		salvar(config);
	}

	public static void lerConfig() throws FileNotFoundException, IOException {
		if (Files.exists(DIRETORIO_ARQUIVO)) {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(NOME_ARQUIVO), "UTF-8"));
			Gson gson = new Gson();
			config = gson.fromJson(bufferedReader, Configuracoes.class);
		}

		if (config == null) {
			config = new Configuracoes();
			config.setTagsFixas(new ArrayList<Tag>());
		}
	}
}
