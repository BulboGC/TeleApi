package com.desertgm.app.Repositories.Imports.Estabelecimento;

import com.desertgm.app.Models.ImportModels.Estabelecimento.Municipio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends MongoRepository<Municipio,String> {
   Municipio findByCode(int code);
}
