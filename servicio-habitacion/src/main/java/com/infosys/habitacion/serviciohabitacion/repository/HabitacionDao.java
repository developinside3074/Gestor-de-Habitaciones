package com.infosys.habitacion.serviciohabitacion.repository;

import com.infosys.habitacion.serviciohabitacion.models.Habitacion;
import org.springframework.data.repository.CrudRepository;

public interface HabitacionDao extends CrudRepository<Habitacion, Long> {

}
