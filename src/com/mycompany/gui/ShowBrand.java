/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entity.Enseigne;
import com.mycompany.Service.FavoryBrandService;
import com.mycompany.Service.ServiceBrand;
import static com.mycompany.gui.DetailBrand.idenseigne;
import com.mycompany.myapp.MyApplication;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emir
 */
public class ShowBrand extends BaseForm{

    //Form f;
 public ShowBrand()
 {
         this(com.codename1.ui.util.Resources.getGlobalResources());

 }
    public ShowBrand(com.codename1.ui.util.Resources resourceObjectInstance) {
    installSidemenu(resourceObjectInstance);
       // f = new Form();
        

        ImageViewer img = new ImageViewer();

        ServiceBrand sb = new ServiceBrand();
        FavoryBrandService fb = new FavoryBrandService();
        List<Enseigne> ls = new ArrayList<Enseigne>();
        ls = sb.getListBrand();

        Container c3 = new Container(BoxLayout.y());

        Image imgUrl;
        Image imgUrl2;
        Image imgUrl22;

        for (Enseigne e : ls) {
            Label lb = new Label("");
            lb.setText(e.getName());

            Image placeholder = Image.createImage(120, 120);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            System.out.println("http://" + e.getLogo());

            imgUrl = URLImage.createToStorage(encImage, "http://" + e.getLogo(), "http://" + e.getLogo());
            //imgUrl.fetch();
            ImageViewer img1 = new ImageViewer(imgUrl);
            Container c1 = new Container(BoxLayout.y());
            Container c2 = new Container(BoxLayout.x());
            Image placeholder1 = Image.createImage(40, 40);
            EncodedImage encImage1 = EncodedImage.createFromImage(placeholder1, false);
            imgUrl2 = URLImage.createToStorage(encImage1, "http://localhost/CupcakeScript/fav.png", "http://localhost/CupcakeScript/fav.png");
            ImageViewer img2 = new ImageViewer(imgUrl2);

            refreshTheme();
            Label l1 = new Label("favory");
            Container c4 = new Container(BoxLayout.x());
            c4.add(img2);
            c4.add(l1);

            l1.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    fb.AddFavory(e.getId(),1);
                }
            });

            c4.setLeadComponent(l1);
            /*Button b = new Button("Add");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   DetailBrand.idenseigne=e.getId();
                                      DetailBrand a = new DetailBrand();

            a.show();
            
                }
            });*/
            Image placeholder11 = Image.createImage(40, 40);
        EncodedImage encImage11 = EncodedImage.createFromImage(placeholder11, false);
        imgUrl22 = URLImage.createToStorage(encImage11, "http://localhost/CupcakeScript/detalhes-h.png", "http://localhost/CupcakeScript/detalhes-h.png");
        ImageViewer img22 = new ImageViewer(imgUrl22);
        Label l12 = new Label("details");
        Container c41 = new Container(BoxLayout.x());
        c41.add(img22);
        c41.add(l12);

        l12.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                DetailBrand.idenseigne=e.getId();
                                      DetailBrand a = new DetailBrand();

            a.show();
            }
        });

        c41.setLeadComponent(l12);
        //add(c41);
            c1.add(lb);
            c1.add(c41);
            c1.add(c4);
            c2.add(img1);
            c2.add(c1);
            c3.add(c2);
                        //c3.add(FlowLayout.encloseCenter(createStarRankSlider()));

        }
        //c3.add(c1);

        add(c3);

        refreshTheme();
        getToolbar().addCommandToRightBar("Mes favoris", null, (ev) -> {
            FavoryFrom h = new FavoryFrom();
            h.show();
        });
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Enseignes", "Title")
                )
        );
    }

  

    /*public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }*/
}
