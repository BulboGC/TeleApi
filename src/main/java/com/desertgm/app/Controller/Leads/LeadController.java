package com.desertgm.app.Controller.Leads;

import com.desertgm.app.DTO.Lead.LeadDto;
import com.desertgm.app.DTO.Lead.LeadStatusDto;
import com.desertgm.app.DTO.NewResponseDto;
import com.desertgm.app.Enums.Lead.LeadStatus;
import com.desertgm.app.Models.ImportModels.Estabelecimento;
import com.desertgm.app.Models.Leads.Lead;
import com.desertgm.app.Models.User.User;
import com.desertgm.app.Repositories.prod.LeadRepository;
import com.desertgm.app.Services.LeadService;
import com.desertgm.app.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/lead")
@RestController
@CrossOrigin(origins = "*")
public class LeadController {
    @Autowired
    LeadService leadService;
    @Autowired
    UserService userService;
    @Autowired
    private LeadRepository leadRepository;

    @PostMapping("/addLeads")
    public ResponseEntity getAllLeads(@RequestBody List<Lead> list){

        leadService.addLeadList(list);
        return  ResponseEntity.ok().build();

    }

    @PutMapping("/status/{leadId}")
    public ResponseEntity<NewResponseDto> updateStatus(@PathVariable String leadId, @RequestBody LeadStatusDto body){
        switch (body.status()){
            case 3:
                leadService.updateStatusandDate(LeadStatus.fromValue(body.status()), leadId ,body.datetocall());
            case 4:
                leadService.updateStatusandDate(LeadStatus.fromValue(body.status()), leadId ,null);
        }
        NewResponseDto responseDto = new NewResponseDto("status do Lead atualizado com sucesso","OK",null);
        return ResponseEntity.ok().body(responseDto);
    }



    @PostMapping("/comment/{leadId}")
    public ResponseEntity<NewResponseDto> addComment(@PathVariable String leadId,@RequestBody HashMap<String,String> comments){
        Lead lead = leadService.getLeadByLeadId(leadId);
        var response = leadService.addOrUpdateComment(lead,comments);
        NewResponseDto responseDto = new NewResponseDto("comentario atualizado com sucesso","OK",response);
        return ResponseEntity.ok().body(responseDto);
    }



   /* @PostMapping("/add")
    public ResponseEntity postLead(@RequestBody LeadDto leadDto,@RequestAttribute("userId") String userId){

        Lead lead =  leadService.DtoToLead(leadDto,userId);
        leadService.addLead(lead);
        return ResponseEntity.ok().build();
    }*/

    @GetMapping("/status/test/")
    public ResponseEntity testePagination(@RequestParam int page,@RequestParam int size){
        var obj = leadService.testPagination(page,size);
        NewResponseDto responseDto = new NewResponseDto("transação realizada","OK",obj);
        return ResponseEntity.ok().body(responseDto);

                //rota de teste fazer paginação em leads e orders
    }



    @PutMapping("/{id}")
    public ResponseEntity putLead(@RequestBody LeadDto leadDto,@RequestAttribute("userId") String userId,@PathVariable("id") String id){
        leadService.editLead(id,leadDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/distribute-Lead/{cnae}")
    public ResponseEntity distributeLeads(@PathVariable Long cnae){
        var leads = leadService.getLeadsPerCnae(cnae);

        var users = userService.getUserbyRoleint(1);
        leadService.distributeLeads(leads,users);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<NewResponseDto> getLeads(
            @RequestAttribute("userId") String userId,
            @RequestParam int page,
            @RequestParam int size
    ){
       User user =  userService.getUserById(userId);
       List<Lead> list =  leadService.getLeadsPerRole(user.getId(), user.getRole() ,page,size);
       NewResponseDto responseDto = new NewResponseDto("transação realizada com sucesso","OK",list);
       return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/estabelecimento")
    public ResponseEntity<NewResponseDto> estabelecimentoToLead(@RequestBody List<Estabelecimento> estabelecimento){
     List<Lead> leadList = leadService.EstabelecimentoToLeadByCnae(estabelecimento);
     NewResponseDto responseDto = new NewResponseDto("dados cadastrados com sucesso","OK",leadList);
     return ResponseEntity.ok().body(responseDto);
    }



}
