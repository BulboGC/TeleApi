package com.desertgm.app.Repositories.Imports.Estabelecimento;

import com.desertgm.app.Models.ImportModels.Estabelecimento.Pais;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends MongoRepository<Pais,String> {
    Pais findByCode(int code);
}
