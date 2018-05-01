/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controllers;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entity.Users;
import com.mycompany.Service.UsersService;
import com.mycompany.myapp.MyApplication;
import java.util.ArrayList;

/**
 *
 * @author Emir
 */
public class LoginController {
    String status;
    Users usertest = new Users();

    public String login(String username, String password) {
        ArrayList<Users> users = new ArrayList<>();
        UsersService us = new UsersService();
        users = us.getAllUsers();
        for (Users user : users) {
            if (user.getUsername().equals(username)) {
                MyApplication.user = user;
                String p;

              
               

            }

        }
        if (MyApplication.user!=null) {
            
       
         ConnectionRequest r = new ConnectionRequest();
                r.setUrl("http://localhost/testpass.php?user=" + MyApplication.user.getId() + "&password=" + password);
                r.setPost(false);
                r.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
               
                       status =testpass(new String(r.getResponseData()));
                       

                    }

                });

                NetworkManager.getInstance().addToQueueAndWait(r);
        return status;
          }
        else
        {
            return "nouser";
        }
      
    }
    
    
    public String testpass(String status)
    {
        if (status.equals("nice")) {
                            return "good";
                        }
                        else 
                        {
                            return "notgood";
                        }
    }
}
