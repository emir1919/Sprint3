/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entity.Msg;
import com.mycompany.Service.MessageService;
import com.mycompany.myapp.MyApplication;
import java.util.ArrayList;

/**
 *
 * @author Emir
 */
public class BaseForm extends Form {

    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");

        Image inboxImage = null;
        if (isCurrentInbox()) {
            inboxImage = selection;
        }

        Image trendingImage = null;
        if (isCurrentTrending()) {
            trendingImage = selection;
        }

        Image calendarImage = null;
        if (isCurrentCalendar()) {
            calendarImage = selection;
        }

        Image statsImage = null;
        if (isCurrentStats()) {
            statsImage = selection;
        }

        Button inboxButton = new Button("Inbox", inboxImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
                ArrayList<Msg> listmsg2=new ArrayList<>();
        MessageService ms=new MessageService();
                                listmsg2=ms.GetReceiveMsg(MyApplication.user.getId());

        Container inbox = FlowLayout.encloseMiddle(inboxButton,
                new Label(""+listmsg2.size(), "SideCommandNumber"));
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new InboxForm().show());
        getToolbar().addCommandToSideMenu("Enseignes", null, e -> new ShowBrand(res).show());
        //getToolbar().addCommandToSideMenu("Mes favoris", null, e -> new FavoryFrom(res).show());

        getToolbar().addComponentToSideMenu(inbox);
        /*getToolbar().addCommandToSideMenu("Enseignes", null, e -> new ShowBrand(res).show()
        );*/

        getToolbar().addCommandToSideMenu("Stats", statsImage, e -> new StatsForm(res).show());
        getToolbar().addCommandToSideMenu("Produits", statsImage, e -> new ProductsList().getF().show());
        getToolbar().addCommandToSideMenu("Categories", statsImage, e -> new CategoriesList().getF().show());
        //getToolbar().addCommandToSideMenu("Calendar", calendarImage, e -> new CalendarForm(res).show());
        //getToolbar().addCommandToSideMenu("Trending", trendingImage, e -> new TrendingForm(res).show());

        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
        getToolbar().addComponentToSideMenu(new Label(res.getImage("profile_image.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label("Detra Mcmunn", "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label("Long Beach, CA", "SideCommandSmall"));
    }

    protected boolean isCurrentInbox() {
        return false;
    }

    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
}
