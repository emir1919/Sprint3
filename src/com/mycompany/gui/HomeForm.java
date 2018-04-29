/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
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
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Emir
 */
public class HomeForm extends Form{

    Form f;
    Image selection;
    Image selection2;
    Image selection3;
    Image selection4;

    Button btnaff;
    public Resources res;

    public HomeForm() {
        f = new Form("home");

        btnaff = new Button("Affichage");

        f.add(btnaff);

        /*btnaff.addActionListener((e) -> {
            ShowBrand a = new ShowBrand();
            a.getF().show();
        });*/
        installSidemenu();
        //Image selection = res.getImage("round.png");
       
        /*f.getToolbar().addCommandToSideMenu("Enseignes",imgbrand,e -> new ShowBrand().getF().show());

        f.getToolbar().addCommandToSideMenu("Stats", statsImage, e -> new ShowBrand().getF().show());
        f.getToolbar().addCommandToSideMenu("Calendar", calendarImage, e -> new ShowBrand().getF().show());
        f.getToolbar().addCommandToSideMenu("Map", null, e -> {
        });
        f.getToolbar().addCommandToSideMenu("Settings", null, e -> {
        });*/

        // spacer
        //f.getToolbar().addComponentToSideMenu(new Label("hi  ", "SideCommand"));
        //f.getToolbar().addComponentToSideMenu(new Label("hi 2 ", "Container"));
        //f.getToolbar().addComponentToSideMenu(new Label("Detra Mcmunn", "SideCommandNoPad"));
        //f.getToolbar().addComponentToSideMenu(new Label("Long Beach, CA", "SideCommandSmall"));
    }
  public void installSidemenu() {
       Image placeholder1 = Image.createImage(40, 40);
        EncodedImage encImage1 = EncodedImage.createFromImage(placeholder1, false);
        selection = URLImage.createToStorage(encImage1, "http://localhost/CupcakeScript/gato.jpg", "http://localhost/CupcakeScript/gato.jpg");
        ImageViewer imgbrand = new ImageViewer(selection);
        Image placeholder2 = Image.createImage(40, 40);
        EncodedImage encImage2 = EncodedImage.createFromImage(placeholder2, false);
        selection2 = URLImage.createToStorage(encImage2, "http://localhost/CupcakeScript/inbox.jpg", "http://localhost/CupcakeScript/inbox.jpg");
        ImageViewer imgInbox = new ImageViewer(selection2);
        Image placeholder3 = Image.createImage(40, 40);
        EncodedImage encImage3 = EncodedImage.createFromImage(placeholder3, false);
        selection3 = URLImage.createToStorage(encImage3, "http://localhost/CupcakeScript/map.png", "http://localhost/CupcakeScript/map.png");
        ImageViewer imgmap = new ImageViewer(selection3);
        Image placeholder4 = Image.createImage(40, 40);
        EncodedImage encImage4 = EncodedImage.createFromImage(placeholder4, false);
        selection4 = URLImage.createToStorage(encImage4, "http://localhost/CupcakeScript/stat.png", "http://localhost/CupcakeScript/stat.png");
        ImageViewer imgstat = new ImageViewer(selection4);
        

        Button inboxButton = new Button("Inbox");
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(imgInbox,inboxButton,
        new Label("18", "SideCommandNumber"));
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new InboxForm().show());
        f.getToolbar().addComponentToSideMenu(inbox);
        Button BrandButton = new Button("Enseignes");
        BrandButton.setUIID("SideCommand");
        BrandButton.getAllStyles().setPaddingBottom(0);
        Container brand = FlowLayout.encloseMiddle(imgbrand,BrandButton);
        brand.setLeadComponent(BrandButton);
        brand.setUIID("SideCommand");
        //BrandButton.addActionListener(e -> new ShowBrand().getF().show());
        f.getToolbar().addComponentToSideMenu(brand);
        Button StatButton = new Button("Statistiques");
        StatButton.setUIID("SideCommand");
        StatButton.getAllStyles().setPaddingBottom(0);
        Container stat = FlowLayout.encloseMiddle(imgstat,StatButton);
        stat.setLeadComponent(StatButton);
        stat.setUIID("SideCommand");
        //StatButton.addActionListener(e -> new ShowBrand().getF().show());
        f.getToolbar().addComponentToSideMenu(stat);
      
  }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    protected boolean isCurrentInbox() {
        return false;
    }

    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
}
