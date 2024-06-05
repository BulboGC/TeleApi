package com.desertgm.app.Repositories;

import com.desertgm.app.Models.Leads.Estabelecimento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstabelecimentoRepository extends MongoRepository<Estabelecimento,String> {
}
