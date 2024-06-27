package com.desertgm.app.Repositories.Imports;

import com.desertgm.app.Models.ImportModels.Socios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SociosRepository extends MongoRepository<Socios,String > {
}
