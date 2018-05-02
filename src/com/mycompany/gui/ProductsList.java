/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entity.Product;
import java.util.ArrayList;
import java.util.Collections;
import com.mycompany.Service.ProductService;

/**
 *
 * @author yassi
 */
public class ProductsList {

    private Form f;
    private Resources theme;
    private ArrayList<Product> products;
    private ArrayList<Product> currentProducts;
    private Container list;

    public ProductsList() {

        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Produits", new BoxLayout(BoxLayout.Y_AXIS));
        Container filtersZoneCtn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container filtersCtn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
            ShowBrand sb = new ShowBrand();
            sb.show();
        });
        //Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //cnt.setScrollableY(true);

        ProductService p = new ProductService();
        products = p.getProducts();
        currentProducts = products;

        TextField productTF = new TextField("", "Nom de produit");

        Container grid = new Container(new GridLayout(3, 2));

        Button showFilterBtn = new Button("filters");
        Button highPriceBtn = new Button("Plus cher");
        Button lowPriceBtn = new Button("moins cher");
        Button highRatedBtn = new Button("Plus populaire");
        Button lowRatedBtn = new Button("Moins populaire");
        Button resetBtn = new Button("reset");
        Button searchBtn = new Button("chercher");

        grid.add(highPriceBtn);
        grid.add(lowPriceBtn);
        grid.add(highRatedBtn);
        grid.add(lowRatedBtn);
        grid.add(resetBtn);
        grid.add(searchBtn);

        filtersCtn.add(productTF);
        filtersCtn.add(grid);

        filtersZoneCtn.add(showFilterBtn);

        f.add(filtersZoneCtn);

        showFilterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (filtersCtn.getParent() != null) {
                    filtersZoneCtn.removeComponent(filtersCtn);
                } else {
                    filtersZoneCtn.addComponent(filtersCtn);
                }
                f.removeComponent(filtersZoneCtn);
                f.addComponent(0, filtersZoneCtn);
                filtersZoneCtn.getParent().animateLayout(300);
            }
        });

        lowPriceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Collections.sort(currentProducts, (p1, p2) -> (int) (p1.getPrice() - p2.getPrice()));
                //currentProducts = (ArrayList<Product>) currentProducts.stream().sorted((Product p1, Product p2) -> (int) (p1.getPrice() - p2.getPrice()));
                f.removeComponent(list);
                loadProducts();
                f.addComponent(list);
            }
        });
        highPriceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Collections.sort(currentProducts, (p1, p2) -> (int) (p2.getPrice() - p1.getPrice()));
                //currentProducts = (ArrayList<Product>) currentProducts.stream().sorted((Product p1, Product p2) -> (int) (p2.getPrice() - p1.getPrice()));
                f.removeComponent(list);
                loadProducts();
                f.addComponent(list);
            }
        });

        lowRatedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Collections.sort(currentProducts, (p1, p2) -> (int) (p1.getRating() - p2.getRating()));
                //currentProducts = (ArrayList<Product>) currentProducts.stream().sorted((Product p1, Product p2) -> (int) (p1.getRating() - p2.getRating()));
                f.removeComponent(list);
                loadProducts();
                f.addComponent(list);
            }
        });

        highRatedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Collections.sort(currentProducts, (p1, p2) -> (int) (p2.getRating() - p1.getRating()));
                //currentProducts = (ArrayList<Product>) currentProducts.stream().sorted((Product p1, Product p2) -> (int) (p2.getRating() - p1.getRating()));
                f.removeComponent(list);
                loadProducts();
                f.addComponent(list);
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                currentProducts = products;
                f.removeComponent(list);
                loadProducts();
                f.addComponent(list);
            }
        });

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList<Product> aux = products;
                ArrayList<Product> res = new ArrayList<>();
                for (Product product : aux) {
                    if (product.getName().toUpperCase().startsWith(productTF.getText().toUpperCase())) {
                        res.add(product);
                    }
                }
                if (!res.isEmpty()) {
                    currentProducts = res;
                    f.removeComponent(list);
                    loadProducts();
                    f.addComponent(list);
                }
                //currentProducts = (ArrayList<Product>) currentProducts.stream().filter((Product p) -> p.getName().toUpperCase().startsWith(productTF.getText().toUpperCase()));

            }
        });
        loadProducts();
        list.setScrollVisible(true);
        f.add(list);
        f.setScrollVisible(true);
        //f.show();
    }

    public void show() {
        f.show();
    }

    public ProductsList(int id) {
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Produits", new BoxLayout(BoxLayout.Y_AXIS));
        Container filtersZoneCtn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container filtersCtn = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        //Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //cnt.setScrollableY(true);
        ProductService p = new ProductService();
        products = p.getProductsBySubcat(id);
        currentProducts = products;

        TextField productTF = new TextField("", "Nom de produit");

        Container grid = new Container(new GridLayout(3, 2));

        Button showFilterBtn = new Button("filters");
        Button highPriceBtn = new Button("Plus cher");
        Button lowPriceBtn = new Button("moins cher");
        Button highRatedBtn = new Button("Plus populaire");
        Button lowRatedBtn = new Button("Moins populaire");
        Button resetBtn = new Button("reset");
        Button searchBtn = new Button("chercher");

        grid.add(highPriceBtn);
        grid.add(lowPriceBtn);
        grid.add(highRatedBtn);
        grid.add(lowRatedBtn);
        grid.add(resetBtn);
        grid.add(searchBtn);

        filtersCtn.add(productTF);
        filtersCtn.add(grid);

        filtersZoneCtn.add(showFilterBtn);

        f.add(filtersZoneCtn);

        showFilterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (filtersCtn.getParent() != null) {
                    filtersZoneCtn.removeComponent(filtersCtn);
                } else {
                    filtersZoneCtn.addComponent(filtersCtn);
                }
                f.removeComponent(filtersZoneCtn);
                f.addComponent(0, filtersZoneCtn);
                filtersZoneCtn.getParent().animateLayout(300);
            }
        });

        lowPriceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Collections.sort(currentProducts, (p1, p2) -> (int) (p1.getPrice() - p2.getPrice()));
                //currentProducts = (ArrayList<Product>) currentProducts.stream().sorted((Product p1, Product p2) -> (int) (p1.getPrice() - p2.getPrice()));
                f.removeComponent(list);
                loadProducts();
                f.addComponent(list);
            }
        });
        highPriceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Collections.sort(currentProducts, (p1, p2) -> (int) (p2.getPrice() - p1.getPrice()));
                //currentProducts = (ArrayList<Product>) currentProducts.stream().sorted((Product p1, Product p2) -> (int) (p2.getPrice() - p1.getPrice()));
                f.removeComponent(list);
                loadProducts();
                f.addComponent(list);
            }
        });

        lowRatedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Collections.sort(currentProducts, (p1, p2) -> (int) (p1.getRating() - p2.getRating()));
                //currentProducts = (ArrayList<Product>) currentProducts.stream().sorted((Product p1, Product p2) -> (int) (p1.getRating() - p2.getRating()));
                f.removeComponent(list);
                loadProducts();
                f.addComponent(list);
            }
        });

        highRatedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Collections.sort(currentProducts, (p1, p2) -> (int) (p2.getRating() - p1.getRating()));
                //currentProducts = (ArrayList<Product>) currentProducts.stream().sorted((Product p1, Product p2) -> (int) (p2.getRating() - p1.getRating()));
                f.removeComponent(list);
                loadProducts();
                f.addComponent(list);
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                currentProducts = products;
                f.removeComponent(list);
                loadProducts();
                f.addComponent(list);
            }
        });

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList<Product> aux = products;
                ArrayList<Product> res = new ArrayList<>();
                for (Product product : aux) {
                    if (product.getName().toUpperCase().startsWith(productTF.getText().toUpperCase())) {
                        res.add(product);
                    }
                }
                if (!res.isEmpty()) {
                    currentProducts = res;
                    f.removeComponent(list);
                    loadProducts();
                    f.addComponent(list);
                }
                //currentProducts = (ArrayList<Product>) currentProducts.stream().filter((Product p) -> p.getName().toUpperCase().startsWith(productTF.getText().toUpperCase()));

            }
        });
        loadProducts();
        //list.setScrollVisible(true);
        f.add(list);
        f.setScrollVisible(true);
        //f.show();
    }

    private void loadProducts() {
        list = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Product product : currentProducts) {
            list.add(SetCell(product));
            //cnt.add(createProductsComponent(product));
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    private Container SetCell(Product p) {
        try {
            Container cell = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container con = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
            Container actionsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));

            //cell.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(f.getWidth(), f.getWidth() / 2, 0xffff0000), true);
            URLImage i = URLImage.createToStorage(placeholder,
                    "http://" + p.getImage_name(),
                    "http://" + p.getImage_name());
            i.fetch();
            //cell.getAllStyles().setBgImage(i);
            //cell.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            ImageViewer image = new ImageViewer(i);

            ImageViewer nameImg = new ImageViewer(theme.getImage("informationoutline.png"));
            Label nameLbl = new Label(p.getName());

            ImageViewer priceImg = new ImageViewer(theme.getImage("currencyusd.png"));
            Label priceLbl = new Label(String.valueOf(p.getPrice()));

            ImageViewer cartImg = new ImageViewer(theme.getImage("cartplus.png"));
            Label cartLbl = new Label("To cart");

            //Slider rating = createStarRankSlider();
            actionsContainer.add(nameImg);
            actionsContainer.add(nameLbl);
            actionsContainer.add(priceImg);
            actionsContainer.add(priceLbl);
            //actionsContainer.add(cartImg);
            //actionsContainer.add(cartLbl);

            con.add(BorderLayout.CENTER, actionsContainer);

            cell.add(image);
            cell.add(con);
            //cell.add(rating);

            nameLbl.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    InteractionDialog dlg = new InteractionDialog("Actions");
                    dlg.setLayout(new BorderLayout());
                    dlg.add(BorderLayout.CENTER, new Label("Veuillez choisir une action :"));
                    Button cart = new Button("Ajouter au panier");
                    cart.addActionListener((ee) -> {
                        // ryadh
                        dlg.dispose();
                    });
                    Button details = new Button("Details");

                    details.addActionListener((ee) -> {
                        ProductDetails f2 = new ProductDetails(p);
                        f2.getF().getToolbar().addCommandToLeftBar("Back", null, new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                f.showBack();

                            }
                        });
                        dlg.dispose();
                        f2.show();
                    });
                    Container cnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    cnt.add(cart);
                    cnt.add(details);
                    dlg.addComponent(BorderLayout.SOUTH, cnt);
                    Dimension pre = dlg.getContentPane().getPreferredSize();
                    dlg.show(0, 0, 0, 0);
                    //dlg.show(0, 0, Display.getInstance().getDisplayWidth() - (pre.getWidth() + pre.getWidth() / 2), 0);

                }
            });

            cartLbl.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("to cart");
                }
            });

            cell.setLeadComponent(nameLbl);

            return cell;
        } catch (Exception ex) {
            System.out.println("dat error");
        }
        return null;
    }

    private Container createProductsComponent(Product p) {
        EncodedImage placeholder = EncodedImage.createFromImage(theme.getImage("plc.jpg"), true);
        URLImage img = URLImage.createToStorage(placeholder,
                "http://" + p.getImage_name(),
                "http://" + p.getImage_name());

        //ImageViewer image = new ImageViewer(i);
        //Image img = theme.getImage(p.getImageName());
        Container mb = new Container(new BorderLayout());
        mb.setSize(new Dimension(350, 350));
        mb.getUnselectedStyle().setBgImage(img);
        mb.getSelectedStyle().setBgImage(img);
        mb.getPressedStyle().setBgImage(img);
        mb.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        mb.getSelectedStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        mb.getPressedStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Container box = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button title = new Button(p.getName());
        title.setUIID("DishTitle");
        Label highlights = new Label(String.valueOf(p.getRating()));
        TextArea details = new TextArea(p.getDescription());
        details.setUIID("DishBody");
        highlights.setUIID("DishBody");
        Label price = new Label(String.valueOf(p.getPrice()));
        price.setUIID("DishPrice");
        box.addComponent(title);
        box.addComponent(highlights);

        Container boxAndPrice = new Container(new BorderLayout());
        boxAndPrice.addComponent(BorderLayout.CENTER, box);
        boxAndPrice.addComponent(BorderLayout.EAST, price);
        mb.addComponent(BorderLayout.SOUTH, boxAndPrice);

        mb.setLeadComponent(title);

        title.addActionListener((e) -> {
            ProductDetails f2 = new ProductDetails(p);

            /*if (highlights.getParent() != null) {
                box.removeComponent(highlights);
                box.addComponent(details);
            } else {
                box.removeComponent(details);
                box.addComponent(highlights);
            }
            mb.getParent().animateLayout(300);*/
        });
        return mb;
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
}
