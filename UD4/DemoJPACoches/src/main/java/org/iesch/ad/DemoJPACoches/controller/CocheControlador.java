package org.iesch.ad.DemoJPACoches.controller;

import org.iesch.ad.DemoJPACoches.modelo.Coche;
import org.iesch.ad.DemoJPACoches.service.CocheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coches")
public class CocheControlador {

    @Autowired
    CocheService cocheService;

    @PostMapping
    public ResponseEntity<Coche> guardar(@RequestBody Coche coche) {

        Coche cocheGuardado = cocheService.guardar(coche);

        return ResponseEntity.ok(cocheGuardado);
    }

    @GetMapping
    public ResponseEntity<List<Coche>> obtenerTodos() {
        return ResponseEntity.ok(cocheService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coche> obtenerUno(@PathVariable Long id) {
        return ResponseEntity.ok(cocheService.obtenerUno(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coche> updateOne(@PathVariable Long id, @RequestBody Coche coche) {
        return ResponseEntity.ok(cocheService.updateUno(id, coche));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borraUno(@PathVariable Long id) {
        Coche cocheBorrado = cocheService.borraUno(id);
        if (cocheBorrado != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
