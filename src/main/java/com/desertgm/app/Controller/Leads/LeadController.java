package com.desertgm.app.Controller.Leads;

import com.desertgm.app.DTO.Lead.LeadDto;
import com.desertgm.app.DTO.Lead.LeadStatusDto;
import com.desertgm.app.DTO.NewResponseDto;
import com.desertgm.app.Enums.Lead.LeadStatus;
import com.desertgm.app.Models.Leads.Lead;
import com.desertgm.app.Models.User.User;
import com.desertgm.app.Repositories.LeadRepository;
import com.desertgm.app.Services.LeadService;
import com.desertgm.app.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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







    @PostMapping("/add")
    public ResponseEntity postLead(@RequestBody LeadDto leadDto,@RequestAttribute("userId") String userId){

        Lead lead =  leadService.DtoToLead(leadDto,userId);
        leadService.addLead(lead);
        return ResponseEntity.ok().build();
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
    public ResponseEntity<NewResponseDto> getLeads(@RequestAttribute("userId") String userId){
       User user =  userService.getUserById(userId);
       List<Lead> list =  leadService.getLeadsPerRole(user.getId(), user.getRole() );
       NewResponseDto responseDto = new NewResponseDto("transação realizada com sucesso","OK",list);
       return ResponseEntity.ok().body(responseDto);
    }
}
