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
import com.mycompany.Entity.Review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yassi
 */
public class ReviewService {
    
    
    public void addReview(Review r){
        ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://localhost/testfos/web/app_dev.php/addReviewsAsJSON?product="
                +r.getProduct_id()+
                "&comment="+r.getReviewText()+
                "&rating="+r.getRating()+
                "&user="+r.getUser_id()
        );
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                res = new String(con.getResponseData());
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        //return res;
    }
    
    String res = "";
    public void removeReview(int id){
        ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://localhost/testfos/web/app_dev.php/removeReviewsAsJSON/"+id);
        
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                res = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public ArrayList<Review> convertReview(String json) {

        ArrayList<Review> listReviews = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Review e = new Review();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setReviewText(obj.get("reviewText").toString());
                e.setRating((int)Float.parseFloat(obj.get("rating").toString()));
                
                //e.setUser_id((int)Float.parseFloat(obj.get("user").toString()));
                System.out.println(e);
                listReviews.add(e);

            }

        } catch (IOException ex) {
        }
        
        return listReviews;

    }
    
    ArrayList<Review> Reviews = new ArrayList<>();
    public ArrayList<Review> getReviews(int id){
        ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://localhost/testfos/web/app_dev.php/reviewsJson/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reviews = convertReview(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Reviews;
    }
}
