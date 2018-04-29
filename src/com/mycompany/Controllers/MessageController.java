/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controllers;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.mycompany.Entity.Comment;
import com.mycompany.Entity.Msg;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emir
 */
public class MessageController {

    public ArrayList<Msg> getMsgReceive(String json) throws IOException {
        ArrayList<Msg> listmsg = new ArrayList<>();

        JSONParser j = new JSONParser();

        Map<String, Object> msgs = j.parseJSON(new CharArrayReader(json.toCharArray()));
        System.out.println(msgs);

        List<Map<String, Object>> list = (List<Map<String, Object>>) msgs.get("root");
        for (Map<String, Object> obj : list) {
            Msg e = new Msg();

            // System.out.println(obj.get("id"));
            float id = Float.parseFloat(obj.get("id").toString());
            float recepteur_id = Float.parseFloat(obj.get("recepteur_id").toString());
            float emetteur_id = Float.parseFloat(obj.get("emetteur_id").toString());
            float lu = Float.parseFloat(obj.get("lu").toString());

            System.out.println(id);
            e.setId((int) id);
            e.setSubject(obj.get("subject").toString());
            e.setBody(obj.get("body").toString());
            e.setLu((int) lu);
            e.setEmetteur_id((int)emetteur_id );
            e.setRecepteur_id(((int) recepteur_id));

            e.setDateEnvoi(obj.get("DateEnvoi").toString());

            System.out.println(e);
            listmsg.add(e);

        }
        return listmsg;
    }
}
