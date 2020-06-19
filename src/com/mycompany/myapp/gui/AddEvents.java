/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.entities.Sponsor;
import com.mycompany.myapp.services.ServiceEvents;
import com.mycompany.myapp.services.ServiceSponsor;

import java.util.List;

/**
 *
 * @author zakib
 */
public class AddEvents extends Form {

    Form f;

    public AddEvents() {

        f = new Form("add Events", BoxLayout.y());
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c8 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l1 = new Label("Nom");
       
        l1.setShowEvenIfBlank(true);

        Label l2 = new Label("DateDebut");
        Label l3 = new Label("DateFin");
        Label l4 = new Label("Description");
        Label l5 = new Label("Lieu");
        Label l6 = new Label("NbPlaces");
        Label l7 = new Label("Rate");
        Label l8 = new Label("Sponsors");
        TextField t1 = new TextField();
        t1.getUnselectedStyle().setBgColor(000000);
        t1.getUnselectedStyle().setBgColor(000000);
        t1.getUnselectedStyle().setBgTransparency(255);
        
        Picker t2 = new Picker();
        t2.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        Picker t3 = new Picker();
        t3.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        TextField t4 = new TextField();
        TextField t5 = new TextField();
        TextField t6 = new TextField();
        TextField t7 = new TextField();
        ServiceSponsor sp = new ServiceSponsor();
        List<Sponsor> sponsors = sp.getListSponsor();
        c8.add(l8);
        for (Sponsor s : sponsors) {
            CheckBox ch = new CheckBox(s.getNom());
            ch.getAllStyles().setFgColor(0xffffff);
            c8.add(ch);
        }
        Button b1 = new Button("Add");

        c1.add(l1).add(t1);
        c2.add(l2).add(t2);
        c3.add(l3).add(t3);
        c4.add(l4).add(t4);
        c5.add(l5).add(t5);
        c6.add(l6).add(t6);
        c7.add(l7).add(t7);
        c0.addAll(c1, c2, c3, c4, c5, c6, c7, c8, b1);
        f.add(c0);

        Toolbar tb = f.getToolbar();

        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {

            AffichageEvents ae = new AffichageEvents();

        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Events ev = new Events();
                ev.setNom(t1.getText());
                if (t1.getText().equals("")) {
                    Dialog d = new Dialog("Popup Title");
                    TextArea popupBody = new TextArea("nb places doit etre un entier positif", 3, 10);
                    popupBody.setUIID("PopupBody");
                    popupBody.setEditable(false);
                    d.setLayout(new BorderLayout());
                    d.add(BorderLayout.CENTER, popupBody);
                    d.showPopupDialog(b1);
                    return;
                }
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//                String s = sdf.format(t2.getDate());
//                String s1 = s.substring(0,10)+ "T" +s.substring(11);
                ev.setDateDebut(t2.getDate());
//                String s2 = sdf.format(t3.getDate());
//                String s3 = s.substring(0,10)+ "T" +s.substring(11);
                ev.setDateFin(t3.getDate());
//                if (t3.getDate().toString().compareTo(t2.getDate().toString()) < 0){
//                    Dialog d = new Dialog("Popup Title");
//                    TextArea popupBody = new TextArea("date fin doit etre superieure a date debut", 3, 10);
//                    popupBody.setUIID("PopupBody");
//                    popupBody.setEditable(false);
//                    d.setLayout(new BorderLayout());
//                    d.add(BorderLayout.CENTER, popupBody);
//                    d.showPopupDialog(b1);
//                    return;
//                }
                System.out.println(ev.getDateDebut());
                ev.setDescription(t4.getText());
                ev.setLieu(t5.getText());
                try {
                    ev.setNbPlaces(Integer.parseInt(t6.getText()));
                    ev.setRate(Integer.parseInt(t6.getText()));
                } catch (Exception e) {
                    Dialog d = new Dialog("Popup Title");
                    TextArea popupBody = new TextArea("nb places doit etre un entier positif", 3, 10);
                    popupBody.setUIID("PopupBody");
                    popupBody.setEditable(false);
                    d.setLayout(new BorderLayout());
                    d.add(BorderLayout.CENTER, popupBody);
                    d.showPopupDialog(b1);
                    return;
                }

                ServiceEvents se = new ServiceEvents();
                se.addEvents(ev);
                new AffichageEvents();
            }
        });

        f.show();

    }

}
