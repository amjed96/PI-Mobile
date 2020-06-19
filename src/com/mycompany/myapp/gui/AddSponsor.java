/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author zakib
 */
public class AddSponsor {

     Form f;
    public AddSponsor() {
        f = new Form("add Sponsor", BoxLayout.y());
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l1 = new Label("Nom");
        Label l2 = new Label("Description");
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        Button b1 = new Button("Add");
                
        c1.add(l1).add(t1);
        c2.add(l2).add(t2);
        c0.addAll(c1,c2,c3,b1);
        f.add(c0);
        
        Toolbar tb = f.getToolbar();
            
            tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e->{
            
            AffichageSponsor ae = new AffichageSponsor();
            
            });
        

        
        f.show();
    }}


