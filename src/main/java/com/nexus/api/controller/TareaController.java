package com.nexus.api.controller;

import com.nexus.api.exception.NotFound;
import com.nexus.api.model.Tarea;
import com.nexus.api.repository.TareaRepository;
import com.nexus.api.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public ResponseEntity<List<Tarea>> listarTarea() {
        return ResponseEntity.ok(tareaService.listarTareas());
    }

    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        return ResponseEntity.created(URI.create("api/tarea/" + tarea.getId())).body(tareaService.crearTarea(tarea));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> traerTarea(@PathVariable Long id){
        return ResponseEntity.ok(tareaService.traerTarea(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Tarea> modificarTarea(@PathVariable Long id, @RequestBody Tarea tarea){
        return ResponseEntity.ok(tareaService.modificarTarea(id, tarea));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id){

        tareaService.eliminarTarea(id);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> handleNotFound(NotFound ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
