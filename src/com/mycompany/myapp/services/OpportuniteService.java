/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Opportunite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author jihed hajlaoui
 */
public class OpportuniteService {

    ArrayList<Opportunite> lis = new ArrayList<>();

    public ArrayList<Opportunite> getAllOpportunite(int iduser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI_2021/web/app_dev.php/O/showothermobile/"+iduser);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                OpportuniteService ser = new OpportuniteService();
                lis = ser.parseListTaskJson(new String(con.getResponseData()));

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lis;
    }
    
     public ArrayList<Opportunite> getMyOpportunities(int iduser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI_2021/web/app_dev.php/O/Afficheropprmobile/"+iduser);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                OpportuniteService ser = new OpportuniteService();
                lis = ser.parseListTaskJson(new String(con.getResponseData()));

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lis;
    }
     
         public void deleteOpportunite(int id) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PI_2021/web/app_dev.php/O/deletemobile/" + id;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }


    public ArrayList<Opportunite> parseListTaskJson(String json) {

        ArrayList<Opportunite> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            System.out.println(list);
            for (Map<String, Object> obj : list) {

                Opportunite o = new Opportunite();

                //float id = Float.parseFloat(obj.get("id").toString());
                //float nbr = Float.parseFloat(obj.get("nbPlace").toString());
                //o.setId((int) id);
                Float c = Float.parseFloat(obj.get("nbPlace").toString());
                o.setAddresse(obj.get("addresse").toString());
                o.setDescription_opportunite(obj.get("descriptionOpportunite").toString());
                o.setNb_place(c.intValue());
                o.setDate(obj.get("date").toString());
                Float id = Float.parseFloat(obj.get("id").toString());
                o.setId(id.intValue());
                o.setEtat(false);
//                o.setImage(obj.get("image").toString());
                Categorie cc = new Categorie();
                /*  Float idcc = Float.parseFloat(obj.get("categorie.id").toString());
                cc.setId(idcc.intValue());
                cc.setDescription(obj.get("categorie.description").toString());
                cc.setNomcategorie(obj.get("categorie.nomCategorie").toString());*/
              /*  JSONObject jsonObject = new JSONObject(obj.values());
                List<String> liste = new ArrayList<String>();
                System.out.println("obj" + obj.values().);
                JSONArray jsonArray = jsonObject.getJSONArray("");
                for (int i = 0; i < jsonArray.length(); i++) {
                    liste.add(jsonArray.getJSONObject(i).getString("nomCategorie"));
                    System.out.println(jsonArray.getJSONObject(i).getString("nomCategorie")); // display usernames
                }*/

                listTasks.add(o);
            }

        } catch (IOException ex) {
        }
        return listTasks;

    }
    ArrayList<Opportunite> listEvents = new ArrayList<>();

    public ArrayList<Opportunite> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI_2021/web/app_dev.php/O/affopp");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                OpportuniteService ser = new OpportuniteService();
                listEvents = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }

    public void addOpportunite(Opportunite e) {
        MultipartRequest con = new MultipartRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/PI_2021/web/app_dev.php/O/ajoutopp/" + e.getC().getNomcategorie() + "?addresse=" + e.getAddresse() + "&nbPlace=" + e.getNb_place() + "&descriptionOpportunite=" + e.getDescription_opportunite() + "&date=" + e.getDate() + "&idUser=" + e.getId_user();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((ee) -> {

            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

}
