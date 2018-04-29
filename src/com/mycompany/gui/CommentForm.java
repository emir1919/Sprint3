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
import com.mycompany.Service.CommentService;
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
      getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Comment", "Title")
                )
        );
       getToolbar().addCommandToRightBar("back", null, (ev) -> {
            DetailBrand h = new DetailBrand();
            h.show();
        });
          ls=cs.GetAllCommentByBrand(idenseigne);
            Container c1=new Container(BoxLayout.y());

 for (Comment e : ls) {
            Label lb = new Label("");
            lb.setText(e.getContent());
            c1.add(lb);

 }
              add(c1);

  }
    
}
