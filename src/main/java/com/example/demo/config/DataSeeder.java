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
            System.out.println(">>> SEEDER: Iniciando la carga de datos...");
            // 1. Creamos y guardamos la Categoria
            Categoria c1 = new Categoria(null, "Electronica");
            Categoria c2 = new Categoria(null,"Hogar");
            categoriaRepository.save(c1);
            categoriaRepository.save(c2);

            // 2. Ahora creamos los productos asociados a esa categorias
            Producto p1 = new Producto();
            p1.setNombre("Monitor 144 hz");
            p1.setPrecio(300.0);
            p1.setDescripcion("Ideal para jugar");
            p1.setCategoria(c1); //Aca le asocio la categoria

            Producto p2 = new Producto();
            p2.setNombre("Silla Oficina");
            p2.setPrecio(150.0);
            p2.setDescripcion("Ergonomica");
            p2.setCategoria(c2);

            productoRepository.save(p1);
            productoRepository.save(p2);

            System.out.println("Base de daatos inicializada con categoria y productos");
        };
    }
}