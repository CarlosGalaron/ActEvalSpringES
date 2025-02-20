package com.example.gestion_proyectos.repository;

import com.example.gestion_proyectos.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByProyectoId(Long proyectoId); // Obtener todas las tareas de un proyecto
}
