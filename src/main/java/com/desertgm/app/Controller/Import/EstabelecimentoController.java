package com.desertgm.app.Controller.Import;

import com.desertgm.app.Models.ImportModels.Estabelecimento;
import com.desertgm.app.Models.Leads.Socio;
import com.desertgm.app.Services.FileService.FileService;
import com.desertgm.app.Services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class EstabelecimentoController {
    @Autowired
    FileService fileService;
    @Autowired
    GenericService<Estabelecimento> estabelecimentoGenericService;

    private AtomicLong taskIdGenerator = new AtomicLong();
    @PostMapping("/estabelecimento")
    public ResponseEntity<?> addEstabelecimento() {
        String path = "D:\\JavaProjects\\ReceitaAPI\\arquivos\\Estabelecimento\\estabelecimentoscsv";
        List<String> directories = fileService.listFiles(path);

        long taskId = taskIdGenerator.incrementAndGet();
        for (String str : directories) {


            fileService.readCsvFile(str, taskId,estabelecimentoGenericService, Estabelecimento.class);

        }
    return ResponseEntity.ok().body("aguarde o processamento");
    }
}

