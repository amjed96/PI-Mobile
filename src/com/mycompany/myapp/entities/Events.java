    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author zakib
 */
public class Events {
    
    private int id ;
    private String nom;
    private Date dateDebut;
    private Date dateFin;
    private int nbPlaces;    
    private int rate;

    private String description;
    private String lieu;
    private String photo;
    private List<Sponsor> sponsors ; 
    static int users=2;

    public static int getUsers() {
        return users;
    }

    public static void setUsers(int users) {
        Events.users = users;
    }

    public Events() {
    }

    public Events(String nom, int nbPlaces, String description, String photo) {
        this.nom = nom;
        this.nbPlaces = nbPlaces;
        this.description = description;
        this.photo = photo;
    }
    
    

    public Events(int id, String nom, Date dateDebut, Date dateFin, int nbPlaces, String description, String lieu, String photo) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbPlaces = nbPlaces;
        this.description = description;
        this.lieu = lieu;
        this.photo = photo;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }
    
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }
    

    @Override
    public String toString() {
           return "{ nom:  " + nom + ",dateDebut: "+dateDebut+",dateFin: "+dateFin+",nbPlaces:"+nbPlaces+",description: "+description+",lieu: "+lieu+",sponsors: "+sponsors+",eventphoto: "+null+",photo: "+photo+",photoUpdatedAt: "+null+"}";
        
    }
    
    
    
    
}
