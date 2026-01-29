package org.iesch.MongoDemo_Repository.controller;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("search/titulo-categoria")
    public ResponseEntity<List<Book>> buscarTituloYCategoria(@RequestParam String titulo, @RequestParam String cat) {
        return ResponseEntity.ok(bookService.findByTituloYCategoria(titulo, cat));
    }

    @PostMapping("search/categorias-multiples")
    public ResponseEntity<List<Book>> buscarCategoriasMultiples(@RequestBody List<String> categorias) {
        return ResponseEntity.ok(bookService.findByCategoriasMultiples(categorias));
    }

    @GetMapping("search/precio-maximo-ordenado")
    public ResponseEntity<List<Book>> buscarPorPrecioMaxOrdenado(@RequestParam Double precio) {
        return ResponseEntity.ok(bookService.findByPrecioMaxOrdenado(precio));
    }

    @GetMapping("search/multiples-autores")
    public ResponseEntity<List<Book>> buscarPorMultiplesAutores() {
        return ResponseEntity.ok(bookService.findByMultiplesAutores());
    }

    @GetMapping("search/contar-categorias")
    public ResponseEntity<Long> contarPorCategoria(@RequestParam String categoria) {
        return ResponseEntity.ok(bookService.contarPorCategoria(categoria));
    }


}
