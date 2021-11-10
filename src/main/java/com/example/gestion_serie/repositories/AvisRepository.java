package com.example.gestion_serie.repositories;

import com.example.gestion_serie.models.Avis;
import com.example.gestion_serie.models.Jeu;
import org.springframework.data.repository.CrudRepository;

public interface AvisRepository extends CrudRepository<Avis,Long> {

    Avis findFirstByJeuxOrderByDateEnvoieDesc(Jeu jeuxId);
}
