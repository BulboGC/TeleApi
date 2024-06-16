package com.desertgm.app.Controller.Leads;

import com.desertgm.app.Services.FileService.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


public class CompanyController {
    /*
    @Autowired
    FileService fileService;
    @PostMapping("/Company-files")
    public ResponseEntity downloadFiles() throws IOException {
        String url = "https://dadosabertos.rfb.gov.br/CNPJ/Empresas";
        fileService.downloadFiles(url,"Empresas");
        return ResponseEntity.ok().body("Em processamento");
    }
*/
}
