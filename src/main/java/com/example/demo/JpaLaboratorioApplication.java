package com.example.demo;

import com.example.demo.entities.Producto;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaLaboratorioApplication {

	public static void main(String[] args) {

        SpringApplication.run(JpaLaboratorioApplication.class, args);
	}
}
