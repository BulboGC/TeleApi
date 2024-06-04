package com.desertgm.app.Services.Imports;

import com.desertgm.app.Models.CompanyModel;
import com.desertgm.app.Repositories.CompanyRepository;
import com.desertgm.app.Services.UtillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    UtillsService utillsService;
    @Autowired
    CompanyRepository companyRepository;

    public void saveAll(List<CompanyModel> companyModelList){
        companyRepository.saveAll(companyModelList);
    }

    public CompanyModel parseLine(String[] data) {
        CompanyModel companyModel = new CompanyModel();

        // Verificar se data contém ao menos um elemento e se é válido
        if (data.length > 0 && utillsService.isValidLong(data[0])) {
            companyModel.setCnpjBaseInt(Long.parseLong(utillsService.cleanString(data[0])));
            companyModel.setCnpjBaseStr(utillsService.truncate(utillsService.cleanString(data[0]), 255));
        }

        // Verificar se data contém ao menos dois elementos
        if (data.length > 1) {
            companyModel.setRazaoSocial(utillsService.truncate(utillsService.cleanString(data[1]), 255));
        }

        // Verificar se data contém ao menos três elementos e se é válido
        if (data.length > 2 && utillsService.isValidLong(data[2])) {
            companyModel.setNaturezaLegal(Long.parseLong(utillsService.cleanString(data[2])));
        }

        // Verificar se data contém ao menos quatro elementos e se é válido
        if (data.length > 3 && utillsService.isValidLong(data[3])) {
            companyModel.setQualificacaoResponsavelModel(Long.parseLong(utillsService.cleanString(data[3])));
        }

        // Verificar se data contém ao menos cinco elementos
        if (data.length > 4) {
            try {
                companyModel.setCapitalSocial(Double.parseDouble(utillsService.cleanString(data[4])));
            } catch (Exception e) {
                // Log error or handle exception if needed
            }
        }

        // Verificar se data contém ao menos seis elementos e se é válido
        if (data.length > 5 && utillsService.isValidLong(data[5])) {
            companyModel.setPorteEmpresa(Long.parseLong(utillsService.cleanString(data[5])));
        }

        // Verificar se data contém ao menos sete elementos
        if (data.length > 6) {
            companyModel.setEnteFederativoResponsavel(utillsService.truncate(utillsService.cleanString(data[6]), 255));
        }

        return companyModel;
    }
}
