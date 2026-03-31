package com.Entity;

import com.Entity.Compte.Compte;

import java.util.Map;
import java.util.Objects;

public class Banque {
    private String nom;
    private Map<String, Compte> comptes;

    public Banque(){}
    public Banque(String nom, Map<String, Compte> comptes) {
        this.nom = nom;
        this.comptes = comptes;
    }

    public String getNom() {
        return nom;
    }

    public void ajouter(Compte compte){
        comptes.put(compte.getNumero(), compte);
    }

    public void suprimer(String numero){
        comptes.remove(numero);
    }

    public Compte getCompte(String numero){
        return comptes.get(numero);
    }

    public double avoirDesComptes(Personne titulaire){
        return comptes.values().stream()
                .filter(c -> c.getTitulaire().equals(titulaire))
                .mapToDouble(Compte::getSolde)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Banque banque = (Banque) o;
        return Objects.equals(getNom(), banque.getNom()) && Objects.equals(comptes, banque.comptes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNom(), comptes);
    }
}
