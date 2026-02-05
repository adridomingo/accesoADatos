package org.iesch.ad.DocumentosReferenciados.controller;

import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.iesch.ad.DocumentosReferenciados.repositorio.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/libros")
public class BookRefController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<BookRef>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookRef> getOne(@PathVariable String id) {
        Optional<BookRef> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<BookRef> crearBook(@RequestBody BookRef book) {
        BookRef books = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(books);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookRef> updateBook(@PathVariable String id, @RequestBody BookRef book) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
        BookRef booksRefSave = bookRepository.save(book);
        return ResponseEntity.ok(booksRefSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/{autorId}")
    public ResponseEntity<List<BookRef>> buscarLibrosPorIdAutor(@PathVariable String id) {
        return ResponseEntity.ok(bookRepository.findByAutoresId(id));
    }

    @GetMapping("/search/precio-anio")
    public ResponseEntity<List<BookRef>> buscarPorPrecioMenorYAñoSuperior(@RequestParam Double precio, @RequestParam Integer anio) {
        return ResponseEntity.ok(bookRepository.buscarPorPrecioInferiorYanioSuperior(precio, anio));
    }

    @GetMapping("/search/economicos-antiguos")
    public ResponseEntity<List<BookRef>> buscarEconomicosAntiguos(@RequestParam Double precio, @RequestParam Integer anio) {
        return ResponseEntity.ok(bookRepository.buscarLibrosEconomicosOAntiguos(precio, anio));
    }



}
