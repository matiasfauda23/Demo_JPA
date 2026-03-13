package com.example.demo.controllers;


import com.example.demo.services.ProductoService;
import com.example.demo.entities.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos/web")
public class ProductoWebController {

    @Autowired
    private ProductoService productoService;

    //Listamos los productos de la web
    @GetMapping
    public String listarProductos(Model model) {
        // Obtenemos los productos del service
        model.addAttribute("productos", productoService.obtenerTodos());
        // Esto busca el archivo index.html en /templates
        return "index";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto){
        productoService.crearProducto(producto);
        //Recargar la pagina
        return "redirect:/productos/web";
    }
    // Eliminar desde la web
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/productos/web";
    }
}
