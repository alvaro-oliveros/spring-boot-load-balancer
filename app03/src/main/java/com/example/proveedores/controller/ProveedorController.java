package com.example.proveedores.controller;

import com.example.proveedores.model.Proveedor;
import com.example.proveedores.repository.ProveedorRepository;
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
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // READ - Listar todos
    @GetMapping({"/", "/proveedores"})
    public String index(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Proveedor> proveedoresPage = proveedorRepository.findAll(pageable);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaActual = LocalDateTime.now().format(formatter);

        model.addAttribute("proveedores", proveedoresPage.getContent());
        model.addAttribute("totalProveedores", proveedoresPage.getTotalElements());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", proveedoresPage.getTotalPages());
        model.addAttribute("fechaActual", fechaActual);
        model.addAttribute("puerto", "7001");
        model.addAttribute("servicio", "App03 - Proveedores");

        return "index";
    }

    // CREATE - Mostrar formulario
    @GetMapping({"/nuevo", "/proveedores/nuevo"})
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("puerto", "7001");
        return "formulario";
    }

    // CREATE - Guardar nuevo
    @PostMapping({"/guardar", "/proveedores/guardar"})
    public String guardarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorRepository.save(proveedor);
        return "redirect:/proveedores";
    }

    // UPDATE - Mostrar formulario edición
    @GetMapping({"/editar/{id}", "/proveedores/editar/{id}"})
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("puerto", "7001");
        return "formulario";
    }

    // DELETE - Eliminar
    @GetMapping({"/eliminar/{id}", "/proveedores/eliminar/{id}"})
    public String eliminarProveedor(@PathVariable Long id) {
        proveedorRepository.deleteById(id);
        return "redirect:/proveedores";
    }
}
