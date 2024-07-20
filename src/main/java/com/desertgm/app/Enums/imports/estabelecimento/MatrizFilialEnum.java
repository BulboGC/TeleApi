package com.desertgm.app.Enums.imports.estabelecimento;

public enum MatrizFilialEnum {
    MATRIZ(1),
    FILIAL(2);

    private final int value;

    MatrizFilialEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MatrizFilialEnum fromValue(int value) {
        for (MatrizFilialEnum status : MatrizFilialEnum.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }

    @Override
    public String toString() {
        return this.name();
    }
}