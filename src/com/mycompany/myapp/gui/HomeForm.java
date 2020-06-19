package com.mycompany.myapp.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;


/**
 *
 * @author bhk
 */
public class HomeForm {

    Form f;
    TextField tnom;
    TextField tetat;
    TextField terech;
    Button btnajout, btnaff, btnrech, btndel;

    public HomeForm() {
        f = new Form("home");
        tnom = new TextField("", "nom");
        tetat = new TextField("", "état");
        terech = new TextField("", "rech");
        btnajout = new Button("ajouter");
        btnaff = new Button("Affichage");
        btnrech = new Button("rech");
        btndel = new Button("del");
        f.add(tnom);
        f.add(tetat);
        f.add(btnajout);
        f.add(btnaff);
        f.add(btnrech);
        f.add(terech);
        f.add(btndel);
     /*   btnajout.addActionListener((e) -> {
            ServiceTask ser = new ServiceTask();
            Task t = new Task(0, tnom.getText(), tetat.getText());
            ser.ajoutTask(t);

        });
       
        btnrech.addActionListener((e) -> {
           
            Affichage a = new Affichage(Integer.parseInt(terech.getText()));
            a.getF().show();
        });
        btndel.addActionListener((e) -> {

            ServiceTask ser = new ServiceTask();
            Task t = new Task(Integer.parseInt(terech.getText()), tnom.getText(), tetat.getText());
            ser.deleteTask(t);
        });*/
        
      /*   btnaff.addActionListener((e) -> {
            Affichage a = new Affichage();
            a.getF().show();
        });*/
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

}
