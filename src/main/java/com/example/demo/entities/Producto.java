package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.*;
//Paquete para validaciones con anotacion
import jakarta.validation.constraints.*;

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

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Column(name = "nombre_producto", nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio debe ser mayor a 0")
    private Double precio;
    private String descripcion;

    // Aca tenemos la relacion
    @ManyToOne //Muchos productos pertenecen a UNA SOLA Categoria
    @JoinColumn(name = "categoria_id") //Nombre de la columna (FK) en la tabla productos
    private Categoria categoria;

}
