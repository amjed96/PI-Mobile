/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author zakib
 */
public class Common extends Form{
    Form f;
    public Common(){
        f = new Form("Gestion d'evennements", BoxLayout.y());
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button sponsor = new Button("Sponsors");
        Button events = new Button("Events");
        Button participations = new Button("Participations");
        c1.add(sponsor);
        c1.add(events);
        c1.add(participations);
        sponsor.addActionListener(e -> new AffichageSponsor());
        events.addActionListener(e -> new AffichageEvents());
        participations.addActionListener(e -> new AffichageParticipation());
        f.add(c1);
        f.show();
    }
}
