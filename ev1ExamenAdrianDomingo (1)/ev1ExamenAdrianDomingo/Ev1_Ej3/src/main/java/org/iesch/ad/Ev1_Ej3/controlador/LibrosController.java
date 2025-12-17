package org.iesch.ad.Ev1_Ej3.controlador;

import org.iesch.ad.Ev1_Ej3.model.Libro;
import org.iesch.ad.Ev1_Ej3.service.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibrosController {

    @Autowired
    LibrosService librosService;

    @GetMapping("/clientes")
    public ResponseEntity<?> mostrarLibros() {
        List<Libro> libros = librosService.mostrarLibros();

        if (libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libros);
    }

//    @GetMapping("/api/librosPorEditorial?editorial={editorial}")
//    public ResponseEntity<?> librosPorEditorial(@RequestParam String editorial) {
//        List<Libro> libros = librosService.librosPorEditorial(editorial);
//
//        if (libros.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(libros);
//    }

    @GetMapping("/api/librosPorEditorial")
    public ResponseEntity<?> librosPorEditorial(@RequestParam("editorial") String editorial) {
        List<Libro> libros = librosService.librosPorEditorial(editorial);

        if (libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libros);
    }

}
