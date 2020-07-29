package com.apistarwars.repository;

import com.apistarwars.entity.Planeta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.awt.print.Pageable;
import java.util.List;

@Repository @Component
public interface PlanetaRepository extends MongoRepository<Planeta, String> {
    Planeta findByNome(String nome);

    Planeta findByNomeIgnoreCase(String nome);
}
