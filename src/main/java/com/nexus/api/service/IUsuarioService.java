package com.nexus.api.service;

import com.nexus.api.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> traerUsuarios();
    public Usuario traerUsuario(Long id);
    public Usuario crearUsuario(Usuario usuario);
    public Usuario modificarUsuario(Long id, Usuario usuario);
    public void eliminarUsuario(Long id);
}
