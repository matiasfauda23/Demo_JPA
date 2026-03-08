package com.example.demo.config;
import com.example.demo.entities.Producto;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class DataSeeder {
    @Bean
    CommandLineRunner initDatabase(ProductoRepository repository) {
        return args -> {
            // Solo insertamos si la base está vacía
            if (repository.count() == 0) {
                repository.save(new Producto(null,"Monitor 144hz", 300.0 , "Ideal para gaming"));
                repository.save(new Producto(null,"Mouse Logi", 50.0 , "Sensor óptico"));
                System.out.println("Base de datos inicializada con productos de prueba.");
            }
        };
    }
}