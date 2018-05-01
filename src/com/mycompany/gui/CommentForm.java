/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.Entity.Comment;
import com.mycompany.Entity.Enseigne;
import com.mycompany.Entity.Msg;
import com.mycompany.Service.CommentService;
import com.mycompany.Service.UsersService;
import java.util.ArrayList;

/**
 *
 * @author Emir
 */
public class CommentForm extends BaseForm{
        public static int idenseigne = 0;

    CommentService cs=new CommentService();
    ArrayList<Comment> ls=new ArrayList<>();
  public CommentForm()
  {
           this(com.codename1.ui.util.Resources.getGlobalResources());

  }
  public CommentForm(com.codename1.ui.util.Resources resourceObjectInstance)
  {
      installSidemenu(resourceObjectInstance);
      
       getToolbar().addCommandToRightBar("back", null, (ev) -> {
            DetailBrand h = new DetailBrand();
            h.show();
        });
          /*ls=cs.GetAllCommentByBrand(idenseigne);
            Container c1=new Container(BoxLayout.y());
            Container c2=new Container(BoxLayout.x());

 for (Comment e : ls) {
            Label lb = new Label("");
            lb.setText(e.getContent());
            c2.add(lb);
 Label lb1 = new Label("");
            lb1.setText(e.getDateComment());
            c2.add(lb1);
             Label lb2 = new Label("");
            lb2.setText(""+e.getUser_id()+"");
            c2.add(lb2);
            c1.add(c2);
 }
              add(c1);*/
        initGuiBuilderComponents(resourceObjectInstance);

  }
              UsersService us=new UsersService();

  private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Comment", "Title")
                )
        );        setName("InboxForm");
        /////loula  
          ls=cs.GetAllCommentByBrand(idenseigne);
        System.out.println(ls.toString());
        for(Comment m:ls)
        {
         com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
     com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
     com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
     com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
     com.codename1.ui.Label gui_Label_4 = new com.codename1.ui.Label();
     com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
     com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label();
     com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
     com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
        addComponent(gui_Container_1);

        
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
        
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(gui_Label_1);
        gui_Label_1.setText(m.getDateComment());
        gui_Label_1.setUIID("SmallFontLabel");
        gui_Label_1.setName("Label_1");
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4);
        gui_Container_4.setName("Container_4");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        gui_Container_4.addComponent(gui_Label_4);
        gui_Label_4.setUIID("Padding2");
        gui_Label_4.setName("Label_4");
        gui_Label_4.setIcon(resourceObjectInstance.getImage("label_round.png"));
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
        gui_Container_3.setName("Container_3");
        gui_Container_3.addComponent(gui_Label_3);
        gui_Container_3.addComponent(gui_Label_2);
        //gui_Container_3.addComponent(gui_Text_Area_1);
            
        gui_Label_3.setText(""+us.getUserbyId(m.getUser_id()).getUsername()+"");
        gui_Label_3.setName("Label_3");
        gui_Label_2.setText(m.getContent());
        gui_Label_2.setUIID("RedLabel");
        gui_Label_2.setName("Label_2");
        /*gui_Text_Area_1.setText(m.getDateComment());
        gui_Text_Area_1.setUIID("SmallFontLabel");
        gui_Text_Area_1.setName("Text_Area_1");*/
        }  }
}
