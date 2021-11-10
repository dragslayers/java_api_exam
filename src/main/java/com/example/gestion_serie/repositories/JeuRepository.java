package com.example.gestion_serie.repositories;

import com.example.gestion_serie.models.Avis;
import com.example.gestion_serie.models.Jeu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JeuRepository extends CrudRepository<Jeu,Long> {

    @Override
    List<Jeu> findAll();

}
