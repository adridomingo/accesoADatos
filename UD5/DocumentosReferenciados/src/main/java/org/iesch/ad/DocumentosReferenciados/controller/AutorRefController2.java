package org.iesch.ad.DocumentosReferenciados.controller;

import org.iesch.ad.DocumentosReferenciados.modelo.AutoresRef;
import org.iesch.ad.DocumentosReferenciados.repositorio.AutoresRepository;
import org.iesch.ad.DocumentosReferenciados.service.AutorRefService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api2/autores")
public class AutorRefController2 {

    @Autowired
    AutorRefService2 autorRefService2;

    @GetMapping
    public ResponseEntity<List<AutoresRef>> getAll() {
        return ResponseEntity.ok(autorRefService2.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoresRef> getOne(@PathVariable String id) {
        Optional<AutoresRef> autor = autorRefService2.getOne(id);
        return autor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<AutoresRef> putOne(@RequestBody AutoresRef autor){
        AutoresRef autoresRef = autorRefService2.putOne(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autoresRef);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AutoresRef> actualizar(@PathVariable String id, @RequestBody AutoresRef autor) {
        AutoresRef autorActualizado = autorRefService2.actualizarOne(id,autor);

        if (autorActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autorActualizado);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<AutoresRef> borrar(@PathVariable String id) {
        Boolean autor = autorRefService2.borrarOne(id);
        if (autor) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
