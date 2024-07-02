package com.desertgm.app.Services;

import com.desertgm.app.DTO.Lead.LeadDto;
import com.desertgm.app.Enums.Lead.LeadStatus;
import com.desertgm.app.Enums.UserRole;
import com.desertgm.app.Models.ImportModels.Estabelecimento.Estabelecimento;
import com.desertgm.app.Models.Leads.Lead;
import com.desertgm.app.Models.User.User;
import com.desertgm.app.Repositories.Imports.Estabelecimento.EstabelecimentoRepository;
import com.desertgm.app.Repositories.prod.LeadRepository;
import com.desertgm.app.Repositories.prod.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public Lead getLeadByLeadId(String leadId){
       Optional<Lead> optionalLead =  leadRepository.findById(leadId);
       return optionalLead.get();
    }
    public Page<Lead> getLeadsPerRole(String id, int role,int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        var roleStr =  UserRole.fromValue(role);
        switch (roleStr){
            case USER:
                return leadRepository.findByUserId(id,pageable);

            case ADMIN:
                return leadRepository.findAll(pageable);


            case SUPERVISOR:
                List<User> users =  userRepository.findBySupervisorId(id);
                List<String> ids = users.stream()
                        .map(User::getId) // Certifique-se de que o método getId retorna o ID do usuário como String
                        .collect(Collectors.toList());

                return leadRepository.findByUserIdIn(ids,pageable);

            case SELLER:
                leadRepository.findByStatus(LeadStatus.CONFIRMED.getLeadStatus(),pageable);

        }

        throw new RuntimeException("a role ou id enviado são invalidos");

    }


    public Lead addLead(Lead lead){
       return leadRepository.save(lead);
    }


    public void addLeadList(List<Lead> list){
        leadRepository.saveAll(list);
    }

    /*public Lead DtoToLead(LeadDto leadDto,String userId){

         Lead lead = new Lead(
                leadDto.identificadorMatrizFilial(),
                leadDto.CNPJ(),
                leadDto.razaoSocial(),
                leadDto.clientName(),
                leadDto.Email(),
                leadDto.Phone1(),
                leadDto.Phone2(),
                leadDto.CNAE(),
                 leadDto.activity(),
                 LeadStatus.PENDING.getLeadStatus(),
                 leadDto.dateForCall(),
                 leadDto.comments(),
                userId
        );
         return lead;

    }*/

    public List<Lead> getLeadsPerCnae(Long cnae){
       var users = leadRepository.findByCNAEAndUserId(cnae,"");
        return  users;
    }

    public void distributeLeads(List<Lead> leads, List<User> users) {
        int numberOfUsers = users.size();
        int numberOfLeads = leads.size();
        int userIndex = 0; // Índice para iterar entre usuários

        // Verifique se há usuários suficientes para os leads
        if (numberOfLeads < numberOfUsers) {
            throw new IllegalArgumentException("Número de leads menor que o número de usuários.");
        }

        for (Lead lead : leads) {
            User user = users.get(userIndex);
            lead.setUserId(user.getId());
            leadRepository.save(lead);

            // Incremente o índice do usuário para o próximo na lista
            userIndex = (userIndex + 1) % numberOfUsers;
        }
    }


    public Lead editLead(String leadId,LeadDto leadDto){
        Optional<Lead> lead = leadRepository.findById(leadId);
        Lead lead1 = lead.get();
        BeanUtils.copyProperties(leadDto,lead1);

        return leadRepository.save(lead1) ;
    }

    public void updateStatusandDate(LeadStatus leadStatus, String leadId, Date date){
        Lead lead =  this.getLeadByLeadId(leadId);
        if(date != null){
            lead.setDateForCall(date);
        }
       lead.setStatus(leadStatus.getLeadStatus());

       leadRepository.save(lead);
    }

    public Lead addOrUpdateComment(Lead lead, HashMap<String,String> comment){
        if(comment.get("comment").isBlank()){
            throw new RuntimeException("não é possivel salvar usuarios vazios");
        }
        lead.setComments(comment.get("comment"));
        return leadRepository.save(lead);
    }

    public List<Lead> EstabelecimentoToLeadByCnae(List<Estabelecimento> list){

       List<Lead> leads = new ArrayList<>();
       list.forEach((estabelecimento)->{

           Lead lead =new Lead();
           lead.setCNPJ(Long.parseLong(estabelecimento.getCnpjBase() + estabelecimento.getCnpjOrdem()+ estabelecimento.getCnpjDV()));
           lead.setEmail(estabelecimento.getEmail());
           lead.setStatus(LeadStatus.PENDING.getLeadStatus());
           lead.setCNAE(estabelecimento.getCnae());
           lead.setIdentificadorMatrizFilial(estabelecimento.getMatrizFilial());

           if(estabelecimento.getDdd1() != null &&  estabelecimento.getTelefone1() != null){
               lead.setPhone1(estabelecimento.getDdd1() + estabelecimento.getTelefone1());
           }

           if(estabelecimento.getDdd2() != null &&  estabelecimento.getTelefone2() != null){
               lead.setPhone2(estabelecimento.getDdd2()+ estabelecimento.getTelefone2());
           }

            leads.add(lead);
       });

       return leadRepository.saveAll(leads);
    }


    public Page<Lead> testPagination(int page,int size){
        Pageable pageable = PageRequest.of(page, size);
        return leadRepository.findAll(pageable);

    }

}
