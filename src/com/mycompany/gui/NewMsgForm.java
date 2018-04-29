/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.Entity.Msg;
import com.mycompany.Service.MessageService;
import static com.mycompany.gui.DetailBrand.idenseigne;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Emir
 */
public class NewMsgForm extends BaseForm {

    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_3 = new com.codename1.ui.TextField();

    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    MessageService ms = new MessageService();

    public NewMsgForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());

    }

    public NewMsgForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        installSidemenu(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);

    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Nouveau msg");
        setName("SignInForm");
        getToolbar().addCommandToRightBar("retour", null, (ev) -> {
            InboxForm h = new InboxForm();
            h.show();
        });
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        Label l1 = new Label("Email:");

        gui_Component_Group_1.addComponent(gui_Text_Field_3);

        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Text_Field_3.setName("Text_Field_3");
        gui_Text_Field_2.setText("Objet:");
        gui_Text_Field_3.setText("Destinataire:");

        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_1.setText("Contenu:");
        gui_Text_Field_1.setName("Text_Field_1");
        gui_Container_1.addComponent(gui_Button_2);
        //gui_Container_1.addComponent(gui_Button_3);
        /*gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));*/
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Envoyer");
        gui_Button_2.setName("Button_2");
        gui_Button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Msg m = new Msg();
                m.setBody(gui_Text_Field_1.getText());
                Date d = new Date();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

// Using DateFormat format method we can create a string 
// representation of a date with the defined format.
                String reportDate = df.format(new java.util.Date().getTime());
                m.setDateEnvoi(reportDate);
                m.setEmetteur_id(2);
                m.setRecepteur_id(1);
                m.setSubject(gui_Text_Field_2.getText());
                m.setLu(0);
                System.out.println(m.toString());
                ms.AddMsg(m);
                InboxForm h = new InboxForm();
            h.show();
            }
        });
        /*gui_Button_3.setText("Forgot Your Password");
        gui_Button_3.setUIID("CenterLabelSmall");
        gui_Button_3.setName("Button_3");*/
        //addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        /*gui_Button_1.setText("Create New Account");
        gui_Button_1.setUIID("CenterLabel");
        gui_Button_1.setName("Button_1");*/
    }// </editor-fold>

}
