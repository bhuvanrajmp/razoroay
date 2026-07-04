package com.MyFirstSpringbootApp.razoroay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RazoroayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RazoroayApplication.class, args);
	}

}
