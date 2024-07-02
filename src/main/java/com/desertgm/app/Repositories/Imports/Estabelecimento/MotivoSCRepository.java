package com.desertgm.app.Repositories.Imports.Estabelecimento;

import com.desertgm.app.Models.ImportModels.Estabelecimento.MotivoSC;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoSCRepository extends MongoRepository<MotivoSC,String> {
    MotivoSC findByCode(int code);
}
