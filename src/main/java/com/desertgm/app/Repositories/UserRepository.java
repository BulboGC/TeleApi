package com.desertgm.app.Repositories;
import com.desertgm.app.Models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<UserModel,Long> {
    UserDetails findByEmail(String email);

}
