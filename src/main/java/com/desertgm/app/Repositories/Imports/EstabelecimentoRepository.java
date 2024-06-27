package com.desertgm.app.Repositories.Imports;

import com.desertgm.app.Models.ImportModels.Estabelecimento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EstabelecimentoRepository extends MongoRepository<Estabelecimento,String> {
    List<Estabelecimento> findByCnaeFiscalPrincipalId(Long cnaeFiscalPrincipalId);
}
