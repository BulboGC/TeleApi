package com.desertgm.app.Repositories;

import com.desertgm.app.Models.EstabelecimentoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstabelecimentoRepository extends MongoRepository<EstabelecimentoModel,String> {
}
