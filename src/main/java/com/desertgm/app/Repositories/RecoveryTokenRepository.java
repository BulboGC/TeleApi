package com.desertgm.app.Repositories;

import com.desertgm.app.Models.User.RecoveryToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecoveryTokenRepository extends MongoRepository<RecoveryToken,String> {
}
