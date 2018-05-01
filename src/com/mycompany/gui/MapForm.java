/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Controllers.BrandsControllers;
import com.mycompany.Entity.Bakery;
import com.mycompany.Service.BakeryService;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emir
 */
public class MapForm extends BaseForm {

    private Resources theme;
    public static int idenseigne = 0;
    BrandsControllers sb = new BrandsControllers();
    List<Bakery> lb = new ArrayList<Bakery>();
    BakeryService bs = new BakeryService();

    public MapForm() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());

    }

    public MapForm(com.codename1.ui.util.Resources resourceObjectInstance) throws IOException {
        installSidemenu(resourceObjectInstance);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Nos locaux", "Title")
                )
        );
        getToolbar().addCommandToRightBar("retour", null, (ev) -> {
            DetailBrand h = new DetailBrand();
            h.show();
        });
        lb=bs.getListBakeryByidBrand(idenseigne);
        System.out.println(lb.toString());
       Image marker = Image.createImage("/marker.png");
        Image fill = marker.fill(30, 30);

        MapComponent m = new MapComponent();
        double latitude = 36.899527163883356;
        double longitude = 10.18983787368006;
        Coord lastLocation = new Coord(latitude, longitude);
        m.zoomTo(lastLocation, 10);
        //Image marker = Image.createImage(theme.getImage("marker.png"));

        //Coord coordonnees = new Coord(36.899527163883356, 10.18983787368006);
        for(Bakery e:lb){
        Coord coordonnees = sb.getCoords(e.getAddress());
        PointLayer pl = new PointLayer(coordonnees, e.getName(), fill);
        pl.setDisplayName(true);
        PointsLayer pointsL = new PointsLayer();
        pointsL.addPoint(pl);
        pointsL.setPointIcon(fill);
        m.addLayer(pointsL);
        }
        add(m);

        show();
        refreshTheme();
    }
}
