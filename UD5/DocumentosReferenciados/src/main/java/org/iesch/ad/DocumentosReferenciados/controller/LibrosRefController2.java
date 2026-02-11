package org.iesch.ad.DocumentosReferenciados.controller;

import org.iesch.ad.DocumentosReferenciados.modelo.AutoresRef;
import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.iesch.ad.DocumentosReferenciados.service.BookRefService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api2/libros")
public class LibrosRefController2 {
    
    @Autowired
    BookRefService2 bookRefService2;

    @GetMapping
    public ResponseEntity<List<BookRef>> getAll() {
        return ResponseEntity.ok(bookRefService2.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookRef> getOne(@PathVariable String id) {
        Optional<BookRef> autor = bookRefService2.getOne(id);
        return autor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<BookRef> putOne(@RequestBody BookRef autor){
        BookRef BookRef = bookRefService2.putOne(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(BookRef);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<BookRef> actualizar(@PathVariable String id, @RequestBody BookRef autor) {
        BookRef autorActualizado = bookRefService2.actualizarOne(id,autor);

        if (autorActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autorActualizado);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<BookRef> borrar(@PathVariable String id) {
        Boolean autor = bookRefService2.borrarOne(id);
        if (autor) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar/titulo-autores")
    public ResponseEntity<BookRef> buscarTituloAutores(@RequestParam String titulo) {
        return ResponseEntity.ok(bookRefService2.buscarTituloAutores(titulo));
    }

    @GetMapping("buscar/nombre-autor")
    public ResponseEntity<List<BookRef>> buscarLibroNombreAutor(@RequestParam String nombre) {
        return ResponseEntity.ok(bookRefService2.buscarLibroNombreAutor(nombre));
    }
}
