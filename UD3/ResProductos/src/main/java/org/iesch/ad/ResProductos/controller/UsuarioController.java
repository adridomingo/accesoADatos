package org.iesch.ad.ResProductos.controller;

import org.iesch.ad.ResProductos.modelo.Usuario;
import org.iesch.ad.ResProductos.modelo.UsuarioDTORespuesta;
import org.iesch.ad.ResProductos.modelo.UsuarioDTOpeticion;
import org.iesch.ad.ResProductos.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RestController
public class UsuarioController {

    @Autowired
    Map<Long, Usuario> usuarios;

    @Autowired
    UsuariosService usuariosService;

    @PostMapping("/usuario")
    public ResponseEntity<?> registra(@RequestBody UsuarioDTOpeticion usuarioDTOpeticion) {
        Usuario user = Usuario.builder().nombre(usuarioDTOpeticion.getNombre()).build();
        Usuario usuario1 = usuariosService.addUser(user);
        URI location = URI.create("/usuario/"+usuario1.getId());
        UsuarioDTORespuesta usuarioDTORespuesta = UsuarioDTORespuesta.builder().nombre(usuario1.getNombre()).build();
        return ResponseEntity.created(location).body(usuarioDTORespuesta);
    }
}
