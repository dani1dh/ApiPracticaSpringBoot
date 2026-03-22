package com.nexus.api.service;

import com.nexus.api.exception.NotFound;
import com.nexus.api.model.Tarea;
import com.nexus.api.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService implements ITareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Tarea traerTarea(Long id) {

        return tareaRepository.findById(id).orElseThrow(()-> new NotFound("No se encontro la tarea"));
    }

    @Override
    public Tarea crearTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public Tarea modificarTarea(Long id, Tarea tarea) {
        Tarea tareaCreada = tareaRepository.findById(id).orElseThrow(()-> new NotFound("No se encontro la tarea a modificar"));

        //Se setean los datos
        tareaCreada.setTitulo(tarea.getTitulo());
        tareaCreada.setFecha(tarea.getFecha());
        tareaCreada.setCompletado(tarea.isCompletado());

        return tareaRepository.save(tareaCreada);
    }

    @Override
    public void eliminarTarea(Long id) {
        if (tareaRepository.existsById(id)){
            tareaRepository.deleteById(id);
        }else {
            throw new NotFound("Tarea no encontrada para eliminar");
        }
    }
}
