package com.desertgm.app.Services.Imports.Estabelecimento;

import com.desertgm.app.DTO.Imports.ResponseEstabelecimentoDto;
import com.desertgm.app.DTO.ResponsePageble;
import com.desertgm.app.Enums.Lead.LeadStatus;

import com.desertgm.app.Enums.imports.estabelecimento.MatrizFilialEnum;
import com.desertgm.app.Enums.imports.estabelecimento.SituacaoCadastralEnum;
import com.desertgm.app.Models.ImportModels.Company;
import com.desertgm.app.Models.ImportModels.Estabelecimento.Estabelecimento;
import com.desertgm.app.Models.ImportModels.Estabelecimento.MotivoSC;
import com.desertgm.app.Models.ImportModels.Estabelecimento.Municipio;
import com.desertgm.app.Models.ImportModels.Estabelecimento.Pais;
import com.desertgm.app.Models.Leads.Lead;
import com.desertgm.app.Repositories.Imports.Estabelecimento.EstabelecimentoRepository;
import com.desertgm.app.Services.GenericService;

import com.desertgm.app.Services.Imports.CompanyService;
import com.desertgm.app.Services.UtillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class EstabelecimentoService implements GenericService<Estabelecimento> {
    @Autowired
    UtillsService utillsService;
    @Autowired
    CompanyService companyService;
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    MotivoSCService motivoSC;
    @Autowired
    PaisService paisService;
    @Autowired
    MotivoSCService motivoSCService;

    @Autowired
    CnaeService cnaeService;

    @Autowired
    MunicipioService municipioService;
    @Autowired
    MongoTemplate mongoTemplate;


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
            estabelecimento.setMatrizFilial(utillsService.parseInt(data[3]));
        }
        if (data.length > 4) {
            estabelecimento.setNomeFantasia(utillsService.truncate(utillsService.cleanString(data[4]), 255));
        }
        if (data.length > 5 && utillsService.isValidLong(data[5])) {
            estabelecimento.setSitutacaoCadastral(utillsService.parseInt(data[5]));
        }
        if (data.length > 6) {
            estabelecimento.setDt_SituacaoCadastral(utillsService.parseDate(data[6], format));
        }
        if (data.length > 7 && utillsService.isValidLong(data[7])) {
            estabelecimento.setMotivoSituacaoCadastral(utillsService.parseInt(data[7]));
        }
        if (data.length > 8) {
            estabelecimento.setNomeCidadeExterior(utillsService.truncate(utillsService.cleanString(data[8]), 255));
        }
        if (data.length > 9 && utillsService.isValidLong(data[9])) {
            estabelecimento.setPais(utillsService.parseInt(data[9]));
        }
        if (data.length > 10) {
            estabelecimento.setDt_inicioAtividade(utillsService.parseDate(data[10], format));
        }
        if (data.length > 11 && utillsService.isValidLong(data[11])) {
            estabelecimento.setCnae(utillsService.parseInt(data[11]));
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
            estabelecimento.setMunicipio(utillsService.parseInt(data[20]));
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
                lead.setCNPJ(utillsService.parseLong(estabelecimento.getCnpjBase() + estabelecimento.getCnpjOrdem()+ estabelecimento.getCnpjDV()));
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

    public List<Estabelecimento> findByCnae(int cnae){
        
        return estabelecimentoRepository.findByCnae(cnae);
    }

    public Page<Estabelecimento> findByCnaePageble(int cnae,int page){
        int size = 100;
        Pageable pageable = PageRequest.of(page, size);
        return  estabelecimentoRepository.findByCnae(cnae,pageable);

    }

    @Async
    public CompletableFuture< List<ResponseEstabelecimentoDto>> transformEstabelecimentoToResponseDto(List<Estabelecimento> entities) {
        String pattern = "dd/MM/yyyy";
        List<ResponseEstabelecimentoDto> estabelecimentoDtos = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        for (Estabelecimento estabelecimento : entities) {
            ResponseEstabelecimentoDto responseEstabelecimentoDto = new ResponseEstabelecimentoDto();
            responseEstabelecimentoDto.setCnpj(utillsService.parseLong(estabelecimento.getCnpjBase() + estabelecimento.getCnpjOrdem()+ estabelecimento.getCnpjDV()));
            responseEstabelecimentoDto.setMatrizFilial(MatrizFilialEnum.fromValue(estabelecimento.getMatrizFilial()));
            responseEstabelecimentoDto.setNome_fantasia(estabelecimento.getNomeFantasia());
            responseEstabelecimentoDto.setSituacaoCadastral(SituacaoCadastralEnum.fromCodigo(estabelecimento.getSitutacaoCadastral()));

            // Verifica e formata a data de situação cadastral
            if (estabelecimento.getDt_SituacaoCadastral() != null && utillsService.isDateValid(estabelecimento.getDt_SituacaoCadastral())) {
                try {
                    String dateFormatted = dateFormat.format(estabelecimento.getDt_SituacaoCadastral());
                    responseEstabelecimentoDto.setDtSituacaoCadastral(dateFormatted);
                } catch (Exception e) {
                    System.out.println("Erro ao formatar data de situação cadastral: " + e.getMessage());
                }
            }

            // Motivo Situacao Cadastral
            try {
                var motivo = motivoSC.findByCode(estabelecimento.getMotivoSituacaoCadastral());
                if (motivo != null) {
                    responseEstabelecimentoDto.setMotivoSituacaoEspecial(motivo.getDescription());
                }
            } catch (Exception e) {
                System.out.println("Erro ao buscar motivo de situação cadastral: " + e.getMessage());
            }

            responseEstabelecimentoDto.setNome_cidade_exterior(estabelecimento.getNomeCidadeExterior());

            // País
            try {
                Pais pais = paisService.findbyCode(estabelecimento.getPais());
                responseEstabelecimentoDto.setPais(pais != null ? pais.getDescription() : null);
            } catch (Exception e) {
                System.out.println("Erro ao buscar país: " + e.getMessage());
            }

            // Data de Início de Atividade
            try {
                if (estabelecimento.getDt_inicioAtividade() != null && utillsService.isDateValid(estabelecimento.getDt_inicioAtividade())) {
                    String formattedDate = dateFormat.format(estabelecimento.getDt_inicioAtividade());
                    responseEstabelecimentoDto.setDt_inicioAtividade(formattedDate);
                }
            } catch (Exception e) {
                System.out.println("Erro ao formatar data de início de atividade: " + e.getMessage());
            }

            // CNAE
            try {
                var cnae = cnaeService.findByCode(estabelecimento.getCnae());
                if (cnae != null) {
                    responseEstabelecimentoDto.setCnae_code(cnae.getCode());
                    responseEstabelecimentoDto.setCnae_descricao(cnae.getDescricao());
                }
            } catch (Exception e) {
                System.out.println("Erro ao buscar CNAE: " + e.getMessage());
            }

            // Demais atributos
            responseEstabelecimentoDto.setCnae_secundaria(estabelecimento.getCnae_secundaria());
            responseEstabelecimentoDto.setTipo_Logradouro(estabelecimento.getTipo_Logradouro());
            responseEstabelecimentoDto.setLogradouro(estabelecimento.getLogradouro());
            responseEstabelecimentoDto.setNumero(estabelecimento.getNumero());
            responseEstabelecimentoDto.setComplemento(estabelecimento.getComplemento());
            responseEstabelecimentoDto.setBairro(estabelecimento.getBairro());
            responseEstabelecimentoDto.setCep(estabelecimento.getCep());
            responseEstabelecimentoDto.setUf(estabelecimento.getUf());

            // Município
            try {
                Municipio municipio = municipioService.findByCode(estabelecimento.getMunicipio());
                responseEstabelecimentoDto.setMunicipio(municipio != null ? municipio.getDescription() : null);
            } catch (Exception e) {
                System.out.println("Erro ao buscar município: " + e.getMessage());
            }

            responseEstabelecimentoDto.setDdd1(estabelecimento.getDdd1());
            responseEstabelecimentoDto.setTelefone1(estabelecimento.getTelefone1());
            responseEstabelecimentoDto.setDdd2(estabelecimento.getDdd2());
            responseEstabelecimentoDto.setTelefone2(estabelecimento.getTelefone2());
            responseEstabelecimentoDto.setDdd_fax(estabelecimento.getDdd_fax());
            responseEstabelecimentoDto.setFax(estabelecimento.getFax());
            responseEstabelecimentoDto.setEmail(estabelecimento.getEmail());
            responseEstabelecimentoDto.setSituacao_especial(estabelecimento.getSituacao_especial());


            try {
                if (estabelecimento.getDt_situacao_especial() != null && utillsService.isDateValid(estabelecimento.getDt_situacao_especial())) {
                    String formattedDate = dateFormat.format(estabelecimento.getDt_situacao_especial());
                    responseEstabelecimentoDto.setDt_situacao_especial(formattedDate);
                }
            } catch (Exception e) {
                System.out.println("Erro ao formatar data de situação especial: " + e.getMessage());
            }

            estabelecimentoDtos.add(responseEstabelecimentoDto);
        }

        return CompletableFuture.completedFuture(estabelecimentoDtos);
    }




    public ResponsePageble filterEstabelecimento(String cnpjBase, Integer cnae, String nomeFantasia, Integer municipio, Integer situacaoCadastral,int page, int size) {
        Query query = new Query();

        if (cnpjBase != null && !cnpjBase.isEmpty()) {
            query.addCriteria(Criteria.where("cnpjBase").is(cnpjBase));
        }
        if (cnae != null) {
            query.addCriteria(Criteria.where("cnae").is(cnae));
        }
        if (nomeFantasia != null && !nomeFantasia.isEmpty()) {
            query.addCriteria(Criteria.where("nomeFantasia").regex(nomeFantasia, "i"));
        }
        if (municipio != null) {
            query.addCriteria(Criteria.where("municipio").is(municipio));
        }
        if (situacaoCadastral != null) {
            query.addCriteria(Criteria.where("situacaoCadastral").is(situacaoCadastral));
        }

        long total = mongoTemplate.count(query, Estabelecimento.class); // Conta o total de documentos correspondentes
        query.skip((page - 1) * size).limit(size); // Adiciona paginação

        List<Estabelecimento> estabelecimentos = mongoTemplate.find(query, Estabelecimento.class);
        int totalPages = (int) Math.ceil((double) total / size); // Calcula o total de páginas
        return new ResponsePageble("dados pegos com sucesso","OK",page,size,totalPages,estabelecimentos);

    }

}
