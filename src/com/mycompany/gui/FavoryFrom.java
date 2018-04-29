/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.Entity.Enseigne;
import com.mycompany.Entity.favory_brand;
import com.mycompany.Service.FavoryBrandService;
import com.mycompany.Service.ServiceBrand;
import java.util.ArrayList;

/**
 *
 * @author Emir
 */
public class FavoryFrom extends BaseForm {

    ServiceBrand sb = new ServiceBrand();
    ArrayList<Enseigne> listBrands = new ArrayList<>();
    ArrayList<favory_brand> listfavoris = new ArrayList<>();
    Enseigne e = new Enseigne();
    FavoryBrandService fs = new FavoryBrandService();

    public FavoryFrom() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public FavoryFrom(com.codename1.ui.util.Resources resourceObjectInstance) {
        installSidemenu(resourceObjectInstance);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Mes favoris", "Title")
                )
        );
        getToolbar().addCommandToRightBar("back", null, (ev) -> {
            ShowBrand h = new ShowBrand();
            h.show();
        });
        listfavoris = fs.GetFaovryByUser(1);
        System.out.println(listfavoris.toString());
        System.out.println(sb.getBrandById(listfavoris.get(0).getEnseigne_id()).toString());

        for(int i=0;i<listfavoris.size();i++){
           System.out.println(listfavoris.get(i).getEnseigne_id());
           listBrands.add(sb.getBrandById(listfavoris.get(i).getEnseigne_id()));
       }
        System.out.println(listBrands.toString());
        Container c3 = new Container(BoxLayout.y());

        Image imgUrl;
        Image imgUrl2;
        Image imgUrl22;

        for (Enseigne e : listBrands) {
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
                    //fb.AddFavory(e.getId(),1);
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
    }

}
