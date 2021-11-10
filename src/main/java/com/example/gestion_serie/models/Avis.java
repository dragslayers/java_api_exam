package com.example.gestion_serie.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Avis")
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Basic
    private int note;

    @Basic
    private String Description;

    @Temporal(TemporalType.DATE)
    private Date dateEnvoie;

    @ManyToOne
    @JoinColumn(name = "jeux_id", nullable = false)
    @JsonBackReference
    private Jeu jeux;

    public Avis() {}

    public Avis(int note, String description, Date dateEnvoie) {
        this.note = note;
        Description = description;
        this.dateEnvoie = dateEnvoie;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getDateEnvoie() {
        return dateEnvoie;
    }

    public void setDateEnvoie(Date dateEnvoie) {
        this.dateEnvoie = dateEnvoie;
    }

    public Jeu getJeux() {
        return jeux;
    }

    public void setJeux(Jeu jeux) {
        this.jeux = jeux;
    }
}
