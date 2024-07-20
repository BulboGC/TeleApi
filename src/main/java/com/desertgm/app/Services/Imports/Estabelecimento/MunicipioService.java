package com.desertgm.app.Services.Imports.Estabelecimento;

import com.desertgm.app.Models.ImportModels.Estabelecimento.Municipio;
import com.desertgm.app.Repositories.Imports.Estabelecimento.MunicipioRepository;
import com.desertgm.app.Services.GenericService;
import com.desertgm.app.Services.UtillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class MunicipioService implements GenericService<Municipio> {
    @Autowired
    private UtillsService utillsService;
    @Autowired
    private MunicipioRepository municipioRepository;

    @Override
    public Municipio parseLine(String[] line, SimpleDateFormat format) {
        Municipio municipio = new Municipio();
        String line0 = this.utillsService.cleanString(line[0]);
        String line1 = this.utillsService.cleanString(line[1]);

        if(utillsService.isValidLong(line0)){
            municipio.setCode(utillsService.parseInt(line0));
        }
        municipio.setDescription(line1);
        return municipio;
    }

    @Override
    public void saveAll(List<Municipio> entities) {
        municipioRepository.saveAll(entities);
    }
    public Municipio findByCode(int code){
       return municipioRepository.findByCode(code);
    }
}
