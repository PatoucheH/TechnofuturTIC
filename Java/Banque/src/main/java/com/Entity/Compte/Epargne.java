package com.Entity.Compte;

import com.Entity.Personne;

import java.time.LocalDate;

public class Epargne extends Compte{
    private LocalDate dateDernierRetrait;

    public Epargne(){}
    public Epargne(String numero, Personne titulaire){
        super(numero, 0, titulaire);
        setDateDernierRetrait(LocalDate.now());
    }

    public LocalDate getDateDernierRetrait() {
        return dateDernierRetrait;
    }

    public void setDateDernierRetrait(LocalDate dateDernierRetrait) {
        this.dateDernierRetrait = dateDernierRetrait;
    }

    public double calculInteret(){
        return getSolde() * 0.045;
    }

    public void retrait(double montant){
        if(getSolde() - montant < 0) throw new IllegalArgumentException("Fond insuffisant ");
        super.retrait(montant);
        setDateDernierRetrait(LocalDate.now());
    }
}
