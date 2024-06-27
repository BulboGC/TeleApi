package com.desertgm.app.Controller.Import;

import com.desertgm.app.Models.ImportModels.Company;
import com.desertgm.app.Models.ImportModels.Estabelecimento;
import com.desertgm.app.Services.FileService.FileService;
import com.desertgm.app.Services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class CompanyController {
    @Autowired
    FileService fileService;
    /*@Autowired
    GenericService<Company> companyGenericService;

    private AtomicLong taskIdGenerator = new AtomicLong();
    @PostMapping("/company")
    public ResponseEntity<?> addEstabelecimento() {
        String path = "D:\\JavaProjects\\ReceitaAPI\\arquivos\\Empresas\\";
        List<String> directories = fileService.listFiles(path);

        long taskId = taskIdGenerator.incrementAndGet();
        for (String str : directories) {


            fileService.readCsvFile(str, taskId,companyGenericService, Company.class);

        }
        return ResponseEntity.ok().body("aguarde o processamento");
    }*/
}
