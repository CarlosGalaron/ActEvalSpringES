package com.example.gestion_proyectos.controller;

import com.example.gestion_proyectos.model.Tarea;
import com.example.gestion_proyectos.service.TareaService;
import com.example.gestion_proyectos.service.ProyectoService;
import com.example.gestion_proyectos.model.Proyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Autowired
    private ProyectoService proyectoService;

    // Formulario para crear una nueva tarea
    @GetMapping("/crear/{proyectoId}")
    public String mostrarFormularioTarea(@PathVariable Long proyectoId, Model model) {
        Optional<Proyecto> proyecto = proyectoService.obtenerPorId(proyectoId);
        if (proyecto.isPresent()) {
            model.addAttribute("proyecto", proyecto.get());
            model.addAttribute("tarea", new Tarea());
            return "tareas/crear";
        }
        return "redirect:/proyectos";
    }

    @PostMapping("/guardar")
    public String guardarTarea(@ModelAttribute Tarea tarea, @RequestParam Long proyectoId) {
        Optional<Proyecto> proyecto = proyectoService.obtenerPorId(proyectoId);
        if (proyecto.isPresent()) {
            tarea.setProyecto(proyecto.get());  // Asigna el proyecto a la tarea
            tareaService.guardar(tarea);
        }
        return "redirect:/proyectos";
    }
    

    @GetMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        Optional<Tarea> tarea = tareaService.obtenerPorId(id);
        if (tarea.isPresent()) {
            Long proyectoId = tarea.get().getProyecto().getId();
            tareaService.eliminar(id);
            return "redirect:/proyectos/" + proyectoId;
        }
        return "redirect:/proyectos";
    }

    @GetMapping("/proyecto/{proyectoId}")
    public String listarTareas(@PathVariable Long proyectoId, Model model) {
        Optional<Proyecto> proyecto = proyectoService.obtenerPorId(proyectoId);
        if (proyecto.isPresent()) {
            model.addAttribute("proyecto", proyecto.get());
            model.addAttribute("tareas", proyecto.get().getTareas());
            return "tareas/lista";
        }
        return "redirect:/proyectos";
    }

}
