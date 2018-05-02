/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entity.Subcategory;
import com.mycompany.Service.SubcategoryService;

import java.util.ArrayList;


/**
 *
 * @author yassi
 */
public class CategoriesList {
    private Form f;
    private Resources theme;
    private ArrayList<Subcategory> subcategories;

    public CategoriesList() {
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Categories", new BoxLayout(BoxLayout.Y_AXIS));
        Container list = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        subcategories = new SubcategoryService().getSubcats();
        f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
ShowBrand sb = new ShowBrand();
            sb.show();        });
        for (Subcategory s : subcategories) {
            list.add(SetCell(s));
        }
        
        f.add(list);
        f.show();
    }
    
    private Container SetCell(Subcategory s){
        Container cell = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container con = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        Container actionsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        EncodedImage placeholder =  EncodedImage.createFromImage(Image.createImage(f.getWidth(), f.getWidth() / 4, 0xffff0000), true);
        URLImage i = URLImage.createToStorage(placeholder,
                "http://"+s.getImage_name(),
                "http://"+s.getImage_name());
        i.fetch();
        ImageViewer image = new ImageViewer(i);
        
        ImageViewer nameImg = new ImageViewer(theme.getImage("informationoutline.png"));
        Label nameLbl = new Label(s.getName());
        
        actionsContainer.add(nameImg);
        actionsContainer.add(nameLbl);
        
        
        con.add(BorderLayout.CENTER, actionsContainer);
        
        cell.add(image);
        cell.add(con);
        
        nameLbl.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                System.out.println("to filter by cat");
                ProductsList f2 = new ProductsList(s.getId());
                /*ProductDetails f2 = new ProductDetails(s);*/
                f2.getF().getToolbar().addCommandToLeftBar("Back", null, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        f.showBack();

                    }
                });
                f2.show();
            }
        });
        
        cell.setLeadComponent(nameLbl);
        
        return cell;
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
