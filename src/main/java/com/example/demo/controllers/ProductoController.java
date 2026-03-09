package com.example.demo.controllers;

import com.example.demo.entities.Producto;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Indica que esta clase es un punto de entrada para peticiones HTTP
@RequestMapping("/productos") //La url base va a ser http://localhost:8080/productos

public class ProductoController {

    @Autowired // Le pide a spring que "inyecte" el repo automaticamente
    private ProductoRepository productoRepository;

    //Obtengo los productos de la db
    @GetMapping
    public List<Producto> obtenerTodos(){
        return productoRepository.findAll();
    }
    //Crear un producto
    @PostMapping
    public Producto crear(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    //Obtener producto por ID
    @GetMapping("/{id}")
    public  Producto obtenerPorId(@PathVariable Long id){
        return productoRepository.findById(id).orElse(null);
    }

    //Borrar producto
    @DeleteMapping("{id}")
    public void borrar(@PathVariable Long id){
        productoRepository.deleteById(id);
    }

}
