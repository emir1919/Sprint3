/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompany.Controllers.LoginController;
import com.mycompany.Entity.Users;
import com.mycompany.Service.UsersService;
import com.mycompany.myapp.MyApplication;

/**
 *
 * @author Emir
 */
public class Login {
    Form f;

    Button btnaff;

    
    public Login() {
       f = new Form("Connexion");

        TextField u = new TextField("", "Nom d'utilisateur", 20, TextField.EMAILADDR) ;
        TextField p =  new TextField("", "Password", 20, TextField.PASSWORD) ;
        btnaff = new Button("Se connecter");
        Button newaccount = new Button("S'inscrire");
        Button mdpoublie = new Button("Mot de passe oublié ?");
        TextField email = new TextField("", "Email", 20, TextField.EMAILADDR) ;
        Button nvmdp = new Button("Nouveau mot de passe");
f.add(u);
f.add(p);

        f.add(btnaff);
        f.add(newaccount);
        f.add(mdpoublie);
        btnaff.addActionListener((e) -> {
            LoginController l = new LoginController();
      String logged;
        logged=l.login(u.getText(), p.getText());
            System.err.println(u.getText());
        if (logged.equals("good")) {
            System.err.println("Logged good");
             /*Home r = new Home();
          r.getF().show();*/
                             new ShowBrand().show();

        }
        else if (logged.equals("nouser")) {
            System.err.println("nouser");
                                        MyApplication.user=null;

        }
        else
        {
            System.err.println("error");
                                        MyApplication.user=null;

        }
        });
        
        newaccount.addActionListener((evt) -> {
          /*Registration r = new Registration();
          r.getF().show();*/
        });
        
        mdpoublie.addActionListener((evt) -> {
            f.add(email);
            f.add(nvmdp);
            f.refreshTheme();
            
            nvmdp.addActionListener((e) -> {
                UsersService us = new UsersService();
                Users user = new Users();
                user=us.getUserbyEmail(email.getText());
                if (user!=null) {
               /* Message m = new Message("Body of message");
Display.getInstance().sendMessage(new String[] {"ryadh.berrahal@esprit.tn"}, "Subject of message", m);*/
             String newpass=us.getSaltString();
               user.setPassword(us.genpass(newpass));
               us.updatepasswordUser(user);
               
               ConnectionRequest r = new ConnectionRequest();
                r.setUrl("http://localhost/sendmail.php?subject=Cupcake - Mot de passe&body="+newpass);
                r.setPost(false);
                NetworkManager.getInstance().addToQueueAndWait(r);
                }
            });
        });
    }
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
