/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.mycompany.myapp.entities.Opportunite;
import com.mycompany.myapp.services.OpportuniteService;
import com.mycompany.myapp.services.PostulationService;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class displayOtherOpportunities extends BaseForm {

    private static int id_user = 7;

    public displayOtherOpportunities(Resources res) {
        super(BoxLayout.y());

        setUIID("SignIn");
        Toolbar tb = getToolbar();
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        OpportuniteService serviceTask = new OpportuniteService();

        ArrayList<Opportunite> list = serviceTask.getAllOpportunite(id_user);
      
        for (Opportunite ass : list) {

            add(createRankWidget(ass, ass.getId(), ass.getAddresse(), ass.getNb_place(), ass.getDescription_opportunite(), ass.getDate(), res));

        }
//        getToolbar().addCommandToRightBar("back", null, (ev) -> {
//           NewsfeedForm nf = new NewsfeedForm(res);
//           nf.show();
//
//        });
    }

    public SwipeableContainer createRankWidget(Opportunite e, int id, String adresse, int nbplace, String description, String date, Resources res) {
        MultiButton button = new MultiButton();
        Button delete = new Button("Postuler");
        // button.addComponent(BorderLayout.SOUTH, delete);
        button.setHeight(200);

//    Image ig = res.getImage("C:\\xampp\\htdocs\\wordfriendship\\web\\images\\"+img).scaled(100, 100);
//    button.setIcon(ig);
//      
        button.setTextLine1(adresse);
        button.setTextLine2("" + nbplace);
        button.setTextLine3(description);

        button.addActionListener(
                ee -> {

                    dialog(e);
                });
        add(delete);

        delete.addActionListener(ee -> {
            PostulationService es = new PostulationService();
           
            es.addPostulation(e, id_user);
            sms();
            button.setTextLine1(adresse);
            button.setTextLine2("" + nbplace);
            button.setTextLine3(description);
//            Form current = new Form("Don't Ask Again", BoxLayout.y());
//            Dialog d = new Dialog("Popup Title");
//            Button a = new Button();
//            d.add(a);
//            a.addActionListener(r -> {
//                System.out.println("");
//            });
//
//            //Button clear = new Button("Clear");
//            Button show = new Button("Show Dialog");
//
//            //clear.addActionListener(r -> Preferences.set("dontShowDialog", false));
//            // show.addActionListener(r -> {
//            boolean b = Preferences.get("dontShowDialog", false);
//            if (!b) {
//                CheckBox areYouSur = new CheckBox("Don't ask again");
//                areYouSur.setUIID("DialogBody");
//                SpanLabel body = new SpanLabel("Are you sure you want to do this thing?", "DialogBody");
//                Command ok = new Command("OK");
//                Command cancel = new Command("Cancel");
//                Command result = Dialog.show("Are you Sure?", BoxLayout.encloseY(body, areYouSur),
//                        new Command[]{cancel, ok});
//                if (result == ok) {
//                    EventService es = new EventService();
//                    es.deleteEvent(id);
//
//                    ToastBar.showMessage("OK Pressed...", FontImage.MATERIAL_INFO);
//                    Preferences.set("dontShowDialog", areYouSur.isSelected());
//                    button.setTextLine1(name);
//                    button.setTextLine2("" + price);
//                    button.setTextLine3(description);
//                }
//            } else {
//                ToastBar.showMessage("Skipped dialog", FontImage.MATERIAL_INFO);
//            }
//            //  });
//
//            current.addAll(delete);
//            current.show();

        });
        return new SwipeableContainer(FlowLayout.encloseCenterMiddle(createStarRankSlider()),
                button);
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);

    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();

        return starRank;
    }

    private void dialog(Opportunite e) {
        Dialog d = new Dialog(e.getAddresse());
        TextArea popupBody = new TextArea(e.getAddresse() + "\n" + e.getNb_place() + "\n" + e.getDescription_opportunite() + "\n" + e.getDate() + "\n", 8, 12);
        popupBody.setUIID("Label");
        popupBody.setEditable(false);
        d.setLayout(new BorderLayout());
        d.addComponent(BorderLayout.CENTER, popupBody);
        d.setTransitionInAnimator(CommonTransitions.createEmpty());
        d.setTransitionOutAnimator(CommonTransitions.createFade(300));
        Rectangle rec = new Rectangle();
        rec.setX(700);
        rec.setY(1000);
        d.showPopupDialog(rec);
    }

    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }

    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    private void sms() {
        String accountSID = "ACbf3d3a54825492e70c31dd0e69f8dc29";
        String authToken = "8b2d9d5fc2fb17e05a9fe0bf60e9b67b";
        String fromPhone = "+12058397260";
        String destinationPhone = "+21652371878";

        Response<Map> SMS = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
                queryParam("To", destinationPhone).
                queryParam("From", fromPhone).
                queryParam("Body", "Votre postulation a ete effectuer avec success").
                header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
                getAsJsonMap();
    }

}
