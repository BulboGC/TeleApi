package com.desertgm.app.Controller.Import;

import com.desertgm.app.Models.ImportModels.Estabelecimento;
import com.desertgm.app.Models.ImportModels.Socios;
import com.desertgm.app.Models.Leads.Lead;
import com.desertgm.app.Models.Leads.Socio;
import com.desertgm.app.Services.FileService.FileService;
import com.desertgm.app.Services.GenericService;
import com.desertgm.app.Services.Imports.SociosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class SociosController {
    @Autowired
    FileService fileService;
   @Autowired
    GenericService<Socios> socioService;
    private AtomicLong taskIdGenerator = new AtomicLong();
    //teste
    @PostMapping("/socios")
    public ResponseEntity<?> addEstabelecimento() {
        String path = "D:\\JavaProjects\\ReceitaAPI\\arquivos\\Socios\\";
        List<String> directories = fileService.listFiles(path);

        long taskId = taskIdGenerator.incrementAndGet();
        for (String str : directories) {


            fileService.readCsvFile(str, taskId,socioService, Socios.class);

        }

        return ResponseEntity.ok().body("Tarefa iniciada com ID: " + taskId);
    }

}
