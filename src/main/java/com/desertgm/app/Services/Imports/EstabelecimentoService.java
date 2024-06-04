package com.desertgm.app.Services.Imports;

import com.desertgm.app.Models.EstabelecimentoModel;
import com.desertgm.app.Repositories.EstabelecimentoRepository;
import com.desertgm.app.Services.UtillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class EstabelecimentoService {
    @Autowired
    UtillsService utillsService;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    public void saveAll(List<EstabelecimentoModel> list){
        estabelecimentoRepository.saveAll(list);
    }


    public EstabelecimentoModel parseLine(String[] data, SimpleDateFormat format) {

        if (data == null || data.length == 0) {
            return null; // Ignora linhas vazias ou nulas
        }

        Date dataSituacaoCadastral = utillsService.parseDate(data[6], format);
        Date dataInicioAtividades = utillsService.parseDate(data[10], format);
        Date dateSituacaoEspecial = null;

        try {
            if (data.length > 29 && data[29] != null) {
                dateSituacaoEspecial = utillsService.parseDate(data[29], format);
            }
        } catch (Exception e) {
            // Handle exception
        }

        EstabelecimentoModel estabelecimentoModel = new EstabelecimentoModel();
        estabelecimentoModel.setCnpjFull(Long.parseLong(data[0] + data[1] + data[2] ));
        estabelecimentoModel.setCnpjBaseId(Long.parseLong(data[0]));
        estabelecimentoModel.setCnpjOrdem(utillsService.truncate(data[1], 255));
        estabelecimentoModel.setCnpjDV(utillsService.truncate(data[2], 255));

        if (utillsService.isValidLong(data[3])) {
            estabelecimentoModel.setIdentificadorMatrizFilialId(Long.parseLong(data[3]));
        }
        if (!data[4].isEmpty()) {
            estabelecimentoModel.setNomeFantasia(utillsService.truncate(data[4], 255));
        }

        if (utillsService.isValidLong(data[5])) {
            estabelecimentoModel.setSitutacaoCadastralId(Long.parseLong(data[5]));
        }

        if (dataSituacaoCadastral != null && utillsService.isDateValid(dataSituacaoCadastral)) {
            estabelecimentoModel.setDataSituacaoCadastral(dataSituacaoCadastral);
        }

        if (utillsService.isValidLong(data[7])) {
            estabelecimentoModel.setMotivoSituacaoCadastralId(Long.parseLong(data[7]));
        }

        estabelecimentoModel.setNomeCidadeExterior(utillsService.truncate(data[8], 255));

        if (utillsService.isValidLong(data[9])) {
            estabelecimentoModel.setPaisId(Long.parseLong(data[9]));
        }

        if (dataInicioAtividades != null && utillsService.isDateValid(dataInicioAtividades)) {
            estabelecimentoModel.setDataInicioAtividade(dataInicioAtividades);
        }

        if (utillsService.isValidLong(data[11])) {
            estabelecimentoModel.setCnaeFiscalPrincipalId(Long.parseLong(data[11]));
        }

        // Não truncar 'cnaeFiscalSecundaria'
        estabelecimentoModel.setCnaeFiscalSecundaria(data[12]);

        estabelecimentoModel.setTipoLogradouro(utillsService.truncate(data[13], 255));
        estabelecimentoModel.setLogradouro(utillsService.truncate(data[14], 255));
        estabelecimentoModel.setNumero(utillsService.truncate(data[15], 255));
        // Não truncar 'complemento'
        estabelecimentoModel.setComplemento(data[16]);
        estabelecimentoModel.setBairro(utillsService.truncate(data[17], 255));
        estabelecimentoModel.setCep(utillsService.truncate(data[18], 255));
        estabelecimentoModel.setUf(utillsService.truncate(data[19], 255));

        if (utillsService.isValidLong(data[20])) {
            estabelecimentoModel.setMunicipioId(Long.parseLong(data[20]));
        }

        estabelecimentoModel.setDdd1(utillsService.truncate(data[21], 255));
        estabelecimentoModel.setTelefone1(utillsService.truncate(data[22], 255));
        estabelecimentoModel.setDdd2(utillsService.truncate(data[23], 255));
        estabelecimentoModel.setTelefone2(utillsService.truncate(data[24], 255));
        estabelecimentoModel.setDddfax(utillsService.truncate(data[25], 255));
        estabelecimentoModel.setFax(utillsService.truncate(data[26], 255));
        estabelecimentoModel.setEmail(utillsService.truncate(data[27], 255));
        estabelecimentoModel.setSituacaoespecial(utillsService.truncate(data[28], 255));

        if (dateSituacaoEspecial != null && utillsService.isDateValid(dateSituacaoEspecial)) {
            estabelecimentoModel.setDatasituacaoespecial(dateSituacaoEspecial);
        }

        return estabelecimentoModel;

    }



}
