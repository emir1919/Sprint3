/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.BarChart;
import com.codename1.ui.Form;
import com.mycompany.Entity.Enseigne;
import com.mycompany.Service.BakeryService;
import com.mycompany.Service.RatingService;
import com.mycompany.Service.ServiceBrand;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emir
 */
public class SalesBarChart2  extends AbstractDemoChart {

  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Sales horizontal bar chart";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "The monthly sales for the last 2 years (horizontal bar chart)";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Form execute() {
      
    String[] titles = new String[] { "2018" };
    List<double[]> values = new ArrayList<double[]>();
    //values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030});
    //values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200, 22030, 21200, 19500, 15500,
        //12600, 14000 });
    int[] colors = new int[] { ColorUtil.CYAN, ColorUtil.BLUE };
    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
    renderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
    setChartSettings(renderer, "Enseigne par note", "Enseigne", "Note", 0,
        8, 0, 7, ColorUtil.GRAY, ColorUtil.LTGRAY);
    renderer.setXLabels(1);
    renderer.setYLabels(10);
      ServiceBrand sb=new ServiceBrand();
      List<Enseigne> ls=new ArrayList<Enseigne>();
      ls=sb.getListBrand();
      RatingService rs=new RatingService();
      BakeryService bs=new BakeryService();
    /////axe des x
    
    String[] aa=new String[20];
    double[] dd=new double[20];
    aa=bs.getBrandName();
dd=bs.getNbrBakeryValue();
for(int i=0;i<aa.length;i++){
        renderer.addXTextLabel(i+1, aa[i]);
    }
    /*for(int i=0;i<ls.size();i++){
        dd[i]=rs.testrate(ls.get(i).getId());
    }*/
            values.add(dd);

    /*renderer.addXTextLabel(1, "Jan");
    renderer.addXTextLabel(3, "Mar");
    renderer.addXTextLabel(5, "May");
    renderer.addXTextLabel(7, "Jul");
    renderer.addXTextLabel(10, "Oct");
    renderer.addXTextLabel(12, "Dec");*/
    int length = renderer.getSeriesRendererCount();
    for (int i = 0; i < length; i++) {
      XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
      seriesRenderer.setDisplayChartValues(true);
    }
    
    BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
        BarChart.Type.DEFAULT);
    return wrap("", new ChartComponent(chart));
    
  }
}
