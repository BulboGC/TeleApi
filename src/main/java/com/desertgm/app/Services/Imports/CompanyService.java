package com.desertgm.app.Services.Imports;

import com.desertgm.app.Models.ImportModels.Company;
import com.desertgm.app.Repositories.Imports.CompanyRepository;
import com.desertgm.app.Services.GenericService;
import com.desertgm.app.Services.UtillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CompanyService implements GenericService<Company> {
    @Autowired
    UtillsService utillsService;
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public void saveAll(List<Company> companyList){
        companyRepository.saveAll(companyList);
    }
    @Override
    public Company parseLine(String[] data, SimpleDateFormat simpleDateFormat) {
        Company company = new Company();

        // Verificar se data contém ao menos um elemento e se é válido
        if (data.length > 0 && utillsService.isValidLong(data[0])) {
            company.setCnpjBase(utillsService.truncate(utillsService.cleanString(data[0]), 255));
        }

        // Verificar se data contém ao menos dois elementos
        if (data.length > 1) {
            company.setRazaoSocial(utillsService.truncate(utillsService.cleanString(data[1]), 255));
        }

        // Verificar se data contém ao menos três elementos e se é válido
        if (data.length > 2 && utillsService.isValidLong(data[2])) {
            company.setNaturezaLegal(Long.parseLong(utillsService.cleanString(data[2])));
        }

        // Verificar se data contém ao menos quatro elementos e se é válido
        if (data.length > 3 && utillsService.isValidLong(data[3])) {
            company.setQualificacaoResponsavelModel(Long.parseLong(utillsService.cleanString(data[3])));
        }

        // Verificar se data contém ao menos cinco elementos
        if (data.length > 4) {
            try {
                company.setCapitalSocial(utillsService.cleanString(data[4]));
            } catch (Exception e) {
                // Log error or handle exception if needed
            }
        }

        // Verificar se data contém ao menos seis elementos e se é válido
        if (data.length > 5 && utillsService.isValidLong(data[5])) {
            company.setPorteEmpresa(Long.parseLong(utillsService.cleanString(data[5])));
        }

        // Verificar se data contém ao menos sete elementos
        if (data.length > 6) {
            company.setEnteFederativoResponsavel(utillsService.truncate(utillsService.cleanString(data[6]), 255));
        }

        return company;
    }

    public Company findByCnpj(String cnpj){
       return companyRepository.findByCnpjBase(cnpj);

    }

}
