package com.desertgm.app.Repositories.prod;

import com.desertgm.app.Models.Leads.Lead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends MongoRepository<Lead,String> {

    List<Lead> findByUserId(String userId);

    List<Lead> findByUserIdIn(List<String> usersId);

    List<Lead> findByCNAEAndUserId(Long CNAE,String userId);

    List<Lead> findByStatus(int status);

    Page<Lead> findByUserId(String userId, Pageable pageable);

    Page<Lead> findByUserIdIn(List<String> usersId, Pageable pageable);

    Page<Lead> findByCNAEAndUserId(Long CNAE, String userId, Pageable pageable);

    Page<Lead> findByStatus(int status, Pageable pageable);
}
