package com.desertgm.app.Repositories;

import com.desertgm.app.Models.EmailModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<EmailModel,String> {
}
