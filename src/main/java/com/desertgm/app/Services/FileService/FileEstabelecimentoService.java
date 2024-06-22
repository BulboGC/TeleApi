package com.desertgm.app.Services.FileService;

import com.desertgm.app.Models.ImportModels.Estabelecimento;
import com.desertgm.app.Services.Imports.EstabelecimentoService;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvMalformedLineException;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FileEstabelecimentoService {
    @Autowired
    EstabelecimentoService estabelecimentoService;

    private final ConcurrentHashMap<Long, String> taskStatusMap = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(FileEstabelecimentoService.class);

    @Async
    public void readCsvFile(String csvFile, Long taskId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        int batchSize = 10000;
        int linecount = 0;
        List<Estabelecimento> batchList = new ArrayList<>(batchSize);

        logger.info("Iniciando leitura do arquivo CSV: {}", csvFile);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            CSVReader reader = new CSVReaderBuilder(br)
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                    .build();
            taskStatusMap.put(taskId, "Processando");

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                linecount++;

                try {
                    if (nextLine.length < 30) {
                        logger.warn("Linha inválida na contagem {}: {}", linecount, Arrays.toString(nextLine));
                        continue; // Pula linhas inválidas
                    }

                    Estabelecimento model = estabelecimentoService.parseLine(nextLine, format);

                    if (model != null) {
                        batchList.add(model);
                    }

                    if (batchList.size() >= batchSize) {
                        estabelecimentoService.saveAll(batchList);
                        logger.info("Linhas processadas: {} - Memória utilizada: {} bytes", linecount, Runtime.getRuntime().totalMemory());
                        batchList.clear();
                        System.gc();
                    }

                } catch (Exception e) {
                    logger.warn("Erro ao processar linha {}: {}", linecount, Arrays.toString(nextLine), e);
                }
            }

            // Salvar qualquer batch restante
            if (!batchList.isEmpty()) {
                estabelecimentoService.saveAll(batchList);
                logger.info("Linhas processadas: {} - Memória utilizada: {} bytes", linecount, Runtime.getRuntime().totalMemory());
                batchList.clear();
            }

        } catch (IOException | CsvValidationException e) {
            logger.error("Erro ao ler o arquivo CSV {}: {}", csvFile, e.getMessage());
        }
    }

}