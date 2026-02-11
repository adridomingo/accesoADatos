package org.iesch.ad.DocumentosReferenciados.service;

import org.iesch.ad.DocumentosReferenciados.modelo.AutoresRef;
import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.iesch.ad.DocumentosReferenciados.repositorio.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookRefService2 {
    
    @Autowired
    BookRepository bookRepository;

    public List<BookRef> getAll() {
        return bookRepository.findAll();
    }

    public Optional<BookRef> getOne(String id) {
        return bookRepository.findById(id);
    }

    public BookRef putOne(BookRef autor) {
        return bookRepository.save(autor);
    }

    public BookRef actualizarOne(String id, BookRef autor) {
        Optional<BookRef> autorBuscar = bookRepository.findById(id);

        if (autorBuscar.isEmpty()) {
            return null;
        } else {
            autor.setId(id);
            return bookRepository.save(autor);
        }
    }

    public Boolean borrarOne(String id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public BookRef buscarTituloAutores(String titulo) {
        return bookRepository.findByTitulo(titulo);
    }

    public List<BookRef> buscarLibroNombreAutor(String nombre) {
        return bookRepository.findByNombreAutores(nombre);
    }
}
