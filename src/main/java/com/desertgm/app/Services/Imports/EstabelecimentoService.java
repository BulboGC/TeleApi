package com.desertgm.app.Services.Imports;

import com.desertgm.app.Models.ImportModels.Estabelecimento;
import com.desertgm.app.Models.Leads.Lead;
import com.desertgm.app.Repositories.Imports.EstabelecimentoRepository;
import com.desertgm.app.Services.GenericService;
import com.desertgm.app.Services.UtillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class EstabelecimentoService implements GenericService<Estabelecimento> {
    @Autowired
    UtillsService utillsService;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;
    @Override
    public void saveAll(List<Estabelecimento> list){
        estabelecimentoRepository.saveAll(list);
    }

    @Override
    public Estabelecimento parseLine(String[] data, SimpleDateFormat format) {

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

        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setCnpjFull(Long.parseLong(data[0] + data[1] + data[2] ));
        estabelecimento.setCnpjBaseId(Long.parseLong(data[0]));
        estabelecimento.setCnpjOrdem(utillsService.truncate(data[1], 255));
        estabelecimento.setCnpjDV(utillsService.truncate(data[2], 255));

        if (utillsService.isValidLong(data[3])) {
            estabelecimento.setIdentificadorMatrizFilialId(Long.parseLong(data[3]));
        }
        if (!data[4].isEmpty()) {
            estabelecimento.setNomeFantasia(utillsService.truncate(data[4], 255));
        }

        if (utillsService.isValidLong(data[5])) {
            estabelecimento.setSitutacaoCadastralId(Long.parseLong(data[5]));
        }

        /*if (dataSituacaoCadastral != null && utillsService.isDateValid(dataSituacaoCadastral)) {
            estabelecimento.setDataSituacaoCadastral(dataSituacaoCadastral);
        }*/

        if (utillsService.isValidLong(data[7])) {
            estabelecimento.setMotivoSituacaoCadastralId(Long.parseLong(data[7]));
        }

        estabelecimento.setNomeCidadeExterior(utillsService.truncate(data[8], 255));

        if (utillsService.isValidLong(data[9])) {
            estabelecimento.setPaisId(Long.parseLong(data[9]));
        }

       /* if (dataInicioAtividades != null && utillsService.isDateValid(dataInicioAtividades)) {
            estabelecimento.setDataInicioAtividade(dataInicioAtividades);
        }*/

        if (utillsService.isValidLong(data[11])) {
            estabelecimento.setCnaeFiscalPrincipalId(Long.parseLong(data[11]));
        }

        // Não truncar 'cnaeFiscalSecundaria'
        estabelecimento.setCnaeFiscalSecundaria(data[12]);

        estabelecimento.setTipoLogradouro(utillsService.truncate(data[13], 255));
        estabelecimento.setLogradouro(utillsService.truncate(data[14], 255));
        estabelecimento.setNumero(utillsService.truncate(data[15], 255));
        // Não truncar 'complemento'
        estabelecimento.setComplemento(data[16]);
        estabelecimento.setBairro(utillsService.truncate(data[17], 255));
        estabelecimento.setCep(utillsService.truncate(data[18], 255));
        estabelecimento.setUf(utillsService.truncate(data[19], 255));

        if (utillsService.isValidLong(data[20])) {
            estabelecimento.setMunicipioId(Long.parseLong(data[20]));
        }

        estabelecimento.setDdd1(utillsService.truncate(data[21], 255));
        estabelecimento.setTelefone1(utillsService.truncate(data[22], 255));
        estabelecimento.setDdd2(utillsService.truncate(data[23], 255));
        estabelecimento.setTelefone2(utillsService.truncate(data[24], 255));
        estabelecimento.setDddfax(utillsService.truncate(data[25], 255));
        estabelecimento.setFax(utillsService.truncate(data[26], 255));
        estabelecimento.setEmail(utillsService.truncate(data[27], 255));
        estabelecimento.setSituacaoespecial(utillsService.truncate(data[28], 255));

        /*if (dateSituacaoEspecial != null && utillsService.isDateValid(dateSituacaoEspecial)) {
            estabelecimento.setDatasituacaoespecial(dateSituacaoEspecial);
        }*/

        return estabelecimento;

    }




}
