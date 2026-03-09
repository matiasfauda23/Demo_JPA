package com.example.demo.controllers;

import com.example.demo.entities.Producto;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Indica que esta clase es un punto de entrada para peticiones HTTP
@RequestMapping("/productos") //La url base va a ser http://localhost:8080/productos

public class ProductoController {

    @Autowired //Le decimos a spring que busque el repositorio creado y que lo ponga aca para poder usarlo
    private ProductoRepository productoRepository;

    //GetMapping lo uso para lectura, leemos los productos de la db
    @GetMapping
    public List<Producto> obtenerTodos(){
        return productoRepository.findAll();
    }

    //Obtener producto por ID
    //@PathVariable para que agarre el id de la URL y lo guarde en la variable id de Java
    @GetMapping("/{id}")
    public  Producto obtenerPorId(@PathVariable Long id){
        return productoRepository.findById(id).orElse(null);
    }

    //PostMapping lo usamos para crear, aqui creamos un producto
    //@RequestBody le dice a Spring que mire el cuerpo del mensaje, agarre ese JSON y lo convierte en un objeto Producto
    @PostMapping
    public Producto crear(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }
    @PutMapping("/{id}")
    public Producto  actualizar (@PathVariable Long id, @RequestBody Producto productoDetalles) {
        //Buscamos el producto que ya existe
        Producto producto = productoRepository.findById(id).orElse(null);

        if (producto != null) {
            producto.setNombre(productoDetalles.getNombre());
            producto.setPrecio(productoDetalles.getPrecio());
            producto.setDescripcion(productoDetalles.getDescripcion());
            return productoRepository.save(producto);
        }
        return null;
    }

    //Borrar producto
    @DeleteMapping("{id}")
    public void borrar(@PathVariable Long id){
        productoRepository.deleteById(id);
    }

}
