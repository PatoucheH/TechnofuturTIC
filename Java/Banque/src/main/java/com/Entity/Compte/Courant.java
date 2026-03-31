package com.Entity.Compte;

import com.Entity.Personne;

import java.util.Objects;

public class Courant extends Compte {

    private double ligneDeCredit;

    public Courant(){}
    public Courant(String numero, Personne titulaire ) {
        super(numero, 0, titulaire);
        setLigneDeCredit(0);
    }
    public Courant(String numero, double ligneDeCredit, Personne titulaire) {
        super(numero, 0, titulaire);
        setLigneDeCredit(ligneDeCredit);
    }
    public double getLigneDeCredit() {
        return ligneDeCredit;
    }

    public void setLigneDeCredit(double ligneDeCredit) {
        if(ligneDeCredit < 0) {
            throw new IllegalArgumentException("Ligne deCredit invalide");
        }
        this.ligneDeCredit = ligneDeCredit;
    }

    public void retrait(double montant){
        if(getSolde() - montant < -getLigneDeCredit()) {
            throw  new IllegalArgumentException("Montant trop élevé pour retiré");
        }
        setSolde(getSolde() - montant);
    }

    public static double getSomme(Courant c1, Courant c2){
        if(c1.equals(c2)){
            throw new IllegalArgumentException("Les comptes sont les mêmes");
        }else{
            if(c1.getSolde() < 0)return c2.getSolde();
            else if(c2.getSolde() < 0)return c1.getSolde();
            return c1.getSolde() + c2.getSolde();
        }
    }

    public double calculInteret(){
        return this.getSolde() > 0 ? this.getSolde() * 0.03 : this.getSolde() * 0.0975;
    }
}
