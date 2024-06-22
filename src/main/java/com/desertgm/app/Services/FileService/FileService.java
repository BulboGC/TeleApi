package com.desertgm.app.Services.FileService;

import com.desertgm.app.Services.GenericService;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileService {

    private static final int BUFFER_SIZE = 4096;
    private final ConcurrentHashMap<Long, String> taskStatusMap = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Async
    public <T> void readCsvFile(String csvFile, Long taskId, GenericService<T> service, Class<T> clazz) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        int batchSize = 10000;
        int linecount = 0;
        List<T> batchList = new ArrayList<>(batchSize);

        logger.info("Iniciando leitura do arquivo CSV: {}", csvFile);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            CSVReader reader = new CSVReaderBuilder(br)
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                    .build();
            taskStatusMap.put(taskId, "Processando");

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                linecount++;

                if (nextLine.length == 0) {
                    logger.warn("Linha inválida na contagem {}: {}", linecount, Arrays.toString(nextLine));
                    continue; // Pula linhas inválidas
                }

                T model = service.parseLine(nextLine, format);

                if (model != null) {
                    batchList.add(model);
                }

                if (batchList.size() >= batchSize) {
                    service.saveAll(batchList);
                    logger.info("Linhas processadas: {} - Memória utilizada: {} bytes", linecount, Runtime.getRuntime().totalMemory());
                    batchList.clear();
                    System.gc();
                }
            }

            if (!batchList.isEmpty()) {
                service.saveAll(batchList);
                logger.info("Linhas processadas (restante): {} - Memória utilizada: {} bytes", linecount, Runtime.getRuntime().totalMemory());
            }
            taskStatusMap.put(taskId, "Concluído");
        } catch (CsvValidationException e) {
            logger.warn("Erro na linha {}: {}", linecount, e.getMessage());
        } catch (Exception e) {
            logger.warn("Erro ao processar linha {}", linecount, e);
        }
    }


    public void downloadFiles(String baseurl, String type) throws IOException {
        String basePath = "/media/desert/HDD/csv_teste/";  // Substitua "username" pelo seu nome de usuário no Ubuntu
        Files.createDirectories(Paths.get(basePath + type));

        switch (type) {
            case "Empresas":
                for (int i = 0; i <= 9; i++) {
                    String url = baseurl + i + ".zip";
                    String fileName = type + i + ".zip";
                    downloadFile(url, basePath + "Empresas/" + fileName);
                }
                break;
            case "Estabelecimentos":
                for (int i = 0; i <= 9; i++) {
                    String url = baseurl + i + ".zip";
                    String fileName = type + i + ".zip";
                    downloadFile(url, basePath + "Estabelecimentos/" + fileName);
                }
                break;
            case "Socios":
                for (int i = 0; i <= 9; i++) {
                    String url = baseurl + i + ".zip";
                    String fileName = type + i + ".zip";
                    downloadFile(url, basePath + "Socios/" + fileName);
                }
                break;
        }
    }

    @Async
    private static void downloadFile(String fileUrl, String destination) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(destination)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    /*  lista os arquivos de um diretorio */

    public List<String> listFiles(String directory) {
        List<String> filePaths = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(directory))) {
            filePaths = paths
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePaths;
    }

    @Async
    public void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String filePath = destDirectory + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    extractFile(zipIn, filePath);
                } else {
                    File dir = new File(filePath);
                    dir.mkdirs();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }
}
