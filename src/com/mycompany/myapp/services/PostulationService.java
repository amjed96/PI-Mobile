/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.myapp.entities.Opportunite;

/**
 *
 * @author jihed hajlaoui
 */
public class PostulationService {
    
    
     public void addPostulation(Opportunite e,int id) {
        MultipartRequest con = new MultipartRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PI_2021/web/app_dev.php/O/postulermobile/" + e.getId()+"/"+id;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((ee) -> {

            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
}
