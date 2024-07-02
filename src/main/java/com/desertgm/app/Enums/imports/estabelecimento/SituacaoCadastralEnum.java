package com.desertgm.app.Enums.imports.estabelecimento;

public enum SituacaoCadastralEnum {

    NULA(1),
    ATIVA(2),
    SUSPENSA(3),
    INAPTA(4),
    BAIXADA(8);

    private final int codigo;

    SituacaoCadastralEnum(int codigo) {
        this.codigo = codigo;
    }
    public int getCodigo() {
        return codigo;
    }

    public static SituacaoCadastralEnum fromCodigo(int codigo) {
        for (SituacaoCadastralEnum situacao : values()) {
            if (situacao.getCodigo() == codigo) {
                return situacao;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

    @Override
    public String toString() {
        return name();
    }
}
