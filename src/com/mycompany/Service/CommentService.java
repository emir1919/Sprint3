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
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Controllers.BrandsControllers;
import com.mycompany.Controllers.CommentControllers;
import com.mycompany.Entity.Comment;
import com.mycompany.Entity.Enseigne;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Emir
 */
public class CommentService {
        ArrayList<Comment> listcomment = new ArrayList<>();

    public void  AddComment(Comment cm)
    {
    
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/Comment/"); 
        con.setPost(true);
         
        con.setHttpMethod("POST");
        con.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        con.addArgument("content", cm.getContent());
        con.addArgument("DateComment", cm.getDateComment());

        con.addArgument("user_id", Integer.toString(cm.getUser_id()));
        con.addArgument("enseigne_id",Integer.toString( cm.getEnseigne_id()));
Dialog.show("Ajout avec succés",null,"ok",null);
        NetworkManager.getInstance().addToQueueAndWait(con);
    
    }
    CommentControllers cc=new CommentControllers();
    public  ArrayList<Comment> GetAllCommentByBrand(int idbrand)
    {
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/comment?_where=(enseigne_id,eq,"+idbrand+")"); 
        con.setPost(false);
         
        con.setHttpMethod("GET");
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    listcomment = cc.getListComment(new String(con.getResponseData()));
                } catch (IOException ex) {
                                        System.out.println(ex.getMessage());

                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcomment;
    }
    
    
}
