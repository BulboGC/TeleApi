package com.desertgm.app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<com.desertgm.app.Models.Email.Email,String> {
}
