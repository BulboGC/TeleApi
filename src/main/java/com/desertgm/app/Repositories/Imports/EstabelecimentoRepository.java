package com.desertgm.app.Repositories.Imports;

import com.desertgm.app.Models.ImportModels.Estabelecimento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EstabelecimentoRepository extends MongoRepository<Estabelecimento,String> {
    List<Estabelecimento> findByCnaeFiscalPrincipalId(Long cnaeFiscalPrincipalId);
}
