package com.example.gestion_proyectos.controller;

import com.example.gestion_proyectos.model.Proyecto;
import com.example.gestion_proyectos.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public String listarProyectos(Model model) {
        model.addAttribute("proyectos", proyectoService.obtenerTodos());
        return "proyectos/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "proyectos/formulario";
    }

    @PostMapping
    public String guardarProyecto(@ModelAttribute Proyecto proyecto, BindingResult result, @RequestParam("fechaInicio") String fechaInicioStr) {
        try {
            // Convertir la fecha manualmente desde String a Date (dd/MM/yyyy)
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaInicio = formatter.parse(fechaInicioStr);
            proyecto.setFechaInicio(fechaInicio);
        } catch (ParseException e) {
            result.rejectValue("fechaInicio", "error.proyecto", "Formato de fecha inválido");
            return "proyectos/formulario";
        }

        if (result.hasErrors()) {
            return "proyectos/formulario";
        }

        proyectoService.guardar(proyecto);
        return "redirect:/proyectos";
    }

    @GetMapping("/{id}")
    public String verDetalleProyecto(@PathVariable Long id, Model model) {
        Optional<Proyecto> proyecto = proyectoService.obtenerPorId(id);
        if (proyecto.isPresent()) {
            model.addAttribute("proyecto", proyecto.get());
            return "proyectos/detalle";
        }
        return "redirect:/proyectos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminar(id);
        return "redirect:/proyectos";
    }
}
