package com.desertgm.app.Repositories.Imports;

import com.desertgm.app.Models.ImportModels.Estabelecimento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstabelecimentoRepository extends MongoRepository<Estabelecimento,String> {
}
