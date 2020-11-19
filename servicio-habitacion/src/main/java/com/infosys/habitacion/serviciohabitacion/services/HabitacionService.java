package com.infosys.habitacion.serviciohabitacion.services;

import com.infosys.habitacion.serviciohabitacion.dtos.MensajeGenericoRs;
import com.infosys.habitacion.serviciohabitacion.models.Habitacion;

import java.util.List;

public interface HabitacionService {

    Habitacion guardarHabitacion(Habitacion habitacion);
    Habitacion modificarHabitacion(Habitacion habitacion, Habitacion habitacionEncontrada);
    void eliminarHabitacion(Long id);
    List<Habitacion> listarHabitaciones();
    MensajeGenericoRs transicionarEstado(Long id, String estado);
    MensajeGenericoRs reservarHabitacion(Long id);
    Habitacion buscarPorID(Long id);

}
