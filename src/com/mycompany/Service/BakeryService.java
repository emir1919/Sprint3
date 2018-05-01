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
import com.mycompany.Controllers.BakeryController;
import com.mycompany.Controllers.BrandsControllers;
import com.mycompany.Entity.Bakery;
import com.mycompany.Entity.Enseigne;
import com.mycompany.Entity.NbBakeyObject;
import com.mycompany.Entity.Rating;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emir
 */
public class BakeryService {

    ArrayList<Bakery> listBakeries = new ArrayList<>();
    Bakery b = new Bakery();

    public ArrayList<Bakery> getListBakeryByidBrand(int idbrand) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/bakery?_where=(enseigne_id,eq," + idbrand + ")");
        con.setPost(false);

        con.setHttpMethod("GET");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    BakeryController bc = new BakeryController();
                    listBakeries = bc.getListBakery(new String(con.getResponseData()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());

                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listBakeries;
    }
    static int nbrFinal=0;
static int nbr=0;
    public int countbakerybyenseigneid(int idbrand) {

        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost:3000/api/bakery/count?_where=(enseigne_id,eq," + idbrand + ")");

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                BakeryService dcs = new BakeryService();

                try {
                    nbrFinal = dcs.getcountbakery(new String(req.getResponseData()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nbrFinal;
    }

    public int getcountbakery(String json) throws IOException {

        JSONParser j = new JSONParser();
        Map<String, Object> payse = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) payse.get("root");
        for (Map<String, Object> obj : list) {

            nbr = (int) Float.parseFloat(obj.get("no_of_rows").toString());
            //System.out.println("hetha el mocule "+p.toString());
            //p.setImage_name(obj.get("image_name").toString());
            System.out.println((int) Float.parseFloat(obj.get("no_of_rows").toString()));

        }
        //return listMod;
        return nbr;
    }
   private ArrayList<NbBakeyObject> nbrBkr =new ArrayList<>();


public double[] getNbrBakeryValue() {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/CupcakeScript/countbakery.php");

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                BakeryService dcs = new BakeryService();
                try {
                    nbrBkr = dcs.getInfoNbrBrand(new String(req.getResponseData()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        double[] values = new double[nbrBkr.size()];
        for(int i=0;i < nbrBkr.size();i++)
        {
            values[i] = nbrBkr.get(i).getNbbakery();
        }
        
        
        return values;
    }
    
     public String[] getBrandName() {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/CupcakeScript/countbakery.php");

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                BakeryService dcs = new BakeryService();
                try {
                    nbrBkr = dcs.getInfoNbrBrand(new String(req.getResponseData()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        String[] values = new String[nbrBkr.size()];
        for(int i=0;i < nbrBkr.size();i++)
        {
            values[i] = nbrBkr.get(i).getBrandname();
        }
        
        
        return values;
    }

     public ArrayList<NbBakeyObject> getInfoNbrBrand(String json) throws IOException {
        ArrayList<NbBakeyObject> liststr = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> evt = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) evt.get("root");
        for (Map<String, Object> obj : list) {
            NbBakeyObject t = new NbBakeyObject();
            int total = ((int) Float.parseFloat(obj.get("nbr").toString()));
            String titre = (obj.get("enseigne").toString());
            t.setBrandname(titre);
            t.setNbbakery(total);
            System.out.println("total "+t+" titre "+titre);
            liststr.add(t);
        }
        return liststr;
    }


}
