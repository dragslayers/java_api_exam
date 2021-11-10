package com.example.gestion_serie.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Jeu")
public class Jeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Basic
    private String nom;

    @Basic
    private String Description;

    @Temporal(TemporalType.DATE)
    private Date dateSortie;

    @OneToMany(mappedBy="jeux", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Avis> avis;

    public Jeu() {}

    public Jeu(String nom, String description, Date dateSortie) {
        this.nom = nom;
        Description = description;
        this.dateSortie = dateSortie;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }
}
