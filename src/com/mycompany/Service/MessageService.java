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
import com.mycompany.Controllers.CommentControllers;
import com.mycompany.Controllers.MessageController;
import com.mycompany.Entity.Comment;
import com.mycompany.Entity.Msg;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Emir
 */
public class MessageService {
            ArrayList<Msg> listmsg = new ArrayList<>();

     MessageController cc=new MessageController();
    public  ArrayList<Msg> GetReceiveMsg(int iduser)
    {
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/msg?_where=(recepteur_id,eq,"+iduser+")"); 
        con.setPost(false);
         
        con.setHttpMethod("GET");
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    listmsg = cc.getMsgReceive(new String(con.getResponseData()));
                } catch (IOException ex) {
                                        System.out.println(ex.getMessage());

                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listmsg;
    }
     public void  AddMsg(Msg cm)
    {
    
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/msg/"); 
        con.setPost(true);
         
        con.setHttpMethod("POST");
        con.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        con.addArgument("subject", cm.getSubject());
                con.addArgument("body", cm.getBody());
                con.addArgument("DateEnvoi", cm.getDateEnvoi());
        con.addArgument("lu", Integer.toString(cm.getLu()));

        con.addArgument("emetteur_id", Integer.toString(cm.getEmetteur_id()));
        con.addArgument("recepteur_id",Integer.toString( cm.getRecepteur_id()));
                        //Dialog.show("erreur", "not correct", "ok", "cancel");

        NetworkManager.getInstance().addToQueueAndWait(con);
    
    }
}
