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
public class Opportunite {
    private int id ;
    private String addresse ;
    private int nb_place ;
    private String image ;
    private String date ;
    private String description_opportunite ;
    private boolean etat ;
    private Categorie c;
    private int id_user;


    public Categorie getC() {
        return c;
    }

    public void setC(Categorie c) {
        this.c = c;
    }
    public Opportunite() {
    }
     public Opportunite(String addresse) {
         this.addresse=addresse;
    }
         public Opportunite(String addresse, String descriptionOpportunite) {
         this.addresse=addresse;
         this.description_opportunite =descriptionOpportunite;
         
    }
public Opportunite(Categorie c,String addresse, int nb_place,  String description_opportunite,String date,boolean etat) {
        this.c=c;
        this.addresse = addresse;
        this.nb_place = nb_place;
        this.date = date;
        this.description_opportunite = description_opportunite;
        this.etat=etat;
    }
    public Opportunite(int id, String addresse, int nb_place,  String description_opportunite,String date,boolean etat) {
        this.id = id;
        this.addresse = addresse;
        this.nb_place = nb_place;
        this.description_opportunite = description_opportunite;
        this.date=date;
        this.etat=etat;
    }
     
        public Opportunite(String addresse,Categorie c, int nb_place,  String description_opportunite,String date,int id_user) {
        this.addresse = addresse;
        this.nb_place = nb_place;
        this.description_opportunite = description_opportunite;
        this.date=date;
        this.id_user=id_user;
         this.c=c;
    }
 
    public Opportunite(int id, String addresse, int nb_place, String image, String date, String description_opportunite, boolean etat) {
        this.id = id;
        this.addresse = addresse;
        this.nb_place = nb_place;
        this.image = image;
        this.date = date;
        this.description_opportunite = description_opportunite;
        this.etat = etat;
    }
     public Opportunite(String addresse, int nb_place, String image, String date, String description_opportunite) {
        this.addresse = addresse;
        this.nb_place = nb_place;
        this.image = image;
        this.date = date;
        this.description_opportunite = description_opportunite;
    }
      public Opportunite(String addresse, String image, String date, String description_opportunite) {
        this.addresse = addresse;
        this.image = image;
        this.date = date;
        this.description_opportunite = description_opportunite;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    
    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription_opportunite() {
        return description_opportunite;
    }

    public void setDescription_opportunite(String description_opportunite) {
        this.description_opportunite = description_opportunite;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Opportunite{" + "id=" + id + ", addresse=" + addresse + ", nb_place=" + nb_place + ", image=" + image + ", date=" + date + ", description_opportunite=" + description_opportunite + ", etat=" + etat + ", c=" + c + ", id_user=" + id_user + '}';
    }

  
    
}
