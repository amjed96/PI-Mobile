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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.entities.Participation;
import com.mycompany.myapp.entities.Sponsor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author zakib
 */
public class ServiceEvents {

    private static String baseUrl = "http://localhost/PI_2021/web/app_dev.php/events" ;   
    private static String participationUrl = "http://localhost/PI_2021/web/app_dev.php/participation" ;   
    public static String imageUrl = "http://localhost/PI_2021/web/uploads/events/" ;

    public ArrayList<Events> getListEvents() {
       ArrayList<Events> listEvents = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(baseUrl + "/findAll");
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                Map<String, Object> events = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(events);
                //System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");
                for (Map<String, Object> obj : list) {
                    Events ev = new Events();
                    float id = Float.parseFloat(obj.get("idEvents").toString());
                    
                    ev.setId((int) id);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    ev.setNom(obj.get("nom").toString());
                    ev.setDateDebut(sdf.parse(obj.get("dateDebut").toString()));
                    ev.setDateFin(sdf.parse(obj.get("dateFin").toString()));
                    ev.setDescription(obj.get("description").toString());
                    ev.setLieu(obj.get("lieu").toString());
//                    ev.setPhoto(imageUrl+obj.get("photo").toString());
                    ev.setNbPlaces((int) Float.parseFloat(obj.get("nbPlaces").toString()));
                    if (obj.get("rate")!= null){
                    ev.setRate((int) Float.parseFloat(obj.get("rate").toString()));                        
                    }

                    ev.setSponsors(new ArrayList<>());
                    List<Map<String,Object>> sponsors =(List<Map<String,Object>>) obj.get("sponsors");
                    for(Map<String,Object> sp : sponsors){
                        Sponsor sponsor = new Sponsor();
                        sponsor.setId((int) Float.parseFloat(sp.get("idSponsor").toString()));
                        sponsor.setNom(sp.get("nom").toString());
                        sponsor.setDescription(sp.get("description").toString());
//                        sponsor.setPhoto(sp.get("photo").toString());
                        ev.getSponsors().add(sponsor);
                    }
                    listEvents.add(ev);
                    
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
               ex.printStackTrace();
           }
       });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
        
    }
    
    public void addEvents(Events ev) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(baseUrl + "/addEvents?"
                + "nom="+ev.getNom()
                +"&dateDebut="+ev.getDateDebut()
                +"&dateFin="+ev.getDateFin()
                +"&description="+ev.getDescription()
                +"&lieu="+ev.getLieu()
                +"&nbPlaces="+ev.getNbPlaces()
                +"&rate="+ev.getRate()
                +"&photo="+ev.getPhoto()
                +"&sponsors= []"
                +"&eventphoto=null"
                +"&photoUpdatedAt= null"
                +"&rate="+ev.getRate());
        
        

        System.out.println("test");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
   public void updateEvents(Events ev) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(baseUrl + "/updateEvents/"+ev.getId()+"?"
                + "nom="+ev.getNom()
                +"&dateDebut="+ev.getDateDebut()
                +"&dateFin="+ev.getDateFin()
                +"&description="+ev.getDescription()
                +"&lieu="+ev.getLieu()
                +"&nbPlaces="+ev.getNbPlaces()
                +"&rate="+ev.getRate()
                +"&photo="+ev.getPhoto()
                +"&sponsors= []"
                +"&eventphoto=null"
                +"&photoUpdatedAt= null" );
        
        

        System.out.println("test");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    } 

   public void deleteEvents(int idEvents) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(baseUrl + "/deleteEvents/"+idEvents) ;

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   
   public void participerEvents(Events ev) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(participationUrl + "/addParticipation/"+ev.getId()+"/"+Events.getUsers());
      
        System.out.println("test");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   
   public void départiciperEvents(Participation p) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(participationUrl + "/deleteParticipation/"+p.getId());
      
        System.out.println("test");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
   public void rateEvent(Events ev, int rate) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(baseUrl + "/rate/"+MyApplication.currentUser.getId()+"/"+ev.getId()+"/"+rate);

        System.out.println("test");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
