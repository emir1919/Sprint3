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
import com.mycompany.Entity.Subcategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yassi
 */
public class SubcategoryService {
    public ArrayList<Subcategory> convertSubcat(String json) {

        ArrayList<Subcategory> listSubCats = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Subcategory e = new Subcategory();
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
                e.setName(obj.get("name").toString());
                e.setImage_name(obj.get("imageName").toString());
                System.out.println(e);
                listSubCats.add(e);
            }

        } catch (IOException ex) {
        }
        
        return listSubCats;

    }
    
    ArrayList<Subcategory> subcats = new ArrayList<>();
    public ArrayList<Subcategory> getSubcats(){
        ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://localhost/testfos/web/app_dev.php/subcatsJson");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                subcats = convertSubcat(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return subcats;
    }
}
