package com.desertgm.app.Services.Imports;

import com.desertgm.app.Models.ImportModels.Socios;
import com.desertgm.app.Models.Leads.Socio;
import com.desertgm.app.Repositories.Imports.SociosRepository;
import com.desertgm.app.Repositories.SocioRepository;
import com.desertgm.app.Services.GenericService;
import com.desertgm.app.Services.UtillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class SociosService implements GenericService<Socios> {
    @Autowired
    SociosRepository socioRepository;
    @Autowired
    UtillsService utillsService;
    @Override
    public void saveAll(List<Socios> sociosList){
        socioRepository.saveAll(sociosList);
    }
    @Override
    public Socios parseLine(String[] data, SimpleDateFormat format) {

        if (data == null || data.length == 0) {
            return null; // Ignora linhas vazias ou nulas
        }

        Date dataEntradaSociedade = utillsService.parseDate(data[6], format);
        Socios socioModel = new Socios();

        socioModel.setCnpjBase(utillsService.parseLong(data[0]));
        socioModel.setIdentificador(utillsService.parseInt(data[1]));
        socioModel.setNome_razao(utillsService.truncate(data[2], 255));
        socioModel.setCpf_cnpj_socio(utillsService.truncate(data[3], 255));
        socioModel.setQualificacao(utillsService.parseLong(data[4]));
        socioModel.setData_entrada_socidedade(dataEntradaSociedade);
        socioModel.setPais(utillsService.parseLong(data[5]));
        socioModel.setRepresentante_legal(utillsService.truncate(data[6], 255));
        socioModel.setNome_representante(utillsService.truncate(data[7], 255));
        socioModel.setQualificacao_representante(utillsService.parseLong(data[8]));
        socioModel.setFaixa_etaria(utillsService.parseInt(data[9]));
        return socioModel;

    }
}
