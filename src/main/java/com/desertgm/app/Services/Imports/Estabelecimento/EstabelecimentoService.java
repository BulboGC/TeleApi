package com.desertgm.app.Services.Imports.Estabelecimento;

import com.desertgm.app.Enums.Lead.LeadStatus;

import com.desertgm.app.Models.ImportModels.Company;
import com.desertgm.app.Models.ImportModels.Estabelecimento.Estabelecimento;
import com.desertgm.app.Models.Leads.Lead;
import com.desertgm.app.Repositories.Imports.Estabelecimento.EstabelecimentoRepository;
import com.desertgm.app.Services.GenericService;

import com.desertgm.app.Services.Imports.CompanyService;
import com.desertgm.app.Services.UtillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

@Service
public class EstabelecimentoService implements GenericService<Estabelecimento> {
    @Autowired
    UtillsService utillsService;
    @Autowired
    CompanyService companyService;
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;



    @Override
    public void saveAll(List<Estabelecimento> list){
        estabelecimentoRepository.saveAll(list);
    }

    @Override
    public Estabelecimento parseLine(String[] data, SimpleDateFormat format) {

        Estabelecimento estabelecimento = new Estabelecimento();

        estabelecimento.setCnpjBase(utillsService.truncate(utillsService.cleanString(data[0]), 255));

        if (data.length > 1) {
            estabelecimento.setCnpjOrdem(utillsService.truncate(utillsService.cleanString(data[1]), 255));
        }
        if (data.length > 2) {
            estabelecimento.setCnpjDV(utillsService.truncate(utillsService.cleanString(data[2]), 255));
        }
        if (data.length > 3 && utillsService.isValidLong(data[3])) {
            estabelecimento.setMatrizFilial(utillsService.parseLong(data[3]));
        }
        if (data.length > 4) {
            estabelecimento.setNomeFantasia(utillsService.truncate(utillsService.cleanString(data[4]), 255));
        }
        if (data.length > 5 && utillsService.isValidLong(data[5])) {
            estabelecimento.setSitutacaoCadastral(utillsService.parseLong(data[5]));
        }
        if (data.length > 6) {
            estabelecimento.setDt_SituacaoCadastral(utillsService.parseDate(data[6], format));
        }
        if (data.length > 7 && utillsService.isValidLong(data[7])) {
            estabelecimento.setMotivoSituacaoCadastral(utillsService.parseLong(data[7]));
        }
        if (data.length > 8) {
            estabelecimento.setNomeCidadeExterior(utillsService.truncate(utillsService.cleanString(data[8]), 255));
        }
        if (data.length > 9 && utillsService.isValidLong(data[9])) {
            estabelecimento.setPais(utillsService.parseLong(data[9]));
        }
        if (data.length > 10) {
            estabelecimento.setDt_inicioAtividade(utillsService.parseDate(data[10], format));
        }
        if (data.length > 11 && utillsService.isValidLong(data[11])) {
            estabelecimento.setCnae(utillsService.parseLong(data[11]));
        }
        if (data.length > 12) {
            String cnaeSecundaria = utillsService.cleanString(data[12]);
            if (cnaeSecundaria != null) {
                estabelecimento.setCnae_secundaria(cnaeSecundaria);
            } else {
                estabelecimento.setCnae_secundaria("");
            }
        }
        if (data.length > 13) {
            estabelecimento.setTipo_Logradouro(utillsService.truncate(utillsService.cleanString(data[13]), 255));
        }
        if (data.length > 14) {
            estabelecimento.setLogradouro(utillsService.truncate(utillsService.cleanString(data[14]), 255));
        }
        if (data.length > 15) {
            estabelecimento.setNumero(utillsService.truncate(utillsService.cleanString(data[15]), 255));
        }
        if (data.length > 16) {
            String complemento = utillsService.cleanString(data[16]);
            if (complemento != null) {
                estabelecimento.setComplemento(complemento.trim());
            } else {
                estabelecimento.setComplemento("");
            }
        }
        if (data.length > 17) {
            estabelecimento.setBairro(utillsService.truncate(utillsService.cleanString(data[17]), 255));
        }
        if (data.length > 18) {
            estabelecimento.setCep(utillsService.truncate(utillsService.cleanString(data[18]), 255));
        }
        if (data.length > 19) {
            estabelecimento.setUf(utillsService.truncate(utillsService.cleanString(data[19]), 255));
        }
        if (data.length > 20 && utillsService.isValidLong(data[20])) {
            estabelecimento.setMunicipio(utillsService.parseLong(data[20]));
        }
        if (data.length > 21) {
            estabelecimento.setDdd1(utillsService.truncate(utillsService.cleanString(data[21]), 255));
        }
        if (data.length > 22) {
            estabelecimento.setTelefone1(utillsService.truncate(utillsService.cleanString(data[22]), 255));
        }
        if (data.length > 23) {
            estabelecimento.setDdd2(utillsService.truncate(utillsService.cleanString(data[23]), 255));
        }
        if (data.length > 24) {
            estabelecimento.setTelefone2(utillsService.truncate(utillsService.cleanString(data[24]), 255));
        }
        if (data.length > 25) {
            estabelecimento.setDdd_fax(utillsService.truncate(utillsService.cleanString(data[25]), 255));
        }
        if (data.length > 26) {
            estabelecimento.setFax(utillsService.truncate(utillsService.cleanString(data[26]), 255));
        }
        if (data.length > 27) {
            estabelecimento.setEmail(utillsService.truncate(utillsService.cleanString(data[27]), 255));
        }
        if (data.length > 28) {
            estabelecimento.setSituacao_especial(utillsService.truncate(utillsService.cleanString(data[28]), 255));
        }
        if (data.length > 29) {
            estabelecimento.setDt_situacao_especial(utillsService.parseDate(data[29], format));
        }

        return estabelecimento;
    }
    
    
    
    public List<Lead> trasformInLead(List<Estabelecimento> list){
        List<Lead> leadList = new ArrayList<>();
        for( Estabelecimento estabelecimento : list){

            Company company = companyService.findByCnpj(estabelecimento.getCnpjBase());
            if(company != null){
                Lead lead = new Lead();
                lead.setCNPJ(estabelecimento.getCnpjFull());
                lead.setCNAE(estabelecimento.getCnae());
                lead.setPhone1(estabelecimento.getDdd1() + estabelecimento.getTelefone1());
                lead.setRazaoSocial(company.getRazaoSocial());
                lead.setPorte(company.getPorteEmpresa());
                lead.setPhone2(estabelecimento.getDdd2() + estabelecimento.getTelefone2());
                lead.setStatus(LeadStatus.PENDING.getLeadStatus());
                leadList.add(lead);
            }

        }

        return leadList;
    }

    public List<Estabelecimento> findByCnae(Long cnae){ 
        
        return estabelecimentoRepository.findByCnae(cnae);
    }

    public Page<Estabelecimento> findByCnaePageble(Long cnae,int page){
        int size = 50000;
        Pageable pageable = PageRequest.of(page, size);
        return  estabelecimentoRepository.findByCnae(cnae,pageable);

    }
}
