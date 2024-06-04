package com.desertgm.app.Repositories;

import com.desertgm.app.Models.CompanyModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository< CompanyModel,String> {
}
