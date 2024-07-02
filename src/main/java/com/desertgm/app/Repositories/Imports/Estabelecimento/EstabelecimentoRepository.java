package com.desertgm.app.Repositories.Imports.Estabelecimento;

import com.desertgm.app.Models.ImportModels.Estabelecimento.Estabelecimento;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EstabelecimentoRepository extends MongoRepository<Estabelecimento,String> {
    List<Estabelecimento> findByCnae(Long cnae);
    Page<Estabelecimento> findByCnae(Long cnae,Pageable pageable);
}
