/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.capture.Capture;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.media.MediaManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.Callback;
import com.mycompany.Entity.Product;
import com.mycompany.myapp.MyApplication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import rest.file.uploader.tn.FileUploader;



/**
 *
 * @author yassi
 */
public class ProductService {
   
    public void pictureUpload(final Callback<String> resultURL, String photo, String name) {
        
        if (photo != null) {
            
            MultipartRequest request = new MultipartRequest() {
                protected void readResponse(InputStream input) throws IOException {
                    JSONParser jp = new JSONParser(); 
                    Map<String, Object> result = jp.parseJSON(new InputStreamReader(input, "UTF-8"));
                    String url = (String) result.get("url");
                    if (url == null) {
                        resultURL.onError(null, null, 1, result.toString());
                        return;
                    }
                    resultURL.onSucess(url);
                }
            };
            request.setUrl("http://localhost/testfos/web/app_dev.php/addProductsJson");
            try {
                request.addData("file", photo, "image/jpeg");
                request.setFilename("file", name);
                NetworkManager.getInstance().addToQueue(request);
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }
    public void addProduct(Product p, String f){
        try {
            MultipartRequest request = new MultipartRequest();
            request.setUrl("http://localhost/testfos/web/app_dev.php/addProductsJson");
            request.setPost(true);
            request.addArgument("name",p.getName());
            request.addArgument("price",String.valueOf(p.getPrice()));
            request.addArgument("reduction",String.valueOf(p.getReduction()));
            request.addArgument("description",p.getDescription());
            request.addArgument("subcat",String.valueOf(p.getSubcategory_id()));
            //request.addArgument("userId",String.valueOf(MyApplication.user.getId()));
            request.addArgument("userId",String.valueOf(2));
            
            String name = f.substring(0,f.indexOf("."))+".jpg";
            System.out.println(name);
            
            //add the data image
            request.addData("file", f, "image/jpeg");
            request.setFilename("file", name);
            request.addArgument("img", MyApplication.urlUpload + name);
            
            
            request.setPriority(ConnectionRequest.PRIORITY_CRITICAL);
            //NetworkManager.getInstance().addToQueue(request);
            
            request.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    String res = new String(request.getResponseData());
                    System.out.println(res);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(request);
        } catch (IOException ex) {
            System.out.println(ex.toString());
            //Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<Product> convertProduct(String json) {

        ArrayList<Product> listProduits = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Product e = new Product();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
                e.setRating(Float.parseFloat(obj.get("rating").toString()));
                e.setReduction(Float.parseFloat(obj.get("reduction").toString()));
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setName(obj.get("name").toString());
                e.setDescription(obj.get("description").toString());
                e.setPrice(Float.parseFloat(obj.get("price").toString()));
                e.setImage_name(obj.get("imageName").toString());
                System.out.println(e);
                listProduits.add(e);

            }

        } catch (IOException ex) {
        }
        
        return listProduits;

    }
    
    ArrayList<Product> Products = new ArrayList<>();
    public ArrayList<Product> getProducts(){
        ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://localhost/testfos/web/app_dev.php/productsJson");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Products = convertProduct(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Products;
    }
    
    public ArrayList<Product> getProductsBySubcat(int id){
        ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://localhost/testfos/web/app_dev.php/productsJson/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Products = convertProduct(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Products;
    }
}
