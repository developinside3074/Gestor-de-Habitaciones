package com.infosys.habitacion.serviciohabitacion.dtos;

import java.io.Serializable;

public class MensajeGenericoRs implements Serializable {

    private String codigo;
    private String mensaje;

    public MensajeGenericoRs() {
    }

    public MensajeGenericoRs(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    private static final long serialVersionUID = -8624434316561815472L;
}
