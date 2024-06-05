package com.desertgm.app.Controller.Leads;

import com.desertgm.app.Services.FileService.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("files")
public class ImportFilesController {
    @Autowired
    FileService fileService;


    @PostMapping("/download-zip/{type}")
    public ResponseEntity downloadZipFilesCompany(@PathVariable("type") String type) throws IOException {
        String url = "https://dadosabertos.rfb.gov.br/CNPJ/" + type;
        fileService.downloadFiles(url,type);
        return ResponseEntity.ok().body("Em processamento");
    }


    //type = Estabelecimento || Socios || Empresas
    @PostMapping("/unzip-files/{type}")
    public ResponseEntity unzipFiles(@PathVariable("type") String type){
        List<String> arrFiles =  fileService.listFiles("D:\\CSV_CNPJ\\" + type);
        arrFiles.forEach((String path)->{
            try {

                fileService.unzip(path,"D:\\CSV_CNPJ\\" + type+"\\CSV\\"    );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return ResponseEntity.ok().body("unzipando");

    }


}
