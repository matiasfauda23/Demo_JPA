package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.*;
@Entity //Le dice a JPA que esta clase es una tabla de la db
@Table(name = "productos") //Defino el nombre de la tabla
@Getter // Genera los getters
@Setter // Genera los setters
@NoArgsConstructor // Genera el constructor vacio obligatorio para JPA
@AllArgsConstructor // Genera un constructor con todos los campos
public class Producto {
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //El id se genera solo
    private Long id;

    @Column(name = "nombre_producto", nullable = false, length = 100)
    private String nombre;
    private Double precio;

}
