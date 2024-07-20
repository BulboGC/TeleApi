package com.desertgm.app.Services.Imports.Estabelecimento;

import com.desertgm.app.Models.ImportModels.Estabelecimento.Cnae;
import com.desertgm.app.Repositories.Imports.Estabelecimento.CnaeRepository;
import com.desertgm.app.Services.GenericService;
import com.desertgm.app.Services.UtillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CnaeService implements GenericService<Cnae> {

    @Autowired
    UtillsService utillsService;
    @Autowired
    CnaeRepository cnaeRepository;

    @Override
    public Cnae parseLine(String[] line, SimpleDateFormat format) {
        Cnae cnae = new Cnae();

        if(utillsService.isValidLong(line[0])){
            cnae.setCode(utillsService.parseInt(line[0]));
        }
        cnae.setDescricao(utillsService.cleanString(line[1]));

        return cnae;
    }

    @Override
    public void saveAll(List<Cnae> entities) {
        cnaeRepository.saveAll(entities);
    }
    public Cnae findByCode(int code){
        return cnaeRepository.findByCode(code);
    }
}
