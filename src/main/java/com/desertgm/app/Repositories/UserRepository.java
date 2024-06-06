package com.desertgm.app.Repositories;
import com.desertgm.app.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);

    List<User> findBySupervisorId(String supervisorId );
}
