/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controllers;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.mycompany.Entity.Bakery;
import com.mycompany.Entity.Enseigne;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emir
 */
public class BakeryController {

    public ArrayList<Bakery> getListBakery(String json) throws IOException {
        ArrayList<Bakery> listBakeries = new ArrayList<>();

        JSONParser j = new JSONParser();

        Map<String, Object> bakeries = j.parseJSON(new CharArrayReader(json.toCharArray()));
        System.out.println(bakeries);

        List<Map<String, Object>> list = (List<Map<String, Object>>) bakeries.get("root");
        for (Map<String, Object> obj : list) {
            Bakery e = new Bakery();

            float id = Float.parseFloat(obj.get("id").toString());
            System.out.println(id);
            e.setId((int) id);
            e.setPhoneNumber(obj.get("PhoneNumber").toString());
            e.setAddress(obj.get("Address").toString());
            e.setFax(obj.get("Fax").toString());
            e.setEmail(obj.get("Email").toString());
            
            float user_id = Float.parseFloat(obj.get("user_id").toString());
            e.setId_user((int) user_id);
            float enseigne_id = Float.parseFloat(obj.get("enseigne_id").toString());
            e.setId_enseigne((int) enseigne_id);

            //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
            e.setName(obj.get("Name").toString());
            listBakeries.add(e);

        }
        return listBakeries;
    }
}
