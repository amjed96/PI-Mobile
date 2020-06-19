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
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;

import java.util.ArrayList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.entities.Participation;
import com.mycompany.myapp.services.ServiceEvents;
import com.mycompany.myapp.services.ServiceParticipation;

/**
 *
 * @author zakib
 */
public class AffichageEvents extends Form {

    Form f;
    SpanLabel lb;
    Form f2;
    private Resources theme;
    private UIBuilder uiBuilder;
    private Participation part = new Participation();

    public AffichageEvents() {
        uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Events", BoxLayout.y());

        ServiceEvents serviceEvents = new ServiceEvents();
        ServiceParticipation serviceParticipation = new ServiceParticipation();
        ArrayList<Events> lis = serviceEvents.getListEvents();
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Events m : lis) {
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
                    Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    SpanLabel sp1 = new SpanLabel("Nom :" + m.getNom());
                    SpanLabel sp2 = new SpanLabel("DateDebut :" + m.getDateDebut());
                    SpanLabel sp3 = new SpanLabel("DateFin :" + m.getDateFin());
                    SpanLabel sp4 = new SpanLabel("Description :" + m.getDescription());
                    SpanLabel sp5 = new SpanLabel("Lieu :" + m.getLieu());
                    SpanLabel sp6 = new SpanLabel("NbPlaces :" + m.getNbPlaces());
                    String str = "Sponsorisé par : ";

                    for (int i = 0; i < m.getSponsors().size(); i++) {
                        str += m.getSponsors().get(i).getNom();
                        if (i < m.getSponsors().size() - 1) {
                            str += " , ";
                        }
                    }
                    SpanLabel sp8 = new SpanLabel(str);
                    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
                    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                    EncodedImage placeholder;
                    placeholder = EncodedImage.createFromImage(Image.createImage(p.getWidth() * 3, p.getWidth() * 3), false);
                    // placeholder t7 = new TextField(m.getPhoto());
                    System.out.println("photo :" + m.getPhoto());
//                    ImageViewer sp7 = new ImageViewer(URLImage.createToStorage(placeholder, m.getPhoto(), m.getPhoto()));
                    Button b = new Button("Participer");
                    Button b2 = new Button("Départiciper");
//                    C2.add(b2);
//                    f2.add(sp7);
                    f2.add(sp1);
                    f2.add(sp5);
                    f2.add(sp6);
                    f2.add(sp2);
                    f2.add(sp3);
                    f2.add(sp8);
                    Slider slider = createStarRankSlider();
                    System.out.println("m.getRate  " + m.getRate());
                    slider.setProgress(m.getRate());
                    f2.add(FlowLayout.encloseCenter(slider));
                    slider.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            System.out.println("Progress :" + slider.getProgress());
                            serviceEvents.rateEvent(m, slider.getProgress());

                            //  slider.setProgress();
                        }
                    });

                    ArrayList<Participation> participations = serviceParticipation.getListParticipation();
                    boolean found = false;
                    ABC:
                    for (Participation participation : participations) {
                        if ((participation.getEvents().getId() == m.getId()) && (participation.getUsers().getId() == MyApplication.currentUser.getId())) {
                            found = true;
                            part = participation;
                            break ABC;
                        }
                    };
                    System.out.println("found " + found);
                    if (found) {
                        c.add(b2);
                    } else {
                        c.add(b);
                    }
                    //c.addAll(b, b2);
                    f2.add(c);
                    f2.show();

                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            serviceEvents.participerEvents(m);
                            f2.show();
                            new AffichageEvents();
                        }
                    });

                    b2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            serviceEvents.départiciperEvents(part);
                            f2.show();
                            new AffichageEvents();
                        }
                    });

                }
            });

            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    serviceEvents.deleteEvents(m.getId());
                    f.show();
                    new AffichageEvents();
                }
            });

            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form f3 = new Form("Edit Events", BoxLayout.y());
                    Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label l1 = new Label("Nom");
                    Label l2 = new Label("DateDebut");
                    Label l3 = new Label("DateFin");
                    Label l4 = new Label("Description");
                    Label l5 = new Label("Lieu");
                    Label l6 = new Label("NbPlaces");
                    Label l7 = new Label("Photo");
                    TextField t1 = new TextField(m.getNom());
                    Picker t2 = new Picker();
                    t2.setType(Display.PICKER_TYPE_DATE_AND_TIME);
                    t2.setDate(m.getDateDebut());
                    Picker t3 = new Picker();
                    t3.setType(Display.PICKER_TYPE_DATE_AND_TIME);
                    t3.setDate(m.getDateFin());
                    TextField t4 = new TextField(m.getDescription());
                    TextField t5 = new TextField(m.getLieu());
                    TextField t6 = new TextField(m.getNbPlaces() + "", "Nombre PLaces ", 4, TextArea.NUMERIC);
                    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
                    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                    EncodedImage placeholder;
                    placeholder = EncodedImage.createFromImage(Image.createImage(p.getWidth() * 3, p.getWidth() * 3), false);
                    // placeholder t7 = new TextField(m.getPhoto());
                    ImageViewer t7 = new ImageViewer(URLImage.createToStorage(placeholder, m.getPhoto(), m.getPhoto()));
                    Button b1 = new Button("Edit");
                    c1.add(l1).add(t1);
                    c2.add(l2).add(t2);
                    c3.add(l3).add(t3);
                    c4.add(l4).add(t4);
                    c5.add(l5).add(t5);
                    c6.add(l6).add(t6);
                    c7.add(l7).add(t7);
                    c0.addAll(c1, c2, c3, c4, c5, c6, c7, b1);
                    f3.add(c0);
                    b1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Events ev = new Events();
                            ev.setId(m.getId());
                            ev.setNom(t1.getText());
                            ev.setDateDebut(t2.getDate());
                            ev.setDateFin(t3.getDate());
                            ev.setDescription(t4.getText());
                            ev.setLieu(t5.getText());
                            ev.setNbPlaces(Integer.parseInt(t6.getText()));
                            //ev.setPhoto(t7.getText());

                            ServiceEvents se = new ServiceEvents();
                            se.updateEvents(ev);
                            new AffichageEvents();
                        }
                    });

                    Toolbar tb = f3.getToolbar();

                    tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {

                        AffichageEvents ae = new AffichageEvents();

                    });
                    b1.addActionListener(e -> System.out.println("success"));
                    f3.show();
                }
            });

        }
        Button b2 = new Button("Add");
        C2.add(b2);
        f.add(C2);
        b2.addActionListener(e -> new AddEvents());
        Toolbar tb = f.getToolbar();

        tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {

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

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_LARGE);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(200);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

}
