/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Opportunite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jihed hajlaoui
 */
public class CategorieService {
      ArrayList<Categorie> lis = new ArrayList<>();
      
      public ArrayList<Categorie> getAllCategorie() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI_2021/web/app_dev.php/O/allCategorie");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                CategorieService ser = new CategorieService();
                lis = ser.parseListTaskJson(new String(con.getResponseData()));

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lis;
    }
      public ArrayList<Categorie> parseListTaskJson(String json) {

        ArrayList<Categorie> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                Categorie o = new Categorie();

                //float id = Float.parseFloat(obj.get("id").toString());
                //float nbr = Float.parseFloat(obj.get("nbPlace").toString());
                //o.setId((int) id);
                //o.setNomcategorie(obj.get("description ").toString());
                Float  c = Float.parseFloat(obj.get("id").toString());
                o.setId(c.intValue());
                o.setDescription(obj.get("description").toString());
               o.setNomcategorie(obj.get("nomCategorie").toString());
                //o.setDate(obj.get("date").toString());
               
//             e.setImage(obj.get("url").toString());
               // o.setDescription_opportunite(obj.get("descriptionOpportunite").toString());
               // o.setNb_place((int)nbr);
      

                listTasks.add(o);

            }

        } catch (IOException ex) {
        }
        return listTasks;

    }
}
