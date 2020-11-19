package com.infosys.oauth.serviciooauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class ServicioOauthApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ServicioOauthApplication.class, args);
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Override
	public void run(String... args) {
		String password = "123456";

		for (int i = 0; i < 4; i++) {
			String passwordBCrypt = passwordEncode.encode(password);
			System.out.println(passwordBCrypt);
		}
	}
}
