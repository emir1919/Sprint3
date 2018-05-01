/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controllers;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.mycompany.Entity.Comment;
import com.mycompany.Entity.Enseigne;
import com.mycompany.Entity.favory_brand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emir
 */
public class FavoryController {

    public ArrayList<favory_brand> getListFavory(String json) throws IOException {
        ArrayList<favory_brand> listfavoris = new ArrayList<>();

        JSONParser j = new JSONParser();

        Map<String, Object> favoris = j.parseJSON(new CharArrayReader(json.toCharArray()));
        System.out.println(favoris);

        List<Map<String, Object>> list = (List<Map<String, Object>>) favoris.get("root");
        for (Map<String, Object> obj : list) {
            favory_brand e = new favory_brand();

            // System.out.println(obj.get("id"));
            float id = Float.parseFloat(obj.get("id").toString());
            float user_id = Float.parseFloat(obj.get("user_id").toString());
            float enseigne_id = Float.parseFloat(obj.get("enseigne_id").toString());
            System.out.println(id);
            e.setId((int) id);
            e.setEnseigne_id((int) enseigne_id);
            e.setUser_id((int) user_id);
            System.out.println(e);
            listfavoris.add(e);

        }
        return listfavoris;
    }

    public favory_brand getFavory(String json) {

        favory_brand e = new favory_brand();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> brand = j.parseJSON(new CharArrayReader(json.toCharArray()));
            //System.out.println(evt);

            List<Map<String, Object>> list = (List<Map<String, Object>>) brand.get("root");

            for (Map<String, Object> obj : list) {

                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);

                float user_id = Float.parseFloat(obj.get("user_id").toString());
                float enseigne_id = Float.parseFloat(obj.get("enseigne_id").toString());

                e.setEnseigne_id((int) enseigne_id);
                e.setUser_id((int) user_id);

                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
            }

        } catch (IOException ex) {
        }

        return e;

    }
}
