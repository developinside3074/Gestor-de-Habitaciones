package com.infosys.habitacion.serviciohabitacion.enums;

public enum EstadoHabitacion {

    OCUPADA("OCUPADA"),
    MANTENIMIENTO("MANTENIMIENTO"),
    LIMPIEZA("LIMPIEZA"),
    LIBRE("LIBRE");

    private final String descriccion;

    EstadoHabitacion(String descriccion) {
        this.descriccion = descriccion;
    }

    public String getDescriccion() {
        return descriccion;
    }
}
