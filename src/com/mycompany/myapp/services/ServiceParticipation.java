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
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.entities.Participation;
import com.mycompany.myapp.entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zakib
 */
public class ServiceParticipation {
    private final String baseUrl = "http://localhost/PI_2021/web/app_dev.php/participation/";

    public ArrayList<Participation> getListParticipation() {
        ArrayList<Participation> listSponsor = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(baseUrl + "find/"+MyApplication.currentUser.getId());
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                String s = new String(con.getResponseData());
                System.out.println("sss "+ s);
                Map<String, Object> Participations = jsonp.parseJSON(new CharArrayReader(s.toCharArray()));
                //System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) Participations.get("root");
                for (Map<String, Object> obj : list) {
                    Participation sp = new Participation();
                    float id = Float.parseFloat(obj.get("id").toString());
                    sp.setId((int) id);
                    Events event = new Events();
                     //Map<String, Object> events = jsonp.parseJSON(new CharArrayReader(obj.get("events").toString().toCharArray()));
                     //System.out.println("events "+ events.toString());
                    Map<String, Object> obj2 = (Map<String, Object>) obj.get("events");
                   // for (Map<String, Object> obj2 : list2) {
                        event.setId((int)Float.parseFloat(obj2.get("idEvents").toString()));
                        event.setNom(obj2.get("nom").toString());
                        event.setPhoto(ServiceEvents.imageUrl +obj2.get("photo"));
                    //}
                    sp.setEvents(event);
                    User user = new User();
                     //Map<String, Object> users = jsonp.parseJSON(new CharArrayReader(obj.get("users").toString().toCharArray()));
                     obj2 = (Map<String, Object>) obj.get("users");
                    //for (Map<String, Object> obj2 : list2) {
                        user.setId((int)Float.parseFloat(obj2.get("id").toString()));
                        user.setUsername(obj2.get("username").toString());
                        //}
                    sp.setUsers(user);
   
                    listSponsor.add(sp);
                    
                }
            } catch (IOException ex) {
            }
       });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listSponsor;
        
    }
    
}
