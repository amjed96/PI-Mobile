/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author zakib
 */
public class Sponsor {
    
     
    private int id ;
    private String nom ;
    private String photo ;
    private String description ;

    public Sponsor( String nom, String photo,  String description) {
        this.nom = nom;
        this.photo = photo;
        this.description = description;
    }

   

   

    public Sponsor() {
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    @Override
    public String toString() {
   
        return "Sponsor{" + "id=" + id + ", nom=" + nom + ", photo=" + photo +  ", description=" + description + '}';
    }
    
}
