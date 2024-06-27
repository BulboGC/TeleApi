package com.desertgm.app.Controller.Import;

import com.desertgm.app.DTO.NewResponseDto;
import com.desertgm.app.Models.ImportModels.Estabelecimento;
import com.desertgm.app.Models.Leads.Lead;
import com.desertgm.app.Models.Leads.Socio;
import com.desertgm.app.Services.FileService.FileEstabelecimentoService;
import com.desertgm.app.Services.FileService.FileService;
import com.desertgm.app.Services.GenericService;
import com.desertgm.app.Services.Imports.EstabelecimentoService;
import com.desertgm.app.Services.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin(origins = "*")
public class EstabelecimentoController {
    @Autowired
    FileService fileService;
    @Autowired
    EstabelecimentoService estabelecimentoService;
    @Autowired
    LeadService leadService;


   /* private AtomicLong taskIdGenerator = new AtomicLong();
    @PostMapping("/estabelecimento/{id}")
    public ResponseEntity<?> addEstabelecimento(@PathVariable String id) {
        String path = "/media/desert/HDD/CSV_CNPJ/Estabelecimentos/K3241.K03200Y" + id + ".D40511.ESTABELE";

        //List<String> directories = fileService.listFiles(path);

        long taskId = taskIdGenerator.incrementAndGet();
        /*for (String str : directories) {
        }*/
   //         fileEstabelecimentoService.readCsvFile(path,taskId);


       // fileEstabelecimentoService.readCsvFile("/media/desert/HDD/CSV_CNPJ/Estabelecimentos/K3241.K03200Y0.D40511.ESTABELE",taskId);
    //return ResponseEntity.ok().body("aguarde o processamento");
    //}

    @PostMapping("/estabelecimento/{cnae}")
    public ResponseEntity<NewResponseDto> getLeadsByCnae(@PathVariable Long cnae){
       List<Estabelecimento> list =  estabelecimentoService.findByCnae(cnae);
       List<Lead> leadList  = estabelecimentoService.trasformInLead(list);
       leadService.addLeadList(leadList);
       NewResponseDto responseDto = new NewResponseDto("Leads salvos com sucesso", "OK",null);
       return ResponseEntity.ok().body(responseDto);
        }





}

