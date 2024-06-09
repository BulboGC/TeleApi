package com.desertgm.app.Services;

import com.desertgm.app.Enums.UserRole;
import com.desertgm.app.Models.Leads.Lead;
import com.desertgm.app.Models.User.User;
import com.desertgm.app.Repositories.LeadRepository;
import com.desertgm.app.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Lead> getLeadsPerRole(String id, UserRole role){

        switch (role){
            case USER:
                return leadRepository.findByUserId(id);

            case ADMIN:
                return leadRepository.findAll();


            case SUPERVISOR:
                List<User> users =  userRepository.findBySupervisorId(id);
                List<String> subordinateUserIds = users.stream()
                        .map(User::getId)
                        .toList();
                return leadRepository.findByUserIdIn(subordinateUserIds);

        }

        throw new RuntimeException("a role ou id enviado são invalidos");

    }


    public Lead addLead(Lead lead){
       return leadRepository.save(lead);
    }

    public void addLeadList(List<Lead> list){
        leadRepository.saveAll(list);
    }

}
