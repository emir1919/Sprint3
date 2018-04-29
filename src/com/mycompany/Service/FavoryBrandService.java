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
    
}
