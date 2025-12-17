package org.iesch.ad.Ev1_Ej3.service;

import org.iesch.ad.Ev1_Ej3.model.Libro;
import org.iesch.ad.Ev1_Ej3.repositorio.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrosService {

    @Autowired
    LibrosRepository librosRepository;

    public List<Libro> mostrarLibros() {
        return librosRepository.findAll();
    }

//    public List<Libro> librosPorEditorial(String editorial) {
//        return librosRepository.findByEditorial_Id(editorial);
//    }

    public List<Libro> librosPorEditorial(String editorial) {
        return librosRepository.findByEditorialNombre(editorial);
    }
}
