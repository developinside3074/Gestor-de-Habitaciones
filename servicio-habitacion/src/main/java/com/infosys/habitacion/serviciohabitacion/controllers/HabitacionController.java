package com.infosys.habitacion.serviciohabitacion.controllers;


import com.infosys.habitacion.serviciohabitacion.dtos.MensajeGenericoRs;
import com.infosys.habitacion.serviciohabitacion.enums.EstadoHabitacion;
import com.infosys.habitacion.serviciohabitacion.enums.Mensajes;
import com.infosys.habitacion.serviciohabitacion.models.Habitacion;
import com.infosys.habitacion.serviciohabitacion.services.HabitacionService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HabitacionController {

    private final HabitacionService habitacionService;

    @Autowired
    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<Habitacion> listarHabitaciones(){

        return habitacionService.listarHabitaciones();
    }

    @GetMapping("/listar/disponibles")
    public ResponseEntity<?> listarHabitacionesDisponibles(){

        MensajeGenericoRs mensajeGenericoRs = new MensajeGenericoRs();

        List<Habitacion> disponibles = new ArrayList<>();

        habitacionService.listarHabitaciones().forEach(habitacion -> {
                    if(StringUtils.equalsAnyIgnoreCase(habitacion.getEstado(), EstadoHabitacion.LIBRE.getDescriccion()) ){
                        disponibles.add(habitacion);
                    }
                });
        if(CollectionUtils.isNotEmpty(disponibles)){
            return ResponseEntity.ok(disponibles);
        }

        mensajeGenericoRs.setCodigo(Mensajes.HABITACIONES_NO_DISPONIBLES.getCodigo());
        mensajeGenericoRs.setMensaje(Mensajes.HABITACIONES_NO_DISPONIBLES.getDescriccion());
        return ResponseEntity.ok(mensajeGenericoRs);

    }

    @GetMapping("/listar/estados/{descripcion}")
    public ResponseEntity<?> listarHabitacionesPorEstado(@PathVariable String descripcion){

        MensajeGenericoRs mensajeGenericoRs = new MensajeGenericoRs();

        List<Habitacion> habitaciones = new ArrayList<>();

        habitacionService.listarHabitaciones().forEach(habitacion -> {
            if(StringUtils.equalsAnyIgnoreCase(habitacion.getEstado(), descripcion) ){
                habitaciones.add(habitacion);
            }
        });
        if(CollectionUtils.isNotEmpty(habitaciones)){
            return ResponseEntity.ok(habitaciones);
        }

        mensajeGenericoRs.setCodigo(Mensajes.HABITACIONES_NO_DISPONIBLES.getCodigo());
        mensajeGenericoRs.setMensaje(Mensajes.HABITACIONES_NO_DISPONIBLES.getDescriccion());
        return ResponseEntity.ok(mensajeGenericoRs);

    }

    @GetMapping("/listar/tipos/{descripcion}")
    public ResponseEntity<?> listarHabitacionesPorTipo(@PathVariable String descripcion){

        MensajeGenericoRs mensajeGenericoRs = new MensajeGenericoRs();

        List<Habitacion> habitaciones = new ArrayList<>();

        habitacionService.listarHabitaciones().forEach(habitacion -> {
            if(StringUtils.equalsAnyIgnoreCase(habitacion.getTipo(), descripcion) ){
                habitaciones.add(habitacion);
            }
        });

        if(CollectionUtils.isNotEmpty(habitaciones)){
            return ResponseEntity.ok(habitaciones);
        }

        mensajeGenericoRs.setCodigo(Mensajes.HABITACIONES_NO_DISPONIBLES.getCodigo());
        mensajeGenericoRs.setMensaje(Mensajes.HABITACIONES_NO_DISPONIBLES.getDescriccion());
        return ResponseEntity.ok(mensajeGenericoRs);

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        MensajeGenericoRs mensajeGenericoRs = new MensajeGenericoRs();

        Optional<Habitacion> habitacionOptional = Optional.ofNullable(this.habitacionService.buscarPorID(id));

        if(habitacionOptional.isPresent()){
            return ResponseEntity.ok(habitacionOptional.get());
        }
        mensajeGenericoRs.setCodigo(Mensajes.HABITACION_NO_ENCONTRADA.getCodigo());
        mensajeGenericoRs.setMensaje(Mensajes.HABITACION_NO_ENCONTRADA.getDescriccion());
        return ResponseEntity.ok(mensajeGenericoRs);

    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Habitacion crear(@RequestBody Habitacion habitacion){
        return this.habitacionService.guardarHabitacion(habitacion);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Habitacion habitacion){

        MensajeGenericoRs mensajeGenericoRs = new MensajeGenericoRs();

        Optional<Habitacion> habitacionOptional = Optional.ofNullable(this.habitacionService.buscarPorID(id));

        if(habitacionOptional.isPresent()){
            Habitacion habitacionModificada =  this.habitacionService.modificarHabitacion(habitacion, habitacionOptional.get());
            return ResponseEntity.ok(habitacionModificada);
        }

        mensajeGenericoRs.setCodigo(Mensajes.HABITACION_NO_ENCONTRADA.getCodigo());
        mensajeGenericoRs.setMensaje(Mensajes.HABITACION_NO_ENCONTRADA.getDescriccion());
        return ResponseEntity.ok(mensajeGenericoRs);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        MensajeGenericoRs mensajeGenericoRs = new MensajeGenericoRs();

        Optional<Habitacion> habitacionOptional = Optional.ofNullable(this.habitacionService.buscarPorID(id));

        if(habitacionOptional.isPresent()){
            this.habitacionService.eliminarHabitacion(id);
            mensajeGenericoRs.setCodigo(Mensajes.OPERACION_SATISFACTORIA.getCodigo());
            mensajeGenericoRs.setMensaje(Mensajes.OPERACION_SATISFACTORIA.getDescriccion());
            return ResponseEntity.ok(mensajeGenericoRs);
        }

        mensajeGenericoRs.setCodigo(Mensajes.HABITACION_NO_ENCONTRADA.getCodigo());
        mensajeGenericoRs.setMensaje(Mensajes.HABITACION_NO_ENCONTRADA.getDescriccion());
        return ResponseEntity.ok(mensajeGenericoRs);
    }

    @PutMapping("/transicion/{id}/estado/{descriccion}")
    public ResponseEntity<?> transicion(@PathVariable Long id, @PathVariable String descriccion){

        MensajeGenericoRs mensajeGenericoRs = new MensajeGenericoRs();

        Optional<Habitacion> habitacionOptional = Optional.ofNullable(this.habitacionService.buscarPorID(id));

        if(habitacionOptional.isPresent()){
            mensajeGenericoRs =  this.habitacionService.transicionarEstado(id, descriccion);

            if(StringUtils.equalsAnyIgnoreCase(mensajeGenericoRs.getMensaje(), Mensajes.TRANSICION_NO_PERMITIDA.getDescriccion())){
                return ResponseEntity.badRequest().body(mensajeGenericoRs);
            } else {
                return ResponseEntity.ok().body(mensajeGenericoRs);
            }
        }

        mensajeGenericoRs.setCodigo(Mensajes.HABITACION_NO_ENCONTRADA.getCodigo());
        mensajeGenericoRs.setMensaje(Mensajes.HABITACION_NO_ENCONTRADA.getDescriccion());
        return ResponseEntity.ok(mensajeGenericoRs);
    }

    @PutMapping("/reservar/{id}")
    public ResponseEntity<?> reservar(@PathVariable Long id) {

        MensajeGenericoRs mensajeGenericoRs = new MensajeGenericoRs();

        Optional<Habitacion> habitacionOptional = Optional.ofNullable(this.habitacionService.buscarPorID(id));

        if(habitacionOptional.isPresent()){
            mensajeGenericoRs =  this.habitacionService.reservarHabitacion(id);
            return ResponseEntity.ok(mensajeGenericoRs);
        }

        mensajeGenericoRs.setCodigo(Mensajes.HABITACION_NO_ENCONTRADA.getCodigo());
        mensajeGenericoRs.setMensaje(Mensajes.HABITACION_NO_ENCONTRADA.getDescriccion());
        return ResponseEntity.badRequest().body(mensajeGenericoRs);
    }

}
