package com.example.gestion_serie.controllers;

import com.example.gestion_serie.models.Avis;
import com.example.gestion_serie.models.Jeu;
import com.example.gestion_serie.repositories.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/avis")
public class AvisController {

    @Autowired
    private AvisRepository avisRepository;

    @PostMapping(value = "/{jeu}")
    public ResponseEntity<Avis> saveAvis(@PathVariable(name = "jeu",required = false) Jeu jeu,
                                         @Valid @RequestBody Avis avi, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, bindingResult.toString()
            );
        }

        avi.setJeux(jeu);
        avisRepository.save(avi);
        return new ResponseEntity<>(avi,HttpStatus.CREATED);
    }

    @DeleteMapping(value ="/{avis}")
    public void deleteOne(@PathVariable(name = "avis",required = false) Avis avi) {
        if(avi == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "avis introuvable"
            );
        }
        avisRepository.delete(avi);
    }
}
