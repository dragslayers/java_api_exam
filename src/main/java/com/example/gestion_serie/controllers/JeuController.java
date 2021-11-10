package com.example.gestion_serie.controllers;

import com.example.gestion_serie.models.Avis;
import com.example.gestion_serie.models.Jeu;
import com.example.gestion_serie.repositories.AvisRepository;
import com.example.gestion_serie.repositories.JeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/jeux")
public class JeuController {

    @Autowired
    private JeuRepository jeuRepository;

    @Autowired
    private AvisRepository avisRepository;

    @GetMapping(value = "/")
    List<Jeu> all() {
        return jeuRepository.findAll();
    }


    @GetMapping(value = "/{jeu}")
    Jeu getOne(@PathVariable(name="jeu",required = false )Jeu jeu) {
        if(jeu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "jeu introuvable"
            );
        }
        return jeu;
    }

    @GetMapping(value = "/{jeu}/avis")
    List<Avis> getOneAllavis(@PathVariable(name="jeu",required = false )Jeu jeu,
                      @Valid @RequestBody Avis avis, BindingResult bindingResult) {
        if(avis == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "avis introuvable"
            );
        }
        return jeu.getAvis();
    }

    @GetMapping(value = "/{jeu}/dernierAvis")
    Avis getOneLastAvis(@PathVariable(name="jeu",required = false )Jeu jeu,
                             @Valid @RequestBody Avis avis, BindingResult bindingResult) {
        if(avis == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "avis introuvable"
            );
        }
        return avisRepository.findFirstByJeuxOrderByDateEnvoieDesc(jeu);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Jeu> saveJeu(@Valid @RequestBody Jeu jeu, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, bindingResult.toString()
            );
        }
        jeuRepository.save(jeu);
        return new ResponseEntity<>(jeu,HttpStatus.CREATED);
    }

    @PutMapping(value="/{jeu}")
    public ResponseEntity<Jeu> update(@PathVariable(name = "jeu",required = false) Jeu jeu,
                                        @Valid @RequestBody Jeu jeuUpdate, BindingResult bindingResult) {
        if(jeu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "jeu introuvable"
            );
        }

        if(bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }

        jeuUpdate.setId(jeu.getId());
        jeuRepository.save(jeuUpdate);
        return new ResponseEntity<>(jeuUpdate,HttpStatus.OK);
    }

    @DeleteMapping(value ="/{jeu}")
    public void deleteOne(@PathVariable(name = "jeu",required = false) Jeu jeu) {
        if(jeu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "serie introuvable"
            );
        }
        jeuRepository.delete(jeu);
    }
}
