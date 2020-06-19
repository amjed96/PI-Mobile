/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author jihed hajlaoui
 */
public class Categorie {
   private int id ;
    private String nom_categorie ;
    private String description ;

    public Categorie(int id, String nomcategorie, String description) {
        this.id = id;
        this.nom_categorie = nomcategorie;
        this.description = description;
    }
       public Categorie(String nomcategorie, String description) {
        this.nom_categorie = nomcategorie;
        this.description = description;
    }
    public Categorie() {
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomcategorie() {
        return nom_categorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nom_categorie = nomcategorie;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom_categorie=" + nom_categorie + ", description=" + description + '}';
    }
    
}
