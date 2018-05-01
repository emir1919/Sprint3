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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Controllers.FavoryController;
import com.mycompany.Entity.Rating;
import com.mycompany.Entity.favory_brand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emir
 */
public class RatingService {

    int nbr = 0;
    int nbrFinal = 0;

    public void AddRate(int iduser, int idbrand, double note) {
        ConnectionRequest Con = new ConnectionRequest("http://localhost/CupcakeScript/AddRating.php?idUser=" + iduser
                + "&idBrand=" + idbrand + "&note=" + note);
        Con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String ch = new String(data);
                System.out.println(ch);
                Dialog.show("Note ajouté avec succés", null, "ok", null);
            }

        });
        NetworkManager.getInstance().addToQueue(Con);
    }

    public int testrate(int idDoc) {

        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/TestFOS/web/app_dev.php/ratejson/" + idDoc);

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                RatingService dcs = new RatingService();

                try {
                    nbrFinal = dcs.getRateDoc(new String(req.getResponseData()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nbrFinal;
    }

    public int getRateDoc(String json) throws IOException {

        ArrayList<Rating> listMod = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> payse = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) payse.get("root");
        for (Map<String, Object> obj : list) {
            System.out.println(obj.get("nbrVote").toString());
            Rating p = new Rating();
            p.setNbrVote((int) Float.parseFloat(obj.get("nbrVote").toString()));
            p.setNbrEtoile((int) Float.parseFloat(obj.get("note").toString()));
            nbr = (int) Float.parseFloat(obj.get("note").toString());
            //System.out.println("hetha el mocule "+p.toString());
            //p.setImage_name(obj.get("image_name").toString());
            listMod.add(p);
        }
        //return listMod;
        return nbr;
    }

    public int testrate2(int idDoc) {

        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/TestFOS/web/app_dev.php/ratejson/" + idDoc);

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                RatingService dcs = new RatingService();

                try {
                    nbrFinal = dcs.getRateDoc2(new String(req.getResponseData()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nbrFinal;
    }

    public int getRateDoc2(String json) throws IOException {

        ArrayList<Rating> listMod = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> payse = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) payse.get("root");
        for (Map<String, Object> obj : list) {
            System.out.println(obj.get("nbrVote").toString());
            Rating p = new Rating();
            p.setNbrVote((int) Float.parseFloat(obj.get("nbrVote").toString()));
            p.setNbrEtoile((int) Float.parseFloat(obj.get("note").toString()));
            nbr = (int) Float.parseFloat(obj.get("nbrVote").toString());
            //System.out.println("hetha el mocule "+p.toString());
            //p.setImage_name(obj.get("image_name").toString());
            listMod.add(p);
        }
        //return listMod;
        return nbr;
    }
    ArrayList<Rating> lr = new ArrayList<Rating>();

    public ArrayList<Rating> GetAllRating() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/rating");
        con.setPost(false);

        con.setHttpMethod("GET");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    RatingService rs = new RatingService();
                    lr = rs.getListrate(new String(con.getResponseData()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());

                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lr;
    }

    public ArrayList<Rating> getListrate(String json) throws IOException {
        ArrayList<Rating> listr = new ArrayList<>();

        JSONParser j = new JSONParser();

        Map<String, Object> rats = j.parseJSON(new CharArrayReader(json.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) rats.get("root");
        for (Map<String, Object> obj : list) {
            Rating e = new Rating();

            // System.out.println(obj.get("id"));
            float id = Float.parseFloat(obj.get("id").toString());
            float user_id = Float.parseFloat(obj.get("user_id").toString());
            float enseigne_id = Float.parseFloat(obj.get("enseigne_id").toString());
            System.out.println(id);
            e.setId((int) id);
            e.setEnseigne_id((int) enseigne_id);
            e.setUser_id((int) user_id);
            System.out.println(e);
            listr.add(e);

        }
        return listr;
    }
}
