/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controllers;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.mycompany.Entity.Enseigne;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emir
 */
public class BrandsControllers {

    public ArrayList<Enseigne> getListBrand(String json) throws IOException {
        ArrayList<Enseigne> listBrands = new ArrayList<>();

        JSONParser j = new JSONParser();

        Map<String, Object> brands = j.parseJSON(new CharArrayReader(json.toCharArray()));
        System.out.println(brands);

        List<Map<String, Object>> list = (List<Map<String, Object>>) brands.get("root");
        for (Map<String, Object> obj : list) {
            Enseigne e = new Enseigne();

            // System.out.println(obj.get("id"));
            float id = Float.parseFloat(obj.get("id").toString());
            System.out.println(id);
            e.setId((int) id);
            e.setAddress(obj.get("adresse").toString());
            e.setPhoneNumber(obj.get("tel").toString());
            e.setFax(obj.get("fax").toString());
            e.setEmail(obj.get("email").toString());
            e.setWebSite(obj.get("site").toString());
            e.setLogo(obj.get("logo").toString());
            e.setCodeRc(obj.get("code").toString());
            float user_id = Float.parseFloat(obj.get("user").toString());

            e.setId_user((int) user_id);

            //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
            e.setName(obj.get("nom").toString());
            System.out.println(e);
            listBrands.add(e);

        }
        return listBrands;
    }

    public Enseigne getBrand(String json) {

        Enseigne e = new Enseigne();

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
                e.setPhoneNumber(obj.get("PhoneNumber").toString());
                e.setAddress(obj.get("Address").toString());
                e.setFax(obj.get("Fax").toString());
                e.setEmail(obj.get("Email").toString());
                e.setWebSite(obj.get("Website").toString());
                e.setLogo(obj.get("Logo").toString());
                e.setCodeRc(obj.get("CodeRC").toString());
                float user_id = Float.parseFloat(obj.get("user_id").toString());

                e.setId_user((int) user_id);

                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setName(obj.get("Name").toString());

            }

        } catch (IOException ex) {
        }

        return e;

    }
    private static final String MAPS_KEY = "AIzaSyC80gbKBnEtbZxbSAEY7v8gZSTjhd8_wOA"; // Your maps key here
 public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", MAPS_KEY);
            request.addArgument("address", address);

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                    ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
