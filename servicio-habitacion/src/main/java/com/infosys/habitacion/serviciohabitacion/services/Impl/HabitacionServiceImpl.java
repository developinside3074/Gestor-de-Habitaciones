package com.infosys.habitacion.serviciohabitacion.services.Impl;

import com.infosys.habitacion.serviciohabitacion.dtos.MensajeGenericoRs;
import com.infosys.habitacion.serviciohabitacion.enums.EstadoHabitacion;
import com.infosys.habitacion.serviciohabitacion.enums.Mensajes;
import com.infosys.habitacion.serviciohabitacion.models.Habitacion;
import com.infosys.habitacion.serviciohabitacion.repository.HabitacionDao;
import com.infosys.habitacion.serviciohabitacion.services.HabitacionService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HabitacionServiceImpl implements HabitacionService {

    private final HabitacionDao habitacionDao;

    @Autowired
    public HabitacionServiceImpl(HabitacionDao habitacionDao) {
        this.habitacionDao = habitacionDao;
    }

    @Override
    @Transactional
    public Habitacion guardarHabitacion(Habitacion habitacion) {
        return habitacionDao.save(habitacion);
    }

    @Override
    @Transactional
    public Habitacion modificarHabitacion(Habitacion habitacion, Habitacion habitacionEncontrada) {
        return this.habitacionDao.save(habitacion);
    }

    @Override
    @Transactional
    public void eliminarHabitacion(Long id) {
        habitacionDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Habitacion> listarHabitaciones() {
        return (List<Habitacion>) habitacionDao.findAll();
    }

    @Override
    @Transactional
    public MensajeGenericoRs transicionarEstado(Long id, String estado) {

        MensajeGenericoRs mensajeGenericoRs = new MensajeGenericoRs();
        Optional<Habitacion> habitacionOptional = Optional.ofNullable(buscarPorID(id));

        if(habitacionOptional.isPresent()){

            Boolean transicion_de_estado = realizarTransicion(habitacionOptional.get().getEstado(), estado, habitacionOptional);

            if(transicion_de_estado){
                mensajeGenericoRs.setCodigo(Mensajes.OPERACION_SATISFACTORIA.getCodigo());
                mensajeGenericoRs.setMensaje(Mensajes.OPERACION_SATISFACTORIA.getDescriccion());
            } else {
                mensajeGenericoRs.setCodigo(Mensajes.TRANSICION_NO_PERMITIDA.getCodigo());
                mensajeGenericoRs.setMensaje(Mensajes.TRANSICION_NO_PERMITIDA.getDescriccion());
            }
            return mensajeGenericoRs;
        }

        mensajeGenericoRs.setCodigo(Mensajes.HABITACION_NO_ENCONTRADA.getCodigo());
        mensajeGenericoRs.setMensaje(Mensajes.HABITACION_NO_ENCONTRADA.getDescriccion());

        return mensajeGenericoRs;
    }

    private Boolean realizarTransicion(String estado, String estado1, Optional<Habitacion> habitacionOptional) {

        // libre --> ocupada
        if(StringUtils.equalsAnyIgnoreCase(estado, EstadoHabitacion.LIBRE.getDescriccion()) && StringUtils.equalsAnyIgnoreCase(estado1, EstadoHabitacion.OCUPADA.getDescriccion())){
            habitacionOptional.get().setEstado(estado1);
            this.habitacionDao.save(habitacionOptional.get());
            return true;
        }

        // libre --> mantenimiento
        if(StringUtils.equalsAnyIgnoreCase(estado, EstadoHabitacion.LIBRE.getDescriccion()) && StringUtils.equalsAnyIgnoreCase(estado1, EstadoHabitacion.MANTENIMIENTO.getDescriccion())){
            habitacionOptional.get().setEstado(estado1);
            this.habitacionDao.save(habitacionOptional.get());
            return true;
        }

        // ocupada --> mantenimiento
        if(StringUtils.equalsAnyIgnoreCase(estado, EstadoHabitacion.OCUPADA.getDescriccion()) && StringUtils.equalsAnyIgnoreCase(estado1, EstadoHabitacion.MANTENIMIENTO.getDescriccion())){
            habitacionOptional.get().setEstado(estado1);
            this.habitacionDao.save(habitacionOptional.get());
            return true;
        }

        // ocupada --> limpieza
        if(StringUtils.equalsAnyIgnoreCase(estado, EstadoHabitacion.OCUPADA.getDescriccion()) && StringUtils.equalsAnyIgnoreCase(estado1, EstadoHabitacion.LIMPIEZA.getDescriccion())){
            habitacionOptional.get().setEstado(estado1);
            this.habitacionDao.save(habitacionOptional.get());
            return true;
        }

        // limpieza --> libre
        if(StringUtils.equalsAnyIgnoreCase(estado, EstadoHabitacion.LIMPIEZA.getDescriccion()) && StringUtils.equalsAnyIgnoreCase(estado1, EstadoHabitacion.LIBRE.getDescriccion())){
            habitacionOptional.get().setEstado(estado1);
            this.habitacionDao.save(habitacionOptional.get());
            return true;
        }

        // limpieza --> mantenimiento
        if(StringUtils.equalsAnyIgnoreCase(estado, EstadoHabitacion.LIMPIEZA.getDescriccion()) && StringUtils.equalsAnyIgnoreCase(estado1, EstadoHabitacion.MANTENIMIENTO.getDescriccion())){
            habitacionOptional.get().setEstado(estado1);
            this.habitacionDao.save(habitacionOptional.get());
            return true;
        }

        // mantenimiento --> libre
        if(StringUtils.equalsAnyIgnoreCase(estado, EstadoHabitacion.MANTENIMIENTO.getDescriccion()) && StringUtils.equalsAnyIgnoreCase(estado1, EstadoHabitacion.LIBRE.getDescriccion())){
            habitacionOptional.get().setEstado(estado1);
            this.habitacionDao.save(habitacionOptional.get());
            return true;
        }

        // mantenimiento --> limpieza
        if(StringUtils.equalsAnyIgnoreCase(estado, EstadoHabitacion.MANTENIMIENTO.getDescriccion()) && StringUtils.equalsAnyIgnoreCase(estado1, EstadoHabitacion.LIBRE.getDescriccion())){
            habitacionOptional.get().setEstado(estado1);
            this.habitacionDao.save(habitacionOptional.get());
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public MensajeGenericoRs reservarHabitacion(Long id) {

        MensajeGenericoRs mensajeGenericoRs = new MensajeGenericoRs();
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        List<Habitacion> totalDeHabitaciones = (List<Habitacion>) this.habitacionDao.findAll();

        totalDeHabitaciones.forEach(habitacion -> {
                    if(StringUtils.equalsAnyIgnoreCase(habitacion.getEstado(), EstadoHabitacion.LIBRE.getDescriccion())){
                        habitacionesDisponibles.add(habitacion);
                    }
                });

        if(CollectionUtils.isNotEmpty(habitacionesDisponibles)){

            Optional<Habitacion> habitacionOptional = this.habitacionDao.findById(id);

            if(habitacionOptional.isPresent()){
                if(StringUtils.equalsAnyIgnoreCase(habitacionOptional.get().getEstado(), EstadoHabitacion.LIBRE.getDescriccion())){
                    habitacionOptional.get().setEstado(EstadoHabitacion.OCUPADA.getDescriccion());
                    this.habitacionDao.save(habitacionOptional.get());
                    mensajeGenericoRs.setCodigo(Mensajes.OPERACION_SATISFACTORIA.getCodigo());
                    mensajeGenericoRs.setMensaje(Mensajes.OPERACION_SATISFACTORIA.getDescriccion());
                } else {
                    mensajeGenericoRs.setCodigo(Mensajes.HABITACION_NO_DISPONIBLE.getCodigo());
                    mensajeGenericoRs.setMensaje(Mensajes.HABITACION_NO_DISPONIBLE.getDescriccion());
                }
            } else {
                mensajeGenericoRs.setCodigo(Mensajes.HABITACION_NO_ENCONTRADA.getCodigo());
                mensajeGenericoRs.setMensaje(Mensajes.HABITACION_NO_ENCONTRADA.getDescriccion());
            }
            return mensajeGenericoRs;
        }

        mensajeGenericoRs.setCodigo(Mensajes.HABITACIONES_NO_DISPONIBLES.getCodigo());
        mensajeGenericoRs.setMensaje(Mensajes.HABITACIONES_NO_DISPONIBLES.getDescriccion());
        return mensajeGenericoRs;
    }

    @Override
    @Transactional(readOnly = true)
    public Habitacion buscarPorID(Long id) {
        return habitacionDao.findById(id).orElse(null);
    }
}
