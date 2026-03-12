package com.example.demo.config;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Producto;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class DataSeeder {
    @Bean
    CommandLineRunner initDatabase(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        return args -> {
            // Al dejarlo vacío, no se inserta nada, pero no borras la estructura
            System.out.println("Base de datos lista (sin inserciones automáticas)");
        };
    }
}