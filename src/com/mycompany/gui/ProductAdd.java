/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entity.Subcategory;
import com.mycompany.Entity.Product;
import com.mycompany.Service.SubcategoryService;
import com.mycompany.Service.ProductService;

import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author yassi
 */
public class ProductAdd {
    private Form f;
    private Resources theme;
    private ArrayList<Subcategory> subcategories;
    private String photo = "";
    
    public ProductAdd() {
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Ajouter Produit", new BoxLayout(BoxLayout.Y_AXIS));
        subcategories = new SubcategoryService().getSubcats();
        Container lay = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        // add image here
        ImageViewer imgV = new ImageViewer();
        EncodedImage placeholder =  EncodedImage.createFromImage(Image.createImage(f.getWidth(), f.getWidth() / 4, 0xffff0000), true);
        imgV.setImage(placeholder);
        // form
        Button getImgBtn = new Button("Capturer");
        
        getImgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                photo = Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
                if(photo != null){
                    try {
                        Image i = Image.createImage(photo);
                        
                        imgV.setImage(i);
                        System.out.println(photo);
                    } catch (IOException ex) {
                        System.out.println(ex.toString());
                    }
                }
                
            }
        });
        
        TextField nameTf = new TextField("", "Nom");
        
        Slider priceS = new Slider();
        priceS.setMaxValue(999);
        priceS.setMinValue(1);
        priceS.setEditable(true);
        priceS.setIncrements(10);
        Label priceLbl = new Label("Prix : ");
        
        
        Container priceCnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
        priceCnt.add(priceLbl);
        priceCnt.add(priceS);
        
        Slider discS = new Slider();
        discS.setMaxValue(100);
        discS.setMinValue(1);
        discS.setEditable(true);
        discS.setIncrements(5);
        Label discLbl = new Label("Promotion : ");
        
        Container discCnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        discCnt.add(discLbl);
        discCnt.add(discS);
        
        ComboBox subsCB = new ComboBox();
        for (Subcategory s : subcategories) {
            subsCB.addItem(s);
        }
        TextArea descTA = new TextArea(3, 2);
        descTA.setMaxSize(255);
        
        Button addBtn = new Button("ajouter");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (nameTf.getText() != null && !nameTf.getText().equals("") && photo != null) {
                    Product p = new Product();
                    p.setName(nameTf.getText());
                    p.setPrice(priceS.getProgress());
                    p.setReduction(discS.getProgress());
                    p.setDescription(descTA.getText());
                    Subcategory s = (Subcategory) subsCB.getSelectedItem();
                    p.setSubcategory_id(s.getId());
                    //img
                    ProductService service = new ProductService();

                    //File f = new File(photo);
                    System.out.println(photo);
                    //Upload u = new Upload();
                    //String res = u.UploadFile(f);
                    //p.setImage_name(res);
                    service.addProduct(p, photo);

                } else {
                    Dialog.show("Erreur", "Champs vides", "Ok", "Cancel");
                }

            }
        });
        
        lay.add(imgV);
        lay.add(getImgBtn);
        lay.add(nameTf);
        lay.add(priceCnt);
        lay.add(discCnt);
        lay.add(subsCB);
        lay.add(descTA);
        lay.add(addBtn);
        
        f.add(lay);
        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Resources getTheme() {
        return theme;
    }

    public void setTheme(Resources theme) {
        this.theme = theme;
    }

    public ArrayList<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(ArrayList<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
    
    
}
