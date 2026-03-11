package com.example.demo.repositories;

import com.example.demo.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

//Comentario general:  3) Su funcion es ejecutar las consultas SQL (insert,Select,Delete).

@Repository //Le inidicamos a Spring que este es un compponente de acceso a datos
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //Aca dentro gracias a JPARepository tenemos todos los metodos basicos (save,delete. etc)
    //Podemos crear metodos personalizados solo con el nombre (Query Methods)

    //Spring entiende que quiero buscar por nombre exacto
    List<Producto> findByNombre(String nombre);

    //Spring entiende que buscamos productos que contengan una palabra
    List<Producto> findByNombreContaining(String palabra);

    List<Producto> findByPrecioGreaterThan(Double precio);

}
