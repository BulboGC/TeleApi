package com.desertgm.app.Repositories;

import com.desertgm.app.Models.Leads.Lead;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends MongoRepository<Lead,String> {
    List<Lead> findByUserId(String userId);

    List<Lead> findByUserIdIn(List<String> usersId);
}
