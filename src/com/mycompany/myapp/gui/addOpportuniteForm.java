/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Opportunite;
import com.mycompany.myapp.services.CategorieService;
import com.mycompany.myapp.services.OpportuniteService;
import java.util.ArrayList;

import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author ASUS
 */
public class addOpportuniteForm extends BaseForm {

    private static int id_user = 7;
    private Button photo = new Button("Choisir image..");
    public static String jobPic = "";
    public static byte[] bytesdata;
    public static String chosenfile = "";

    public addOpportuniteForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField adresse = new TextField("", "Adresse", 20, TextField.ANY);
        TextField nbr_place = new TextField("", "Nombre de place", 20, TextField.ANY);

        TextField descr = new TextField("", "Description", 20, TextField.ANY);
        Picker dated = new Picker();
        dated.setText("Date");
        dated.setStartDate(new Date());

        // datef.setStartDate(new Date(new Date().getYear(),new Date().getMonth(),new Date().getDay()+1));
        CategorieService cs = new CategorieService();
        ComboBox<String> type1 = new ComboBox<>();

        ArrayList<Categorie> test = cs.getAllCategorie();
        for (int i = 0; i < test.size(); i++) {
            type1.addItem(test.get(i).getNomcategorie());
        }
        adresse.setSingleLineTextArea(false);

        nbr_place.setSingleLineTextArea(false);
        descr.setSingleLineTextArea(false);
        Button add = new Button("Ajouter");

        OpportuniteService es = new OpportuniteService();

        Container content = BoxLayout.encloseY(
                createLineSeparator(),
                type1,
                createLineSeparator(),
                new FloatingHint(adresse),
                createLineSeparator(),
                new FloatingHint(nbr_place),
                createLineSeparator(),
                new FloatingHint(descr),
                createLineSeparator(),
                dated,
                createLineSeparator()
        );

        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);

        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                add
        ));

        add.requestFocus();
        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (adresse.getText().isEmpty() || descr.getText().isEmpty() || nbr_place.getText().isEmpty()) {
                    Dialog.show("AVERTISSEMENT !!!", "Veuillez verifier vos champs Mercii !!!", "OK", null);

                } else {

                    if (!Pattern.matches("[0-9]*", nbr_place.getText())) {
                        Dialog.show("AVERTISSEMENT !!!", "Veuillez saisir le nombre de place !!!", "OK", null);

                    } else {
                        OpportuniteService ser = new OpportuniteService();
                        Categorie c = new Categorie();
                        for (int i = 0; i < cs.getAllCategorie().size(); i++) {
                            if (test.get(i).getNomcategorie() == type1.getSelectedItem()) {
                                c.setId(test.get(i).getId());
                                c.setDescription(test.get(i).getDescription());
                                c.setNomcategorie(test.get(i).getNomcategorie());

                            }
                        }
                        Opportunite ev = new Opportunite(adresse.getText(), c, Integer.parseInt(nbr_place.getText()), descr.getText(), dated.getDate().toString(), id_user);

                        ser.addOpportunite(ev);
                        showToast("Opportunite ajouter avec success !!");

                    }

                }
            }

        });

//        photo.addActionListener(e -> {
//            try {
//                UploadImage();
//            } catch (Exception ex) {
//                System.out.println("");
//            }
//
//        });
     /*   photo.addActionListener(e
                -> {
//
//                    try {
//                        UploadImage();
//                    } catch (Exception ex) {
//                        System.out.println("");
//                    }
        }
        );*/

    }

    private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();

    }

//    private void UploadImage() {
//
//        if (Dialog.show("Camera or Gallery", "Would you like to use the camera or the gallery for the picture?", "Camera", "Gallery")) {
//            jobPic = Capture.capturePhoto();
//            if (jobPic != null) {
//                try {
//                    Image img = Image.createImage(jobPic);
//                    ImageIO imgIO = ImageIO.getImageIO();
//                    ByteArrayOutputStream out = new ByteArrayOutputStream();
//                    imgIO.save(img, out, ImageIO.FORMAT_PNG, 1);
//                    bytesdata = out.toByteArray();
//
//                } catch (IOException err) {
//                    ToastBar.showErrorMessage("An error occured while loading the image: " + err);
//                    Log.e(err);
//                }
//            }
//        } else {
//            Display.getInstance().openGallery(ee -> {
//                if (ee.getSource() != null) {
//                    try {
//                        jobPic = (String) ee.getSource();
//                        Image img = Image.createImage((String) ee.getSource());
//                        ImageIO imgIO = ImageIO.getImageIO();
//                        ByteArrayOutputStream out = new ByteArrayOutputStream();
//                        imgIO.save(img, out, ImageIO.FORMAT_PNG, 1);
//                        bytesdata = out.toByteArray();
//                        chosenfile = ee.getSource().toString().substring(jobPic.toString().lastIndexOf("/") + 1);
//                        System.out.println(chosenfile);
//
//                    } catch (IOException err) {
//                        ToastBar.showErrorMessage("An error occured while loading the image: " + err);
//                        Log.e(err);
//                    }
//                }
//            }, Display.GALLERY_IMAGE);
//
//        }
//    }
//    private void UploadImage() throws Exception {
//        String file = Capture.capturePhoto();
//        FileSystemStorage fs = FileSystemStorage.getInstance();
//        String recordingsDir = fs.getAppHomePath() + "recordings/";
//
//        Display.getInstance().openGallery(ee -> {
//            if (file != null) {
//                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MMM-dd-kk-mm");
//                String fileName = sd.format(new Date());
//                String ss = ee.getSource().toString();
//                String pathfinal = "C:\\Users\\ASUS\\AppData\\Local\\Temp\\";
//
//                FileUploader fu = new FileUploader("http://localhost/wordfriendship/web/images/");
//                try {
//                    //Upload
//                    String fileNameInServer = fu.upload(pathfinal);
//                } catch (Exception ex) {
//                    System.out.println("");
//                }
//            }
//        }, Display.GALLERY_IMAGE);
//
//    }
}
