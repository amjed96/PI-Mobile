/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Amjed
 */
public class Secteur {
    
    private int id;
    private String nom;
    private String adresse;
    private String pays;

    public Secteur() {
    }

    public Secteur(int id, String nom, String adresse, String pays) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.pays = pays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Secteur{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", pays=" + pays + '}';
    }
    
}
