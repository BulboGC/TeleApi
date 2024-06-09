package com.desertgm.app.Repositories;

import com.desertgm.app.Models.Email.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email,String> {
}
