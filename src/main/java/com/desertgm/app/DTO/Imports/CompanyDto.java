package com.desertgm.app.DTO.Imports;

public class CompanyDto {

    private String id;
    private String cnpjBase;
    private String razaoSocial;
    private String naturezaLegal;
    private String qualificacaoResponsavelModel;
    private double capitalSocial;
    private String porteEmpresaTexto;
    private String enteFederativoResponsavel;

    public  CompanyDto(String id, String cnpjBase, String razaoSocial, Long naturezaLegal,
                           Long qualificacaoResponsavelModel, double capitalSocial, Long porteEmpresa,
                           String enteFederativoResponsavel) {
        this.id = id;
        this.cnpjBase = cnpjBase;
        this.razaoSocial = razaoSocial;
        this.naturezaLegal = getNaturezaLegalText(naturezaLegal);
        this.qualificacaoResponsavelModel = getQualificacaoResponsavelText(qualificacaoResponsavelModel);
        this.capitalSocial = capitalSocial;
        this.porteEmpresaTexto = getPorteEmpresaText(porteEmpresa);
        this.enteFederativoResponsavel = enteFederativoResponsavel;
    }

    private String getNaturezaLegalText(Long naturezaLegal) {
        // Implemente a lógica para converter naturezaLegal para texto, se necessário
        return naturezaLegal.toString(); // Exemplo simples
    }

    private String getQualificacaoResponsavelText(Long qualificacaoResponsavelModel) {

        return qualificacaoResponsavelModel.toString();
    }

    private String getPorteEmpresaText(Long porteEmpresa) {
        switch (porteEmpresa.intValue()) {
            case 0: return "NÃO INFORMADO";
            case 1: return "MICRO EMPRESA";
            case 3: return "EMPRESA DE PEQUENO PORTE";
            case 5: return "DEMAIS";
            default: return "Desconhecido";
        }
    }
}
