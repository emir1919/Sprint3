/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Slider;
import com.codename1.ui.Stroke;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.GeneralPath;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author Emir
 */
public class ArcProgress extends Slider{
     private int arcWidth = 10;

    public ArcProgress() {
        setRenderPercentageOnTop(true);
    }

    public void setArcWidth(int arcWidth) {
        this.arcWidth = arcWidth;
    }

    @Override
    public void paint(Graphics g) {
        Style style = getStyle();
        int wPadding = style.getPadding(isRTL(), Component.RIGHT) + style.getPadding(isRTL(), Component.LEFT);
        int hPadding = style.getPadding(Component.TOP) + style.getPadding(Component.BOTTOM);
        GeneralPath path = new GeneralPath();
        int size = Math.min(getWidth() - wPadding, getHeight() - hPadding);
        int x = getX() + style.getPadding(isRTL(), Component.LEFT) + arcWidth/2;
        int y = getY() + style.getPadding(Component.TOP) + arcWidth/2;
        if(style.getAlignment() == CENTER){
            x = getX() + (getWidth() - size)/2;
        }else if(style.getAlignment() == RIGHT){
            x = getX() + getWidth() - (style.getPadding(isRTL(), Component.RIGHT) + arcWidth/2);           
        }

        path.arc(x, y, size, size, -Math.PI / 4, Math.PI * 6 / 4);
        Stroke stroke1 = new Stroke(arcWidth, Stroke.CAP_ROUND, Stroke.JOIN_ROUND, 4);
        g.setAntiAliased(true);
        g.setColor(getStyle().getBgColor());
        g.drawShape(path, stroke1);
        int p = getProgress();
        GeneralPath path1 = new GeneralPath();
        path1.arc(x, y, size, size, -Math.PI / 4 + (Math.PI * 6 / 4) * (100 - p) / 100, (Math.PI * 6 / 4) * p / 100);
        g.setColor(getStyle().getFgColor());
        g.drawShape(path1, stroke1);

        if (isRenderPercentageOnTop()) {
            String val = formattedValue(p);
            g.setFont(style.getFont());
            g.drawString(val, x + (size + arcWidth - style.getFont().stringWidth(val)) / 2, y + (size - style.getFont().getHeight()) / 2);
        }
    }

    public void paintComponentBackground(Graphics g) {
    }

    @Override
    protected Dimension calcPreferredSize() {
        Style style = getStyle();
        int prefW = Display.getInstance().getDisplayWidth() * 30 / 100, prefH = prefW;

        prefH += (style.getPadding(false, Component.TOP) + style.getPadding(false, Component.BOTTOM));
        prefW += (style.getPadding(isRTL(), Component.RIGHT) + style.getPadding(isRTL(), Component.LEFT));

        return new Dimension(prefW, prefH);
    }

}
