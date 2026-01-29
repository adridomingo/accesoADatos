package org.iesch.ad.DocumentosReferenciados.controller;

import org.iesch.ad.DocumentosReferenciados.modelo.AutoresRef;
import org.iesch.ad.DocumentosReferenciados.repositorio.AutoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/autores")
public class AutorRefController {

    @Autowired
    AutoresRepository autoresRepository;

    @GetMapping
    public ResponseEntity<List<AutoresRef>> getAllAutores() {
        return ResponseEntity.ok(autoresRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoresRef> getOne(@PathVariable String id) {
        Optional<AutoresRef> autor = autoresRepository.findById(id);
        return autor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AutoresRef> crearAutor(@RequestBody AutoresRef autor) {
        AutoresRef autoresRef = autoresRepository.save(autor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutoresRef> updateAutor(@PathVariable String id, @RequestBody AutoresRef autor) {
        if (!autoresRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        autor.setId(id);
        AutoresRef autoresRefSave = autoresRepository.save(autor);
        return ResponseEntity.ok(autoresRefSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAutor(@PathVariable String id) {
        if (!autoresRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        autoresRepository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}
