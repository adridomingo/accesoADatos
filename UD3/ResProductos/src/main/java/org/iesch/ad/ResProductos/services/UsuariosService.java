package org.iesch.ad.ResProductos.services;

import org.iesch.ad.ResProductos.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class UsuariosService {

    @Autowired
    Map<Long, Usuario> usuarios;

    public Usuario addUser(Usuario usuario1) {
        Long maxKey = Collections.max(usuarios.keySet());
        usuario1.setId(maxKey + 1);
        usuarios.put(maxKey+ 1, usuario1);
        return usuario1;
    }
}
