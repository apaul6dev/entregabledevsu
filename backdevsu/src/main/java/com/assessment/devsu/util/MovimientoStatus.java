package com.assessment.devsu.util;

public enum MovimientoStatus {
    SALDO_NO_DISPONIBLE("SALDO NO DISPONIBLE");

    private final String status;

    MovimientoStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
