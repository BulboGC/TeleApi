package com.desertgm.app.Repositories.prod;

import com.desertgm.app.Models.Leads.Socio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SocioRepository extends MongoRepository<Socio,String> {
}
