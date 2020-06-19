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
import com.mycompany.myapp.entities.Sponsor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zakib
 */
public class ServiceSponsor {

  private static String baseUrl = "http://localhost/PI_2021/web/app_dev.php/sponsor" ;
  public static String imageUrl = "http://localhost/PI_2021/web/uploads/sponsors/" ;
    public ArrayList<Sponsor> getListSponsor() {
       ArrayList<Sponsor> listSponsor = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(baseUrl + "/findAll");
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                Map<String, Object> sponosr = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(sponosr);
                //System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) sponosr.get("root");
                for (Map<String, Object> obj : list) {
                    Sponsor sp = new Sponsor();
                    float id = Float.parseFloat(obj.get("idSponsor").toString());
                    
                    sp.setId((int) id);
                    
                    sp.setNom(obj.get("nom").toString());
                    //sp.setPhoto(obj.get("photot").toString());
//                    sp.setPhoto(imageUrl+obj.get("photo").toString());
                    sp.setDescription(obj.get("description").toString());
                    
                    
                    
                    listSponsor.add(sp);
                    
                }
            } catch (IOException ex) {
            }
       });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listSponsor;
        
    }

    
    public void addSponsor(Sponsor ev) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(baseUrl + "/addSponsor?"
                + "nom="+ev.getNom()
                +"&description="+ev.getDescription()
                +"&photo="+ev.getPhoto()
                +"&photoUpdatedAt= null");
        
        

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
    
   
        
        

       
    
     public void deleteSponsor(int idSponsor) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(baseUrl + "/deleteSponsor/"+idSponsor) ;

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
    

