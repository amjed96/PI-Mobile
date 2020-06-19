/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Sponsor;
import com.mycompany.myapp.services.ServiceSponsor;

import java.util.ArrayList;

/**
 *
 * @author zakib
 */
public class AffichageSponsor extends Form {
    
     Form f;
    SpanLabel lb;
    Form f2;

    public AffichageSponsor() {
            

        f = new Form("Sponsors", BoxLayout.y());
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button b2 = new Button("Add");
        ServiceSponsor serviceSponsor = new ServiceSponsor();
        ArrayList<Sponsor> lis = serviceSponsor.getListSponsor();

        for (Sponsor m : lis) {
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Label l1 = new Label(m.getNom());
            Button b1 = new Button("Delete");
            Button b3 = new Button("Edit");
            

            C1.add(l1).add(b1).add(b3);
            C2.add(C1);

            //C1.setLeadComponent();

            l1.addPointerPressedListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {

                    f2 = new Form(BoxLayout.y());

                    Toolbar tb = f2.getToolbar();

                    tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {

                        f.showBack();

                    });

                    SpanLabel sp1 = new SpanLabel("Nom :" + m.getNom());
                    SpanLabel sp2 = new SpanLabel("Description :" + m.getDescription());
                    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
                    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                    EncodedImage placeholder;
                    placeholder = EncodedImage.createFromImage(Image.createImage(p.getWidth() * 3, p.getWidth() * 3), false);
                    System.out.println("ééééééééééééééééééééééééééééééééééééé");
                    System.out.println("photo :" + m.getPhoto());
                    ImageViewer sp3 = new ImageViewer(URLImage.createToStorage(placeholder, m.getPhoto(), m.getPhoto()));
                    System.out.println(sp3.toString());
                    f2.add(sp3);
                    f2.add(sp1);
                    f2.add(sp2);
                    f2.show();

                }
            });

            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    serviceSponsor.deleteSponsor(m.getId());
                    f.show();
                    new AffichageSponsor();
                }
            });

            b2.addActionListener(e -> new AddSponsor());

            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form f3 = new Form("Edit Sponsor", BoxLayout.y());
                    Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label l1 = new Label("Nom");
                    Label l2 = new Label("Description");
                    TextField t1 = new TextField(m.getNom());
                    TextField t2 = new TextField(m.getDescription());
                    Button b1 = new Button("Edit");

                    c1.add(l1).add(t1);
                    c2.add(l2).add(t2);
                    c0.addAll(c1, c2, c3, b1);
                    f3.add(c0);

                    Toolbar tb = f3.getToolbar();

                    tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {

                        AffichageSponsor ae = new AffichageSponsor();

                    });
                    b1.addActionListener(e->System.out.println("success"));
                    f3.show();
                }
            });

        }
        C2.add(b2);
        f.add(C2);
        Toolbar tb = f.getToolbar();
            
            tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e->{
            
            new Common();
            
            });
        f.show();

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
