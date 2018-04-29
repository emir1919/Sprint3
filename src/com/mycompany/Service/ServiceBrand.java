/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Controllers.BrandsControllers;
import com.mycompany.Controllers.FavoryController;
import com.mycompany.Entity.Enseigne;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emir
 */
public class ServiceBrand {

    ArrayList<Enseigne> listBrands = new ArrayList<>();
    Enseigne e = new Enseigne();

    public ArrayList<Enseigne> getListBrand() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/TestFOS/web/app_dev.php/listbrandjson");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    BrandsControllers bc = new BrandsControllers();
                    listBrands = bc.getListBrand(new String(con.getResponseData()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());

                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listBrands;
    }
    BrandsControllers bc = new BrandsControllers();

    public Enseigne getBrandById(int idbrand) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/enseigne?_where=(id,eq," + idbrand + ")");
        con.setPost(false);

        con.setHttpMethod("GET");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                e = bc.getBrand(new String(con.getResponseData()));
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return e;

    }
}
