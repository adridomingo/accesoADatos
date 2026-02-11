package org.iesch.ad.DocumentosReferenciados.service;

import org.iesch.ad.DocumentosReferenciados.modelo.AutoresRef;
import org.iesch.ad.DocumentosReferenciados.repositorio.AutoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorRefService2 {

    @Autowired
    AutoresRepository autoresRepository;

    public List<AutoresRef> getAll() {
        return autoresRepository.findAll();
    }

    public Optional<AutoresRef> getOne(String id) {
        return autoresRepository.findById(id);
    }

    public AutoresRef putOne(AutoresRef autor) {
        return autoresRepository.save(autor);
    }

    public AutoresRef actualizarOne(String id, AutoresRef autor) {
        Optional<AutoresRef> autorBuscar = autoresRepository.findById(id);

        if (autorBuscar.isEmpty()) {
            return null;
        } else {
            autor.setId(id);
            return autoresRepository.save(autor);
        }
    }

    public Boolean borrarOne(String id) {
        if (autoresRepository.existsById(id)) {
            autoresRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
