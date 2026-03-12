package com.example.demo.services;

import com.example.demo.entities.Producto;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//Comentario general:  2) Aca vive la logica de negocio, es codigo Java puro.

@Service //Indicamos que aqui vive la logica de negocio

public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    //Recupero lista de productos de la bd
    public List<Producto> obtenerTodos(){

        return productoRepository.findAll();
    }

    //Obtener producto por ID de la bd
    public Producto obtenerPorId(Long id){

        return productoRepository.findById(id).orElse(null);
    }

    //Busqueda personalizada usando metodos de JPA
    public List<Producto> obtenerPorMayorPrecio(Double precio){
        return productoRepository.findByPrecioGreaterThan(precio);
    }

    //Logica para que ningun producto ingrese a la DB con precio invalido
    public Producto crearProducto(Producto producto){
        if(producto.getPrecio() < 0){
            throw new RuntimeException("El precio no puede ser negativo");
        }
        return productoRepository.save(producto);
    }

    //Logica para eliminar un producto con su PK
    public void eliminar(Long id){

        productoRepository.deleteById(id);
    }

    //Logica para actualizar un producto
    public Producto actualizar(Long id, Producto productoDetalles) {
        //Busco si el producto existe
        Producto producto = productoRepository.findById(id).orElse(null);

        if (producto != null) {
            //Actualizamos los campos necesarios
            producto.setNombre(productoDetalles.getNombre());
            producto.setPrecio(productoDetalles.getPrecio());
            producto.setDescripcion(productoDetalles.getDescripcion());

            //Guardamos los cambios
            return productoRepository.save(producto);
        }
        return null;
    }

}
