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
import com.mycompany.Entity.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Emir
 */
public class UsersService {
     ArrayList<Users> listEvents = new ArrayList<>();
    
     
    public ArrayList<Users> getAllUsers(){    
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/users/"); 
        con.setPost(false);
        con.setHttpMethod("GET");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                UsersService es = new UsersService();
                listEvents = es.getUsers(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }
    
    
    public Users getUserbyEmail(String email){    
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/users?_where=(email,eq,"+email+")"); 
        con.setPost(false);
         
        con.setHttpMethod("GET");
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 UsersService es = new UsersService();
                listEvents = es.getUsers(new String(con.getResponseData()));
           
              


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents.get(0);
    }
    
    public Users getUserbyId(int id){    
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/users/"+id); 
        con.setPost(false);
         
        con.setHttpMethod("GET");
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 UsersService es = new UsersService();
                listEvents = es.getUsers(new String(con.getResponseData()));
           
              


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents.get(0);
    }
    
    public ArrayList<Users> getUsers(String json) {

        ArrayList<Users> listEvt = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> evt = j.parseJSON(new CharArrayReader(json.toCharArray()));
            //System.out.println(evt);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) evt.get("root");

            for (Map<String, Object> obj : list) {
                
                Users u = new Users();
                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
            

                u.setId((int) id);
                u.setUsername(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                u.setEnabled((int) Float.parseFloat(obj.get("enabled").toString()));
               // u.setFirstname(obj.get("FirstName").toString());
               // u.setLastname(obj.get("LastName").toString());
               // u.setPhonenumber((long) Float.parseFloat(obj.get("PhoneNumber").toString()));
                u.setPassword(obj.get("password").toString());
                
                
                System.out.println(u.getUsername());
                listEvt.add(u);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEvt);
        return listEvt;

    }
    
    
    public void addUser(Users user)
    {
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/users/"); 
        con.setPost(true);
        
        con.setHttpMethod("POST");
        con.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        con.addArgument("username", user.getUsername());
        con.addArgument("username_canonical", user.getUsername());
        con.addArgument("email", user.getEmail());
        con.addArgument("email_canonical", user.getEmail());
        con.addArgument("enabled", "1");
        con.addArgument("password", genpass(user.getPassword()));
        con.addArgument("Roles", "ROLE_USER");
        con.addArgument("FirstName", user.getFirstname());
        con.addArgument("LastName", user.getLastname());
        con.addArgument("Birthday", user.getBirthday().toString());
        con.addArgument("PhoneNumber",Long.toString(user.getPhonenumber()));
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void updatepasswordUser(Users user)
    {
        /* ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/users/"+user.getId()); 
        con.setPost(true);
         


        con.setHttpMethod("POST");

        con.addRequestHeader("X-HTTP-Method-Override", "PATCH");
              //con.setHttpMethod("POST");
        con.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        con.addArgument("password", genpass(user.getPassword()));
        
        NetworkManager.getInstance().addToQueueAndWait(con);*/
        ConnectionRequest r = new ConnectionRequest();
                r.setUrl("http://localhost/editpass.php?id="+user.getId()+"&password="+user.getPassword());
                r.setPost(false);
                NetworkManager.getInstance().addToQueueAndWait(r);
    }
    
      public String pass;
    public  String genpass(String password)
    {
        ConnectionRequest r = new ConnectionRequest();
                r.setUrl("http://localhost/genpass.php?password="+password);
                r.setPost(false);
                r.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                       /* try {
                            NetworkEvent event = (NetworkEvent) ev;
                            byte[] data = (byte[]) event.getMetaData();
                            String decodedData = new String(data, "UTF-8");
                            System.out.println(decodedData);
                            if (decodedData.equals("nice")) {
                                LoginController.status="good";

                            } else {
                                LoginController.status="notgood";
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }*/
                       pass =new String(r.getResponseData());
                        //System.err.println(status);
                       

                    }

                });

                NetworkManager.getInstance().addToQueueAndWait(r);
                return pass;
    }
    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
}
