package com.infosys.habitacion.serviciohabitacion.enums;

public enum Mensajes {
    OPERACION_SATISFACTORIA("0000", "Operación satisfactoria."),
    TRANSICION_NO_PERMITIDA("0001", "Operación de transición de estado no permitida."),
    HABITACION_NO_ENCONTRADA("0002", "Habitación no encontrada."),
    HABITACION_NO_DISPONIBLE("0003", "Habitación no se encuentra disponible."),
    HABITACIONES_NO_DISPONIBLES("0003", "No hay habitaciones disponibles");

    private final String codigo;
    private final String descriccion;

    Mensajes(String codigo, String descriccion) {
        this.codigo = codigo;
        this.descriccion = descriccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescriccion() {
        return descriccion;
    }
}
