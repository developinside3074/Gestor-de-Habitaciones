package com.infosys.habitacion.serviciohabitacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServicioHabitacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioHabitacionApplication.class, args);
	}

}
