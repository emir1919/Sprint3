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
import com.mycompany.Entity.Rating;
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
     public void AddRate(int iduser,int idbrand,double note) {
        ConnectionRequest Con = new ConnectionRequest("http://localhost/CupcakeScript/AddRating.php?idUser=" + iduser
                + "&idBrand=" + idbrand + "&note=" +note);
        Con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String ch = new String(data);
                System.out.println(ch);

            }

        });
        NetworkManager.getInstance().addToQueue(Con);
    }
      public int testrate(int idDoc) {

        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/TestFOS/web/app_dev.php/ratejson/"+idDoc);

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
         
            ArrayList<Rating> listMod=new ArrayList<>();
            JSONParser j = new JSONParser();      
            Map<String, Object> payse = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) payse.get("root");
            for (Map<String, Object> obj : list) {
                System.out.println(obj.get("nbrVote").toString());
                Rating p = new Rating();
                p.setNbrVote((int)Float.parseFloat(obj.get("nbrVote").toString()));
                p.setNbrEtoile((int)Float.parseFloat(obj.get("note").toString()));
                nbr = (int)Float.parseFloat(obj.get("note").toString());
               //System.out.println("hetha el mocule "+p.toString());
                //p.setImage_name(obj.get("image_name").toString());
                listMod.add(p);
            }
            //return listMod;
            return nbr;
    }
}
