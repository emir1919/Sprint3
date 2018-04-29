/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Controllers.BakeryController;
import com.mycompany.Controllers.BrandsControllers;
import com.mycompany.Entity.Bakery;
import com.mycompany.Entity.Enseigne;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Emir
 */
public class BakeryService {
    ArrayList<Bakery> listBakeries = new ArrayList<>();
    Bakery b = new Bakery();

    public ArrayList<Bakery> getListBakeryByidBrand(int idbrand) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/bakery?_where=(enseigne_id,eq," + idbrand + ")");
        con.setPost(false);

        con.setHttpMethod("GET");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    BakeryController bc=new BakeryController();
                    listBakeries = bc.getListBakery(new String(con.getResponseData()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());

                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listBakeries;
    }
}
