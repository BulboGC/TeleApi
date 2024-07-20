package com.desertgm.app.Controller.Import;

import com.desertgm.app.DTO.Imports.ResponseEstabelecimentoDto;
import com.desertgm.app.DTO.NewResponseDto;
import com.desertgm.app.DTO.ResponsePageble;
import com.desertgm.app.Models.ImportModels.Estabelecimento.*;
import com.desertgm.app.Models.Leads.Lead;

import com.desertgm.app.Services.FileService.FileService;
import com.desertgm.app.Services.Imports.Estabelecimento.*;
import com.desertgm.app.Services.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@CrossOrigin(origins = "*")
public class EstabelecimentoController {

    @Autowired
    private FileService fileService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private LeadService leadService;

    @Autowired
    private CnaeService cnaeService;

    @Autowired
    private MotivoSCService motivoSCService;

    @Autowired
    private PaisService paisService;
    @Autowired
    private MunicipioService municipioService;



    private AtomicLong taskIdGenerator = new AtomicLong();

    @GetMapping("/estabelecimento")
    public ResponseEntity<?> addEstabelecimento() {


        String path = "/mnt/d/CSV_CNPJ/Estabelecimentos" ;

        List<String> directories = fileService.listFiles(path);

        long taskId = taskIdGenerator.incrementAndGet();
        for (String str : directories) {
            fileService.readTxtFile(str,taskId,estabelecimentoService,Estabelecimento.class);
        }

        return ResponseEntity.ok().body("aguarde o processamento");
    }

    @PostMapping("/cnae")
    public ResponseEntity<NewResponseDto> addCnae(){
        long taskId = taskIdGenerator.incrementAndGet();
        String path = "/mnt/d/JavaProjects/ReceitaAPI/arquivos/Cnaes/F.K03200$Z.D40413.CNAECSV";
        fileService.readTxtFile(path,taskId,cnaeService,Cnae.class);
        NewResponseDto responseDto = new NewResponseDto("aguarde o processamento de arquivos","OK",null);
        return ResponseEntity.ok().body(responseDto);
    }
    @PostMapping("/MotivoSC")
    public ResponseEntity<NewResponseDto> addMotivoSC(){
        long taskId = taskIdGenerator.incrementAndGet();
        String path = "/mnt/d/JavaProjects/ReceitaAPI/arquivos/Motivos/F.K03200$Z.D40413.MOTICSV";
        fileService.readTxtFile(path,taskId,motivoSCService, MotivoSC.class);
        NewResponseDto responseDto = new NewResponseDto("aguarde o processamento de arquivos","OK",null);
        return ResponseEntity.ok().body(responseDto);
    }
    @PostMapping("/Pais")
    public ResponseEntity<NewResponseDto> addPais(){
        long taskId = taskIdGenerator.incrementAndGet();
        String path = "/mnt/d/JavaProjects/ReceitaAPI/arquivos/Paises/F.K03200$Z.D40413.PAISCSV";
        fileService.readTxtFile(path,taskId,paisService, Pais.class);
        NewResponseDto responseDto = new NewResponseDto("aguarde o processamento de arquivos","OK",null);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/Municipio")
    public ResponseEntity<NewResponseDto> addMunicipio(){
        long taskId = taskIdGenerator.incrementAndGet();
        String path = "/mnt/d/JavaProjects/ReceitaAPI/arquivos/Municipios/F.K03200$Z.D40413.MUNICCSV";
        fileService.readTxtFile(path,taskId,municipioService, Municipio.class);
        NewResponseDto responseDto = new NewResponseDto("aguarde o processamento de arquivos","OK",null);
        return ResponseEntity.ok().body(responseDto);
    }


    @PostMapping("/estabelecimento/{cnae}")
    public ResponseEntity<NewResponseDto> getLeadsByCnae(@PathVariable int cnae){

       List<Estabelecimento> list =  estabelecimentoService.findByCnae(cnae);
       List<Lead> leadList  = estabelecimentoService.trasformInLead(list);
       leadService.addLeadList(leadList);
       NewResponseDto responseDto = new NewResponseDto("Leads salvos com sucesso", "OK",null);
       return ResponseEntity.ok().body(responseDto);
    }


    @GetMapping("/estabelecimento/{cnae}")
    public ResponseEntity<?> getMethodName(
        @PathVariable int cnae,
        @RequestParam int page) {

        Page<Estabelecimento> ePage = estabelecimentoService.findByCnaePageble(cnae, page);
        CompletableFuture<List<ResponseEstabelecimentoDto>> futureDtoList = estabelecimentoService.transformEstabelecimentoToResponseDto(ePage.getContent());
        try{
            List<ResponseEstabelecimentoDto> responseEstabelecimentoDtos = futureDtoList.get();
            ResponsePageble responseDto = new ResponsePageble(
                    "transação realizada.",
                    "OK",
                    ePage.getNumber(),
                    ePage.getNumberOfElements(),
                    ePage.getTotalPages(),
                    responseEstabelecimentoDtos
            );
            return ResponseEntity.ok().body(responseDto);

        }catch (Exception e){
            NewResponseDto responseDto = new NewResponseDto("erro interno","BADREQUEST",e);
           return ResponseEntity.badRequest().body(responseDto);
        }

    }

    @GetMapping("estabelecimento/filter/")
    public ResponseEntity<?> getFilteredEstabelecimento(
            @RequestParam(required = false) String cnpjBase,
            @RequestParam(required = false) Integer cnae,
            @RequestParam(required = false) String nomeFantasia,
            @RequestParam(required = false) Integer municipio,
            @RequestParam(required = false) Integer situacaoCadastral,
            @RequestParam(required = true) Integer page,
            @RequestParam(required = true) Integer size
            ){

        var response =  estabelecimentoService.filterEstabelecimento(cnpjBase,cnae,nomeFantasia,municipio,situacaoCadastral,page,size);

        return ResponseEntity.ok().body(response);
    }


}

