package org.iesch.MongoDemo_Repository.controller;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books/template")
public class BookTemplateController {

    @Autowired
    BookService bookService;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - Consultas basicas
    /**
     * GET /api/books/template/search/titulo?q=titulo
     * Buscar libros por titulo
     */
    @GetMapping("search/titulo")
    public ResponseEntity<List<Book>> buscarPorTitulo(@RequestParam String q) {
        return ResponseEntity.ok(bookService.findByTituloContainingIgnoreCase(q));
    }

    @GetMapping("search/categoria")
    public ResponseEntity<List<Book>> buscarPorCategoria(@RequestParam String cat) {
        return ResponseEntity.ok(bookService.findByCategoriaContainingIgnoreCase(cat));
    }

    @GetMapping("search/autor")
    public ResponseEntity<List<Book>> buscarPorAutor(@RequestParam String nombre) {
        return ResponseEntity.ok(bookService.findByAutoresNombre(nombre));
    }

    @GetMapping("search/precio")
    public ResponseEntity<List<Book>> buscarPorPrecio(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(bookService.findByPrecioEntre(min, max));
    }

    @GetMapping("search/anio")
    public ResponseEntity<List<Book>> buscarPorAnio(@RequestParam Integer anio) {
        return ResponseEntity.ok(bookService.findByAnioReciente(anio));
    }

    @GetMapping("search/precio-anio")
    public ResponseEntity<List<Book>> buscarPorPrecioAnio(@RequestParam Double precio, @RequestParam Integer anio) {
        return ResponseEntity.ok(bookService.findByPrecioAnio(precio, anio));
    }

}
