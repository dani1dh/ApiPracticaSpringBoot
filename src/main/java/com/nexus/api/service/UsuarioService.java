package com.nexus.api.service;

import com.nexus.api.exception.NotFound;
import com.nexus.api.model.Usuario;
import com.nexus.api.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsuarioService implements  IUsuarioService{

    //Hacemos la inyeccion de dependencias
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> traerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario traerUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(()-> new NotFound("Usuario no encontrado"));
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario modificarUsuario(Long id, Usuario usuario) {
        Usuario usuarioRecibido = usuarioRepository.findById(id).orElseThrow(()->new NotFound("Usuario no encontrado para modificar"));

        usuarioRecibido.setNombre(usuario.getNombre());
        usuarioRecibido.setEdad(usuario.getEdad());

        return usuarioRepository.save(usuarioRecibido);
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
        }else {
            throw new NotFound("Usuario no encontrado para eliminar");
        }
    }
}
