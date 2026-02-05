package org.iesch.ad.DocumentosReferenciados.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.iesch.ad.DocumentosReferenciados.service.BookRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="BookRefTemplateController", description = "Api para la gestion de libros referenciados")
@RestController
@RequestMapping("api/books/template")
public class BookRefTemplateController {

    @Autowired
    BookRefService bookRefService;

    @Operation(summary = "Obtener todos los libros",
    description = "Devuelve la lista completa de libros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encuentra libros correctamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @GetMapping
    public ResponseEntity<List<BookRef>> getAll() {
        return ResponseEntity.ok(bookRefService.buscaTodos());
    }

    @GetMapping("/search/titulo")
    public ResponseEntity<List<BookRef>> busarPorTitulo(@RequestParam String q) {
        return ResponseEntity.ok(bookRefService.findByTituloContainingIgnoreCase(q));
    }

    @GetMapping("/search/autor/{autorId}")
    public ResponseEntity<List<BookRef>> buscarPorAutorId(@PathVariable String autorId) {
        return ResponseEntity.ok(bookRefService.findByAutorId(autorId));
    }

    @Operation(summary = "Obtener el libro pasado id",
            description = "Devuelve un libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encuentra libro correctamente")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookRef> getOne(@PathVariable String id) {
        return ResponseEntity.ok(bookRefService.buscaOne(id));
    }

    @GetMapping("/search/autor-nombre")
    public ResponseEntity<List<BookRef>> buscarPorNombre(@RequestParam String autorNombre) {
        return ResponseEntity.ok(bookRefService.findByAutorNombre(autorNombre));
    }
}
