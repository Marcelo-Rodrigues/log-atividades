package com.rod.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.rod.log.model.EntradaLog;

public class ArquivoLog {
	private static final String NOME_ARQUIVO_LOG = "logAtividades.json";
	private static final Path DIRETORIO_ARQUIVO_LOG = Paths.get(NOME_ARQUIVO_LOG);

	public static List<EntradaLog> obterLogs(Date dataReferencia) throws IOException {
		return Files.readAllLines(DIRETORIO_ARQUIVO_LOG, StandardCharsets.UTF_8).stream()
				.map(linha->Serializacao.deSerializar(linha, EntradaLog.class))
				.filter(entrada->mesmoDia(entrada.getData(), dataReferencia))
				.collect(Collectors.toList());
	}

	private static boolean mesmoDia(Date data1, Date data2) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		return fmt.format(data1).equals(fmt.format(data2));
	}

	public static void gravarLog(EntradaLog log) throws IOException {
		final Path path = Paths.get(NOME_ARQUIVO_LOG);
		Files.write(path, Arrays.asList(Serializacao.serializar(log)), StandardCharsets.UTF_8,
				Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
	}
}
