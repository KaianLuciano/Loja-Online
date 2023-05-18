package com.lojaonline.hermanos.br.models.enums;

public enum Status {
    PENDENTE(1),
    ENVIADO(2),
    ENTREGUE(3);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean isValidValue(int value) {
        for (Status pedido : Status.values()) {
            if (pedido.getValue() == value) {
                return true;
            }
        }
        return false;
    }

}