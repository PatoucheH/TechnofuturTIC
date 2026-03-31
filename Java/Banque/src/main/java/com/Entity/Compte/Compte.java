package com.Entity.Compte;

import com.Entity.Personne;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Compte {
    private String numero;
    private double solde;
    private Personne titulaire;

    public Compte (){}
    public Compte(String numero, double solde, Personne titulaire) {
        setNumero(numero);
        setSolde(solde);
        setTitulaire(titulaire);
    }

    public String getNumero() {
        return numero;
    }
    public double getSolde() {
        return solde;
    }
    public Personne getTitulaire() {
        return titulaire;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    protected void setSolde(double solde) {
        this.solde = solde;
    }

    public void setTitulaire(Personne titulaire) {
        this.titulaire = titulaire;
    }

    public void retrait(double montant){
        if(montant < 0) throw new IllegalArgumentException("montant doit être positif ");
        setSolde(getSolde() - montant);
    }

    public void depot(double montant){
        if(montant < 0) throw new IllegalArgumentException("Montant doit être positif ");
        setSolde(getSolde() + montant);
    }

    protected abstract double calculInteret();

    public void appliquerInteret(){
        setSolde(getSolde() + calculInteret());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return Double.compare(getSolde(), compte.getSolde()) == 0 && Objects.equals(getNumero(), compte.getNumero()) && Objects.equals(getTitulaire(), compte.getTitulaire());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumero(), getSolde(), getTitulaire());
    }
}
