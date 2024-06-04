package com.desertgm.app.Services.FileService;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service

public class FileService {
    private static final int BUFFER_SIZE = 4096;
    @Async
    public void downloadFiles(String baseurl,String type) throws IOException {

        switch (type){
            case "Empresas":
                for (int i = 0; i <= 9; i++) {
                    String url = baseurl + i + ".zip";
                    String fileName = type + i + ".zip";
                    downloadFile(url, "D:\\CSV_CNPJ\\Empresas\\" + fileName);
                }
            case "Estabelecimentos":
                for (int i = 0; i <= 9; i++) {
                    String url = baseurl + i + ".zip";
                    String fileName = type + i + ".zip";
                    downloadFile(url, "D:\\CSV_CNPJ\\Estabelecimentos\\" + fileName);
                }
            case "Socios":
                for (int i = 0; i <= 9; i++) {
                    String url = baseurl + i + ".zip";
                    String fileName = type + i + ".zip";
                    downloadFile(url, "D:\\CSV_CNPJ\\Socios\\" + fileName);
                }
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
