/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.mycompany.myapp.entities.Secteur;
import java.util.ArrayList;

/**
 *
 * @author Amjed
 */
public class SecteurService {
    
    public ArrayList<Secteur> secteurs;
    public static SecteurService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private SecteurService(){
        req = new ConnectionRequest();
    }
    
    public static SecteurService getInstance(){
        if(instance == null){
            instance = new SecteurService();
        }
        return instance;
    }
    
    
    
}
