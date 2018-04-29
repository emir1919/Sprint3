/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Controllers.BrandsControllers;
import com.mycompany.Entity.Comment;
import com.mycompany.Service.CommentService;
import com.mycompany.Service.RatingService;
import com.mycompany.Service.ServiceBrand;
import java.io.IOException;


/**
 *
 * @author Emir
 */
public class DetailBrand extends BaseForm {

    //Form f1;
    private Resources theme;
    public static int idenseigne = 0;
    BrandsControllers sb=new BrandsControllers();
    RatingService rs = new RatingService();
    CommentService cs = new CommentService();
    Image imgUrl2;
    Image imgUrl0;

    public DetailBrand() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
Slider star = createStarRankSlider();
        //star.setProgress(4);
        add(FlowLayout.encloseCenter(star));
        star.setProgress(rs.testrate(idenseigne));
        star.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                rs.AddRate(1, idenseigne, star.getProgress());
                System.out.println(star.getProgress());

            }
        });
    }

    public DetailBrand(com.codename1.ui.util.Resources resourceObjectInstance) {
        installSidemenu(resourceObjectInstance);
getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Details", "Title")
                )
        );
        //f1 = new Form("Palmarés", BoxLayout.y());
        /*f1.getToolbar().addCommandToLeftBar("retour", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ShowBrand a = new ShowBrand();
                a.getF().show();

            }
        });*/
       //theme = UIManager.initFirstTheme("/theme");
        TextField tf1 = new TextField();
        Button b = new Button("Ajouter commentaire");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Comment cm = new Comment();
                cm.setEnseigne_id(idenseigne);
                cm.setUser_id(1);
                cm.setContent(tf1.getText());
                cs.AddComment(cm);

            }
        });
        Container c1 = new Container(BoxLayout.y());
        c1.add(tf1);
        c1.add(b);
        c1.setWidth(50);
        add(c1);
        Image placeholder1 = Image.createImage(40, 40);
        EncodedImage encImage1 = EncodedImage.createFromImage(placeholder1, false);
        imgUrl2 = URLImage.createToStorage(encImage1, "http://localhost/CupcakeScript/dialog.png", "http://localhost/CupcakeScript/dialog.png");
        ImageViewer img2 = new ImageViewer(imgUrl2);
        Label l1 = new Label("Consulter commentaires");
        Container c4 = new Container(BoxLayout.x());
        c4.add(img2);
        c4.add(l1);

        l1.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CommentForm.idenseigne=idenseigne;
                CommentForm a = new CommentForm();

                a.show();
            }
        });

        c4.setLeadComponent(l1);
        add(c4);
        Image placeholder0 = Image.createImage(40, 40);
        EncodedImage encImage0 = EncodedImage.createFromImage(placeholder0, false);
        imgUrl0 = URLImage.createToStorage(encImage0, "http://localhost/CupcakeScript/map.png", "http://localhost/CupcakeScript/map.png");
        ImageViewer img0 = new ImageViewer(imgUrl0);
        Label l0 = new Label("Nos locaux");
        Container c0 = new Container(BoxLayout.x());
        c0.add(img0);
        c0.add(l0);

        l0.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    MapForm.idenseigne=idenseigne;
                    MapForm mp = new MapForm();
                    
                    mp.show();
                } catch (IOException ex) {
System.out.println("erreur");                }
            }
        });

        c0.setLeadComponent(l0);
        add(c0);
        getToolbar().addCommandToRightBar("back", null, (ev) -> {
            ShowBrand h = new ShowBrand();
            h.show();
        });
       
        show();
        refreshTheme();
    }

    /*public Form getF() {
        return f1;
    }

    public void setF(Form f) {
        this.f1 = f;
    }*/
    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(1);
        starRank.setMaxValue(6);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
}
