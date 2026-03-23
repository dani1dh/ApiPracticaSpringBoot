package com.nexus.api.controller;

import com.nexus.api.exception.NotFound;
import com.nexus.api.model.Usuario;
import com.nexus.api.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> traerUsuarios(){
        return ResponseEntity.ok(usuarioService.traerUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> traerUsuario(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.traerUsuario(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.created(URI.create("/api/usuario" + usuario.getId())).body(usuarioService.crearUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.modificarUsuario(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> handleNotFound(NotFound ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
