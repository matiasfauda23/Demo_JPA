package com.example.demo.controllers;

import com.example.demo.entities.Producto;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return productoService.crearProducto(producto);
    }

    //Obtener producto por ID
    //@PathVariable para que agarre el id de la URL y lo guarde en la variable id de Java
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try {
            //Buscamos el producto, si no lo encuentra lanaza RuntimeException
            Producto producto = productoService.obtenerPorId(id);
            //Si llegamos aca el producto existe, crea un paquete con codigo 200 y mete al producto
            return ResponseEntity.ok(producto);
        }catch (RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    //Filtrar por precio
    @GetMapping("/precio-mayor/{precio}")
    public List<Producto> filtrarPorPrecio(@PathVariable Double precio){
        return productoService.obtenerPorMayorPrecio(precio);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        return productoService.actualizar(id, productoDetalles);
    }

    //Borrar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        try {
            productoService.eliminar(id);
            return ResponseEntity.noContent().build(); // 204 No Content es el exito estandar para borrar
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
