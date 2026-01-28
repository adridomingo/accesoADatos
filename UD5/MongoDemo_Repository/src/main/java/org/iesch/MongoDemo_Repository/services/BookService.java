package org.iesch.MongoDemo_Repository.services;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Book> findByTituloContainingIgnoreCase(String titulo) {
        Query query = new Query();
        query.addCriteria(Criteria.where("titulo").regex(titulo, "i"));
        return mongoTemplate.find(query, Book.class);
    }

    public List<Book> findByCategoriaContainingIgnoreCase(String cat) {
        Query query = new Query();
        query.addCriteria(Criteria.where("categorias").regex(cat, "i"));
        return mongoTemplate.find(query, Book.class);
    }

    public List<Book> findByAutoresNombre(String nombre) {
        Query query = new Query();
        query.addCriteria(Criteria.where("autores.nombre").is(nombre));
        return mongoTemplate.find(query, Book.class);
    }

    public List<Book> findByPrecioEntre(Double min, Double max) {
        Query query = new Query();
        query.addCriteria(Criteria.where("precio").gte(min).lte(max));
        return mongoTemplate.find(query, Book.class);
    }

    public List<Book> findByAnioReciente(Integer anio) {
        Query query = new Query();
        query.addCriteria(Criteria.where("anioPublicacion").is(anio));
        return mongoTemplate.find(query, Book.class);
    }

    public List<Book> findByPrecioAnio(Double precio, Integer anio) {
        Query query = new Query();
        query.addCriteria(Criteria.where("precio").lt(precio).and("anioPublicacion").lt(anio));
        return mongoTemplate.find(query, Book.class);
    }
}
