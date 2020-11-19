package com.infosys.habitacion.serviciohabitacion.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="habitaciones")
public class Habitacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descriccion;
    private String tipo;
    private String estado;

    public Habitacion() {
    }

    public Habitacion(Long id, String descriccion, String tipo, String estado) {
        this.id = id;
        this.descriccion = descriccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getDescriccion() {
        return descriccion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private static final long serialVersionUID = 7831763841573663630L;
}
