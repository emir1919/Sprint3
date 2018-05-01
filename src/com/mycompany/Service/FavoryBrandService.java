/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Controllers.BrandsControllers;
import com.mycompany.Controllers.FavoryController;
import com.mycompany.Entity.Comment;
import com.mycompany.Entity.Enseigne;
import com.mycompany.Entity.favory_brand;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Emir
 */
public class FavoryBrandService {
        ArrayList<favory_brand> listfavoris = new ArrayList<>();

    public void AddFavory(int idbrand,int iduser) {
        ConnectionRequest Con = new ConnectionRequest("http://localhost/CupcakeScript/AddFavory.php?idUser=" + iduser
                + "&idBrand=" + idbrand);
        Con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String ch = new String(data);
                System.out.println(ch);
                Dialog.show("Ajouté aux favoris avec succés", "", "ok", "cancel");

            }

        });
        NetworkManager.getInstance().addToQueue(Con);
    }
    public  ArrayList<favory_brand> GetFaovryByUser(int iduser)
    {
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/favory_brand?_where=(user_id,eq,"+iduser+")"); 
        con.setPost(false);
         
        con.setHttpMethod("GET");
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    FavoryController fc = new FavoryController();
                    listfavoris = fc.getListFavory(new String(con.getResponseData()));
                } catch (IOException ex) {
                                        System.out.println(ex.getMessage());

                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listfavoris;
    }
     public void deleteFav(int idbrand, int idUser) {
         ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/CupcakeScript/deleteFav.php?idBrand=" + idbrand + "&idUser=" + idUser + "");
        System.out.println(req.getUrl());
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
            System.out.println(s);
        });

        Dialog a = new Dialog("inf");
        a.show("info", "enseigne retiré du favori", "Yes", null);
        NetworkManager.getInstance().addToQueue(req);
    }
    favory_brand e=new favory_brand();
    public  favory_brand getOneFavory(int idbrand,int iduser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/favory_brand?_where=(user_id,eq,"+iduser+")&(enseigne_id,eq,"+idbrand+")");
        con.setPost(false);

        con.setHttpMethod("GET");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                FavoryController fc=new FavoryController();
                e = fc.getFavory(new String(con.getResponseData()));
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return e;

    }
}
