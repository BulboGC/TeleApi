package com.desertgm.app.Services.Imports.Estabelecimento;

import com.desertgm.app.Models.ImportModels.Estabelecimento.Pais;
import com.desertgm.app.Repositories.Imports.Estabelecimento.PaisRepository;
import com.desertgm.app.Services.GenericService;
import com.desertgm.app.Services.UtillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PaisService implements GenericService<Pais> {
    @Autowired
    private UtillsService utillsService;
    @Autowired
    private PaisRepository paisRepository;

    @Override
    public Pais parseLine(String[] line, SimpleDateFormat format) {
        Pais pais = new Pais();

        String line0 = utillsService.cleanString(line[0]);
        String line1 = utillsService.cleanString(line[1]);

        if (utillsService.isValidLong(line0)) {
            pais.setCode(utillsService.parseInt(line0));
        }

        pais.setDescription(line1);

        return pais;
    }

    @Override
    public void saveAll(List<Pais> entities) {
        paisRepository.saveAll(entities);
    }
}
