package com.desertgm.app.Services.FileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class FileSocioService {
    private final ConcurrentHashMap<Long, String> taskStatusMap = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(FileSocioService.class);
/*
    @Async
    public void readCsvFile(String csvFile, Long taskId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        int batchSize = 10000;
        int linecount = 0;
        List<SocioModel> batchList = new ArrayList<>(batchSize);

        logger.info("Iniciando leitura do arquivo CSV: {}", csvFile);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            CSVReader reader = new CSVReaderBuilder(br)
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                    .build();
            taskStatusMap.put(taskId, "Processando");



            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                linecount++;


                SocioModel model = socioService.parseLine(nextLine, format);

                if (model != null) {
                    batchList.add(model);
                }

                if (batchList.size() >= batchSize) {
                    socioService.saveAll(batchList);
                    logger.info("Linhas processadas: {} - Memória utilizada: {} bytes", linecount, Runtime.getRuntime().totalMemory());
                    batchList.clear();
                }
            }

            // Salvar qualquer restante
            if (!batchList.isEmpty()) {
                socioService.saveAll(batchList);
                logger.info("Linhas processadas (restante): {} - Memória utilizada: {} bytes", linecount, Runtime.getRuntime().totalMemory());
            }

            taskStatusMap.put(taskId, "Concluído");
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            taskStatusMap.put(taskId, "Erro: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            logger.error("Erro de memória após processar {} linhas", linecount);
            taskStatusMap.put(taskId, "Erro: Out of Memory");
        }
    }

 */
}
