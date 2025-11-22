package com.example.productos.controller;

import com.example.productos.model.Producto;
import com.example.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // READ - Listar todos
    @GetMapping({"/", "/productos"})
    public String index(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Producto> productosPage = productoRepository.findAll(pageable);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaActual = LocalDateTime.now().format(formatter);

        model.addAttribute("productos", productosPage.getContent());
        model.addAttribute("totalProductos", productosPage.getTotalElements());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productosPage.getTotalPages());
        model.addAttribute("fechaActual", fechaActual);
        model.addAttribute("puerto", "6001");
        model.addAttribute("servicio", "App02 - Productos");

        return "index";
    }

    // CREATE - Mostrar formulario
    @GetMapping({"/nuevo", "/productos/nuevo"})
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("puerto", "6001");
        return "formulario";
    }

    // CREATE - Guardar nuevo
    @PostMapping({"/guardar", "/productos/guardar"})
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    // UPDATE - Mostrar formulario edición
    @GetMapping({"/editar/{id}", "/productos/editar/{id}"})
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        model.addAttribute("producto", producto);
        model.addAttribute("puerto", "6001");
        return "formulario";
    }

    // DELETE - Eliminar
    @GetMapping({"/eliminar/{id}", "/productos/eliminar/{id}"})
    public String eliminarProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }
}
