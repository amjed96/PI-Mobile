/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Participation;
import com.mycompany.myapp.services.ServiceParticipation;

import java.util.ArrayList;

/**
 *
 * @author zakib
 */
public class AffichageParticipation extends Form {

    Form f;
    SpanLabel lb;

    public AffichageParticipation() {

        f = new Form("Mes Participations", BoxLayout.y());

        ServiceParticipation serviceParticipation = new ServiceParticipation();
        ArrayList<Participation> lis = serviceParticipation.getListParticipation();
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Participation m : lis) {
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            System.out.println(m.getEvents().getNom());

            Label l1 = new Label(m.getEvents().getNom());
            Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
            FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
            EncodedImage placeholder;
            placeholder = EncodedImage.createFromImage(Image.createImage(p.getWidth() * 3, p.getWidth() * 3), false);
            ImageViewer sp7 = new ImageViewer(URLImage.createToStorage(placeholder, m.getEvents().getPhoto(), m.getEvents().getPhoto()));
        
            C1.add(l1).add(sp7);
            C2.add(C1);

        }
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
