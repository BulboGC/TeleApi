package com.desertgm.app.Services;

import com.desertgm.app.DTO.LeadDto;
import com.desertgm.app.Enums.Lead.LeadStatus;
import com.desertgm.app.Enums.UserRole;
import com.desertgm.app.Models.Leads.Lead;
import com.desertgm.app.Models.User.User;
import com.desertgm.app.Repositories.LeadRepository;
import com.desertgm.app.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Lead> getLeadsPerRole(String id, int role){
        var roleStr =  UserRole.fromValue(role);
        switch (roleStr){
            case USER:
                return leadRepository.findByUserId(id);

            case ADMIN:
                return leadRepository.findAll();


            case SUPERVISOR:
                List<User> users =  userRepository.findBySupervisorId(id);
                List<String> ids = users.stream()
                        .map(User::getId) // Certifique-se de que o método getId retorna o ID do usuário como String
                        .collect(Collectors.toList());

                return leadRepository.findByUserIdIn(ids);

            case SELLER:
                leadRepository.findByStatus(LeadStatus.CONFIRMED.getLeadStatus());

        }

        throw new RuntimeException("a role ou id enviado são invalidos");

    }


    public Lead addLead(Lead lead){
       return leadRepository.save(lead);
    }

    @Async
    public void addLeadList(List<Lead> list){
        leadRepository.saveAll(list);
    }

    public Lead DtoToLead(LeadDto leadDto,String userId){

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

    }

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

}
