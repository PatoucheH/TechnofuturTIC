package com.Entity;

import java.time.LocalDate;
import java.util.Objects;

public class Personne {
    private String nom;
    private String prenom;
    private LocalDate dateNaiss;

    public Personne(){}
    public Personne(String nom, String prenom, LocalDate dateNaiss) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(getNom(), personne.getNom()) && Objects.equals(getPrenom(), personne.getPrenom()) && Objects.equals(getDateNaiss(), personne.getDateNaiss());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getNom(), getPrenom(), getDateNaiss());
    }
}
