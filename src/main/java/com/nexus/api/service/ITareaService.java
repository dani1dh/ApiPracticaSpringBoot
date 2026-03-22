package com.nexus.api.service;

import com.nexus.api.model.Tarea;

import java.util.List;

public interface ITareaService {

    public List<Tarea> listarTareas();
    public Tarea traerTarea(Long id);
    public Tarea crearTarea(Tarea tarea);
    public Tarea modificarTarea(Long id, Tarea tarea);
    public void  eliminarTarea(Long id);
}
