package com.desafio.biblioteca2.biblioteca2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class Biblioteca2Application {

	public static void main(String[] args) {
		SpringApplication.run(Biblioteca2Application.class, args);
	}

}
