package org.iesch.MongoDemo_Repository.controller;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book bookSaved = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
        Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable String id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/titulo")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String q) {
        return ResponseEntity.ok(bookRepository.findByTituloContainingIgnoreCase(q));
    }

    @GetMapping("/search/categoria")
    public ResponseEntity<List<Book>> searchByCategory(@RequestParam String cat) {
        return ResponseEntity.ok(bookRepository.findByCategoriasIgnoreCase(cat));
    }

    @GetMapping("/search/autor")
    public ResponseEntity<List<Book>> buscarPorNombreAutor(@RequestParam String nombre){
        return ResponseEntity.ok(bookRepository.findByAutoresNombre(nombre));
    }

    @GetMapping("/search/autornacionalidad")
    public ResponseEntity<List<Book>> buscarPorNacionalidadAutor(@RequestParam String nacionalidad){
        return ResponseEntity.ok(bookRepository.findByAutoresNacionalidad(nacionalidad));
    }

    @GetMapping("/search/precio")
    public ResponseEntity<List<Book>> buscarEntrePrecioMinyMax(@RequestParam Double min, @RequestParam Double max){
        return ResponseEntity.ok(bookRepository.findByPrecioBetween(min, max));
    }

    @GetMapping("/search/recientes")
    public ResponseEntity<List<Book>> buscarRecientes(@RequestParam Double min, @RequestParam Integer anio){
        return ResponseEntity.ok(bookRepository.findByAnioPublicacionGreaterThan(anio));
    }

    // =============================== QUERYS PERSONALIZADAS

    @GetMapping("/search/nativo/autor")
    public ResponseEntity<List<Book>> buscarPorNombreAutor(@RequestParam Double min, @RequestParam String nombre){
        return ResponseEntity.ok(bookRepository.buscarPorAutorNombre(nombre));
    }

    @GetMapping("/search/nativo/precioAnio")
    public ResponseEntity<List<Book>> buscarPorPrecioYAnio(@RequestParam Double precio, @RequestParam Integer anio){
        return ResponseEntity.ok(bookRepository.buscarPorPrecioInferiorYanioMayor(precio, anio));
    }
}
