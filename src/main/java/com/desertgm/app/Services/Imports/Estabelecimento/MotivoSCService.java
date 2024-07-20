package com.desertgm.app.Services.Imports.Estabelecimento;

import com.desertgm.app.Models.ImportModels.Estabelecimento.MotivoSC;
import com.desertgm.app.Repositories.Imports.Estabelecimento.MotivoSCRepository;
import com.desertgm.app.Services.GenericService;
import com.desertgm.app.Services.UtillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class MotivoSCService implements GenericService<MotivoSC> {

    @Autowired
    private MotivoSCRepository motivoSCRepository;
    @Autowired
    private UtillsService utillsService;


    @Override
    public MotivoSC parseLine(String[] line, SimpleDateFormat format) {
        MotivoSC motivoSC = new MotivoSC();
        String line0 = utillsService.cleanString(line[0]);
        String line1 = utillsService.cleanString(line[1]);

        if(utillsService.isValidLong(line0)){
            motivoSC.setCode(utillsService.parseInt(line0));
        }
        motivoSC.setDescription(line1);
        return motivoSC;
    }

    @Override
    public void saveAll(List<MotivoSC> entities) {
        motivoSCRepository.saveAll(entities);
    }

    public MotivoSC findByCode(int code){
       return motivoSCRepository.findByCode(code);
    }
}
