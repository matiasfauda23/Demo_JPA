package com.example.demo.controllers;

import com.example.demo.entities.Producto;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Comentario general:  1) El controlador habla con el cliente, recibe el pedido, valida que el formato
// Sea JSON y entrega la respuesta final, recibe un pedido y se lo para al Service para que el vea que hacer.

@RestController //Indica que devuelve datos JSON
@RequestMapping("/productos") //La url base va a ser http://localhost:8080/productos

public class ProductoController {

    @Autowired
    private ProductoService productoService;

    //GetMapping lo uso para lectura, leemos los productos de la db
    @GetMapping
    public List<Producto> obtenerTodos(){
        return productoService.obtenerTodos();
    }

    //PostMapping lo usamos para crear, aqui creamos un producto
    //@RequestBody le dice a Spring que mire el cuerpo del mensaje, agarre ese JSON y lo convierte en un objeto Producto
    @PostMapping
    public Producto crear(@RequestBody Producto producto){
        return productoService.guardar(producto);
    }

    //Obtener producto por ID
    //@PathVariable para que agarre el id de la URL y lo guarde en la variable id de Java
    @GetMapping("/{id}")
    public  Producto obtenerPorId(@PathVariable Long id){
        return productoService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Producto  actualizar (@PathVariable Long id, @RequestBody Producto productoDetalles) {
        //Buscamos el producto que ya existe
        Producto producto = productoService.obtenerPorId(id);

        if (producto != null) {
            producto.setNombre(productoDetalles.getNombre());
            producto.setPrecio(productoDetalles.getPrecio());
            producto.setDescripcion(productoDetalles.getDescripcion());
            return productoService.guardar(producto);
        }
        return null;
    }

    //Borrar producto
    @DeleteMapping("{id}")
    public void borrar(@PathVariable Long id){
        productoService.eliminar(id);
    }

}
