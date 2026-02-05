package org.iesch.ad.DocumentosReferenciados.service;

import org.bson.types.ObjectId;
import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BookRefService {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<BookRef> buscaTodos() {
        return mongoTemplate.findAll(BookRef.class);
    }

    public List<BookRef> findByTituloContainingIgnoreCase(String q) {
        Query query = new Query();
        query.addCriteria(Criteria.where("titulo").regex(q, "i"));
        return mongoTemplate.find(query, BookRef.class);
    }


    public @Nullable List<BookRef> findByAutorId(String autorId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("autores.id").is(new ObjectId(autorId)));
        return mongoTemplate.find(query, BookRef.class);
    }

    public @Nullable BookRef buscaOne(String id) {
        return mongoTemplate.findById(id, BookRef.class);
    }

    public @Nullable List<BookRef> findByAutorNombre(String autorNombre) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("autores")
                .localField("autores")
                .foreignField("_id")
                .as("autoresData");
        MatchOperation matchOperation = Aggregation.match(
          Criteria.where("autoresData.nombre").regex(autorNombre, "i")
        );
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, matchOperation);
        AggregationResults<BookRef> results = mongoTemplate.aggregate(aggregation, "libros_ref", BookRef.class);
        return results.getMappedResults();
    }
}
