/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controllers;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.mycompany.Entity.Comment;
import com.mycompany.Entity.Enseigne;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emir
 */
public class CommentControllers {
    public ArrayList<Comment> getListComment(String json) throws IOException {
        ArrayList<Comment> listComments = new ArrayList<>();

        JSONParser j = new JSONParser();

        Map<String, Object> Comments = j.parseJSON(new CharArrayReader(json.toCharArray()));
        System.out.println(Comments);

        List<Map<String, Object>> list = (List<Map<String, Object>>) Comments.get("root");
        for (Map<String, Object> obj : list) {
            Comment e = new Comment();

            // System.out.println(obj.get("id"));
            float id = Float.parseFloat(obj.get("id").toString());
            float user_id = Float.parseFloat(obj.get("enseigne_id").toString());
            float enseigne_id = Float.parseFloat(obj.get("user_id").toString());
            System.out.println(id);
            e.setId((int) id);
            e.setContent(obj.get("Content").toString());
            e.setEnseigne_id((int)enseigne_id);
            e.setUser_id((int)user_id);
            System.out.println(e);
            listComments.add(e);

        }
return listComments;
    }
}
