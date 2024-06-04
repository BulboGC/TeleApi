package com.desertgm.app.Repositories;

import com.desertgm.app.Models.SocioModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SocioRepository extends MongoRepository<SocioModel,String> {
}
