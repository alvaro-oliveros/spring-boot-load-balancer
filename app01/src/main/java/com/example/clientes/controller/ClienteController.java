package com.example.clientes.controller;

import com.example.clientes.model.Cliente;
import com.example.clientes.repository.ClienteRepository;
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
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // READ - Listar todos
    @GetMapping({"/", "/clientes"})
    public String index(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Cliente> clientesPage = clienteRepository.findAll(pageable);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaActual = LocalDateTime.now().format(formatter);

        model.addAttribute("clientes", clientesPage.getContent());
        model.addAttribute("totalClientes", clientesPage.getTotalElements());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", clientesPage.getTotalPages());
        model.addAttribute("fechaActual", fechaActual);
        model.addAttribute("puerto", "5001");
        model.addAttribute("servicio", "App01 - Clientes");

        return "index";
    }

    // CREATE - Mostrar formulario
    @GetMapping({"/nuevo", "/clientes/nuevo"})
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("puerto", "5001");
        return "formulario";
    }

    // CREATE - Guardar nuevo
    @PostMapping({"/guardar", "/clientes/guardar"})
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    // UPDATE - Mostrar formulario edición
    @GetMapping({"/editar/{id}", "/clientes/editar/{id}"})
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        model.addAttribute("cliente", cliente);
        model.addAttribute("puerto", "5001");
        return "formulario";
    }

    // DELETE - Eliminar
    @GetMapping({"/eliminar/{id}", "/clientes/eliminar/{id}"})
    public String eliminarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }
}
