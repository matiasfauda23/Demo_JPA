package com.example.demo.controllers;


import com.example.demo.services.ProductoService;
import com.example.demo.entities.Producto;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
//Se comunica con el navegador enviando HTML (pagina web).
@RequestMapping("/productos/web")
public class ProductoWebController {

    @Autowired
    private ProductoService productoService;

    //Listamos los productos de la web
    @GetMapping
    public String listarProductos(Model model) {
        // Obtenemos los productos del service
        model.addAttribute("productos", productoService.obtenerTodos());
        model.addAttribute("producto", new Producto());
        // Esto busca el archivo index.html en /templates
        return "index";
    }

    //Antes de que guarde el producto le decimos a Spring que revise si el producto cumple con las validaciones
    //BindingResult es un objeto que Spring rellena con los "partes de infraccion"
    @PostMapping("/guardar")
    public String guardarProducto(@Valid @ModelAttribute Producto producto, BindingResult result, Model model){
        //Hubo errores en la validacion?
        if(result.hasErrors()){
            //Si hay error,mostramos la lista para que no se vea vacia
            model.addAttribute("Productos", productoService.obtenerTodos());
            return "index";
        }
        //Si no hay error guardo normalmente
        productoService.crearProducto(producto);
        return "redirect:/productos/web";
    }
    // Eliminar desde la web
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/productos/web";
    }
}
