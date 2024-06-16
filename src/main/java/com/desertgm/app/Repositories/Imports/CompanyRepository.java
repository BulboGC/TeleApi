package com.desertgm.app.Repositories.Imports;

import com.desertgm.app.Models.ImportModels.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company,String> {
}
