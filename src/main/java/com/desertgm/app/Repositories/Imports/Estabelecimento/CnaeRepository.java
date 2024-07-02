package com.desertgm.app.Repositories.Imports.Estabelecimento;

import com.desertgm.app.Models.ImportModels.Estabelecimento.Cnae;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnaeRepository extends MongoRepository<Cnae,String > {
    Cnae findByCode(int code);
}

