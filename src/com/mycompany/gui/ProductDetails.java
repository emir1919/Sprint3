/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.Entity.Product;
import com.mycompany.Entity.Review;
import java.util.ArrayList;
import com.mycompany.Service.ReviewService;
import com.mycompany.myapp.MyApplication;

/**
 *
 * @author yassi
 */
public class ProductDetails {
    private Form f;
    private Resources theme;
    private Product p;
    private Container commentsCnt;
    private int currentUser;
    
    public ProductDetails() {
        
    }

    public ProductDetails(Product p) {
        // init
        this.currentUser = 1;
        this.p = p;
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(p.getName(), new BoxLayout(BoxLayout.Y_AXIS));
        f.setScrollableY(true);
        
        // product details
        EncodedImage placeholder =  EncodedImage.createFromImage(Image.createImage(f.getWidth(), f.getWidth(), 0xffff0000), true);
        URLImage i = URLImage.createToStorage(placeholder,
                "http://"+p.getImage_name(),
                "http://"+p.getImage_name());
        
        ImageViewer image = new ImageViewer(i);
        
        
        ImageViewer nameImg = new ImageViewer(theme.getImage("informationoutline.png"));
        Label nameLbl = new Label(p.getName());
        
        ImageViewer priceImg = new ImageViewer(theme.getImage("currencyusd.png"));
        Label priceLbl = new Label(String.valueOf(p.getPrice()));
        
        ImageViewer discountImg = new ImageViewer(theme.getImage("sale.png"));
        Label discountLbl = new Label(String.valueOf(Math.round(p.getReduction()))+"%");
        
        Label descriptionTA = new Label(p.getDescription());
        
        Container infosCnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container detailsCnt =  new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        
        infosCnt.add(nameImg);
        infosCnt.add(nameLbl);
        infosCnt.add(priceImg);
        infosCnt.add(priceLbl);
        infosCnt.add(discountImg);
        infosCnt.add(discountLbl);
        
        Container rateCnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label noteLbl = new Label("Note : " + p.getRating() + "/5");
        rateCnt.add(infosCnt);
        rateCnt.add(noteLbl);
        
        detailsCnt.add(BorderLayout.NORTH, image);
        detailsCnt.add(BorderLayout.CENTER, rateCnt);
        detailsCnt.add(BorderLayout.SOUTH, descriptionTA);
        detailsCnt.setScrollableY(true);
        
        loadComments();
        
        f.add(detailsCnt);
        f.add(commentsCnt);
        f.setScrollableY(true);
        
        //f.show();
    }
    
    public void show(){
        f.show();
    }
    
    private void loadComments(){
        commentsCnt = new Container(new BorderLayout());
        Container addCommentCnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container commentsList = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        TextField commentT = new TextField("", "Commentaire");
        Slider ratingS = new Slider();
        ratingS.setMaxValue(6);
        ratingS.setMinValue(1);
        ratingS.setIncrements(1);
        ratingS.setProgress(3);
        ratingS.setEditable(true);
        Button addBtn = new Button("Ajouter");
        
        if(MyApplication.user == null){
            addBtn.setEnabled(false);
        }
        
        addCommentCnt.add(commentT);
        addCommentCnt.add(ratingS);
        addCommentCnt.add(addBtn);
        
        ArrayList<Review> reviews = getReviews(p.getId());
        for (Review review : reviews) {
            commentsList.add(SetCell(review));
        }
        
        commentsCnt.add(BorderLayout.NORTH, addCommentCnt);
        commentsCnt.add(BorderLayout.CENTER, commentsList);
        
        
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!commentT.getText().trim().equals("")) {
                    ReviewService service = new ReviewService();
                    Review r = new Review();
                    r.setProduct_id(p.getId());
                    r.setRating(ratingS.getProgress());
                    r.setUser_id(MyApplication.user.getId());
                    r.setReviewText(commentT.getText());
                    service.addReview(r);
                    /*commentsList.add(SetCell(res));*/
                    f.removeComponent(commentsCnt);
                    loadComments();
                    f.add(commentsCnt);
                    System.out.println("added");
                } else {
                    Dialog.show("Erreur", "Veuillez saisir un commentaire", "Ok", "");
                }

            }
        });
    }
    
    private Container SetCell(Review r){
        Container cell = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        //Label userLbl = new Label("user : ");
        Label reviewLbl = new Label(r.getReviewText());
        Label ratingLbl = new Label(String.valueOf(r.getRating()) +" stars");
        Button deleteBtn = new Button("Supprimer");
        
        if(MyApplication.user == null && MyApplication.user.getId() != r.getUser_id()){
            deleteBtn.setVisible(false);
        }
        
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ReviewService service = new ReviewService();
                service.removeReview(r.getId());
                f.removeComponent(commentsCnt);
                loadComments();
                f.add(commentsCnt);
                System.out.println("deleted");
            }
        });
        
        //cell.add(userLbl);
        cell.add(reviewLbl);
        cell.add(ratingLbl);
        cell.add(deleteBtn);
        
        cell.setLeadComponent(deleteBtn);
        
        return cell;
    }
    
    private ArrayList<Review> getReviews(int id){
        ReviewService service = new ReviewService();
        return service.getReviews(id);
    }
    
    

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }
    
    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
