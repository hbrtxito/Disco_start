/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tiendacds;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



import java.awt.Color;
import java.awt.Dimension;


import org.jfree.chart.*;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author
 */
public class SellersView extends JFrame {

// componentes visuales
    static JTextArea nameSellerTextArea;
    static JTextArea phoneSellerTextArea;
    static JTextArea resolutionTextArea;

    static JComboBox comboAllSellers;
    static JComboBox comboAllAlbums;
    static JComboBox comboSotanoAlbums;
    static JComboBox comboMoneySellers;

    static JLabel message;
    static JLabel jLabelsotano;

    static JTextArea albumTitleTextArea;
    static JTextArea albumArtistNameTextArea;
    static JTextArea albumPriceTextArea;

    static JLabel jLabelNameSeller;
    static JLabel jLabelPhoneSeller;
    static JLabel jLabelDateSale;

    static JLabel jLabelallSellers;
    static JLabel jLabelVenta;
    static JLabel jLabelMoneySellers;

    static JButton resolutionButton;

    static JList listSearch;

    static JTextField textField;

    public BufferedImage imageLogo;

    static int screenSize = 500;

    // objects services (SQL access)
    public static SellerServices sellerServices = new SellerServices();

    public static AlbumServices albumServices = new AlbumServices();

    public static SalesServices salesServices = new SalesServices();

    final DefaultListModel model = new DefaultListModel();

    public SellersView() {
        
        //When the application run automatically send to
        // the bargain basement the album that are registered more than 30 days.
        albumServices.passSotanoAutomatically();
        //******************************************//

        JPanel content = new JPanel();
        content.setLayout(null);

        content.setBackground(Color.ORANGE);
        // labels -NAME SELLER
        jLabelNameSeller = new JLabel("Seller Name ", JLabel.LEFT);
        jLabelNameSeller.setBounds(20, 10, 300, 30);
        content.add(jLabelNameSeller);

        //Text area - Seller Name
        nameSellerTextArea = new JTextArea("");
        nameSellerTextArea.setBounds(20, 40, 200, 40);
        content.add(nameSellerTextArea);

        //Label - PHONE SELLER
        jLabelPhoneSeller  = new JLabel("Phone Number", JLabel.LEFT);
        jLabelPhoneSeller .setBounds(20, 80, 300, 30);
        content.add(jLabelPhoneSeller);

        //TEXT AREA - PHONE SELLER
        phoneSellerTextArea = new JTextArea("");
        phoneSellerTextArea.setBounds(20, 110, 200, 40);
        content.add(phoneSellerTextArea);//

        //SELECTION OF SELLERS
        jLabelMoneySellers = new JLabel("EARNINGS: ", JLabel.LEFT);
        jLabelMoneySellers.setBounds(500, 450, 500, 30);
        content.add(jLabelMoneySellers);
        List<Seller> sellers = sellerServices.getAllSellers();

        // Earnings LABEL
        JLabel jLabelMoneySellersTitle = new JLabel("Select Seller to Show Earnings", JLabel.LEFT);
        jLabelMoneySellersTitle.setBounds(500, 390, 500, 30);
        content.add(jLabelMoneySellersTitle);

        //Date Sale
        jLabelDateSale = new JLabel("DATE :", JLabel.LEFT);
        jLabelDateSale.setBounds(770, 30, 500, 30);
        content.add(jLabelDateSale);

        //to show  the Sellers
        if (sellers != null) {
            comboMoneySellers = new JComboBox(sellers.toArray());//send the list
        } else {
            comboMoneySellers = new JComboBox();
        }

        //Settings of the combo of the seller - sHOW Earnings
        comboMoneySellers.setPreferredSize(new Dimension(285, 20));
        comboMoneySellers.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
        comboMoneySellers.setBounds(500, 420, 400, 27);
        content.add(comboMoneySellers);

        jLabelallSellers = new JLabel("Register Album for this Seller", JLabel.LEFT);
        jLabelallSellers.setBounds(20, 220, 300, 30);
        content.add(jLabelallSellers);

        //combo box to selling the album
        if (sellers != null) {
            comboAllSellers = new JComboBox(sellers.toArray());
        } else {
            comboAllSellers = new JComboBox();
        }
        //SETTINGS OF THE REGISTRATION OF THE ALBUM -SELLER
        comboAllSellers.setPreferredSize(new Dimension(285, 20));
        comboAllSellers.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
        comboAllSellers.setBounds(20, 260, 400, 27);

        //SALE LABEL
        jLabelVenta = new JLabel("SELL ALBUM", JLabel.LEFT);
        jLabelVenta.setBounds(500, 30, 300, 30);
        content.add(jLabelVenta);

        List<Album> albumsAll = albumServices.getAllAlbums("en_sotano = 0 AND sold_on = 0");

        //Combo box for Send to the Bargain Store
        if (albumsAll != null) {
            comboAllAlbums = new JComboBox(albumsAll.toArray());
        } else {
            comboAllAlbums = new JComboBox();
        }

        comboAllAlbums.setPreferredSize(new Dimension(285, 20));
        comboAllAlbums.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
        comboAllAlbums.setBounds(500, 60, 520, 27);
        content.add(comboAllAlbums);

        //Send to Bargain Basement -LABEL
        jLabelsotano = new JLabel("BARGAIN BASEMENT", JLabel.LEFT);
        jLabelsotano.setBounds(500, 140, 300, 30);
        content.add(jLabelsotano);
        //Combo box fot Bargains Basement albums
        if (albumsAll != null) {
            comboSotanoAlbums = new JComboBox(albumsAll.toArray());
        } else {
            comboSotanoAlbums = new JComboBox();
        }

        comboSotanoAlbums.setPreferredSize(new Dimension(285, 20));
        comboSotanoAlbums.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
        comboSotanoAlbums.setBounds(500, 170, 520, 27);
        content.add(comboSotanoAlbums);

        comboMoneySellers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //System.out.println("cambio");
                Seller seller = (Seller) comboMoneySellers.getSelectedItem();

                if (seller != null) {
                    //Will modify the string of the Earnings to show How much we earn per seller
                    String earnings = " EARNINGS:  This is 40% of their albums sold = " + sellerServices.getEarnings(seller.getId()) + " Dollars";

                    jLabelMoneySellers.setText(earnings);
                }

            }
        });

        content.add(comboAllSellers);

        albumTitleTextArea = new JTextArea("Enter Album Title");
        albumTitleTextArea.setBounds(20, 300, 200, 40);
        content.add(albumTitleTextArea);

        albumArtistNameTextArea = new JTextArea("Enter Artist Name");
        albumArtistNameTextArea.setBounds(20, 350, 200, 40);
        content.add(albumArtistNameTextArea);

        albumPriceTextArea = new JTextArea("Enter Price");
        albumPriceTextArea.setBounds(20, 400, 200, 40);
        content.add(albumPriceTextArea);

        resolutionTextArea = new JTextArea("Enter Resolution");
        resolutionTextArea.setBounds(250, 40, 200, 40);
        resolutionTextArea.setVisible(false);
        content.add(resolutionTextArea);

       //String that will show the message for an action
        message = new JLabel("", JLabel.LEFT);
        message.setBounds(300, 600, 500, 40);
        content.add(message);

        JButton addSellerButton = new JButton("Add New seller");

        addSellerButton.setBounds(240, 40, 200, 40);
        content.add(addSellerButton, BorderLayout.CENTER);
        addSellerButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {

                Seller newSeller = new Seller();

                String nameSeller = nameSellerTextArea.getText();
                if (!nameSeller.isEmpty()) {
                    newSeller.setName(nameSeller);
                }

                String phoneSellerNew = phoneSellerTextArea.getText();

                if (!phoneSellerNew.isEmpty()) {
                    newSeller.setPhone(phoneSellerNew);
                }

                if (sellerServices.insert(newSeller)) {
                    message.setText(" New Seller Successful");
                    newSeller.setId(sellerServices.getLastInsert());
                    jLabelDateSale.setText("Sale Date:");

                    nameSellerTextArea.setText("");
                    phoneSellerTextArea.setText("");

                    comboAllSellers.addItem(newSeller);
                    comboMoneySellers.addItem(newSeller);

                } else {
                    message.setText("Error adding new Seller");
                }

            }
        });

        JButton addAlbumButton = new JButton("Add New Album");
        //OKButton.addActionListener(new MyAction());
        addAlbumButton.setBounds(240, 300, 200, 40);
        content.add(addAlbumButton, BorderLayout.CENTER);
        addAlbumButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    Album newAlbum = new Album();

                    String albumTitleNew = albumTitleTextArea.getText();
                    if (!albumTitleNew.isEmpty()) {
                        newAlbum.setTitle(albumTitleNew);
                    }

                    String albumArtistNameNew = albumArtistNameTextArea.getText();

                    if (!albumArtistNameNew.isEmpty()) {
                        newAlbum.setArtistName(albumArtistNameNew);
                    }

                    String priceAlbumNew = albumPriceTextArea.getText();
                    if (!priceAlbumNew.isEmpty()) {
                        newAlbum.setPrice(Double.parseDouble(priceAlbumNew));
                    }

                    newAlbum.setInSotano(0);
                    newAlbum.setSoldOn(0);

                    Seller sellerSeleted = (Seller) comboAllSellers.getSelectedItem();
                    
                    if(sellerSeleted != null){
                        newAlbum.setSeller_id(sellerSeleted.getId());
                    }

                    if (albumServices.insert(newAlbum, sellerSeleted)) {
                        message.setText("New Album Successful");
                        newAlbum.setId(albumServices.getLastInsert());
                        jLabelDateSale.setText("Date Sale");

                        comboAllAlbums.addItem(newAlbum);
                        comboSotanoAlbums.addItem(newAlbum);

                        albumTitleTextArea.setText("Album Title");
                        albumArtistNameTextArea.setText("Artist Name");
                        albumPriceTextArea.setText("Album Price");

                    } else {
                        message.setText("Error adding new Album");
                    }
                } catch (Exception ex) {
                    message.setText("Error adding new Album, please check the parameters");
                }
            }
        });
        content.add(addAlbumButton, BorderLayout.CENTER);
        ArrayList<Double> mes = new ArrayList<>();

        for (int i =0  ; i<12 ; i++){
            Double temp = sellerServices.getEarningspermoth(12);
            mes.add(temp);

        }



        // to draw the charts
        JButton Monthly_Graphics = new JButton("Monthly Earnings ");
        Monthly_Graphics.setBounds(500, 350, 200, 40);
        content.add(Monthly_Graphics, BorderLayout.CENTER);

        Monthly_Graphics.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(11.5,"Earnings", "September");
                dcd.setValue(168.8,"Earnings", "October");
                dcd.setValue(58.8,"Earnings", "November");
                dcd.setValue(mes.get(1),"Earnings", "December");

                JFreeChart jFreeChart = ChartFactory.createBarChart("Monthly Earnings","Seller Name ","Earnings",dcd
                        ,PlotOrientation.VERTICAL,true,true,false);
                CategoryPlot plot = jFreeChart.getCategoryPlot();
                plot.setRangeGridlinePaint(Color.black);


                ChartFrame chrtFrame = new ChartFrame("Student Record", jFreeChart, true);

                chrtFrame.setVisible(true);
                chrtFrame.setSize(500,400);

            }
        });


        //---------------
        JButton sellAlbumButton = new JButton("Sell Album ");
        //OKButton.addActionListener(new MyAction());
        sellAlbumButton.setBounds(800, 100, 200, 40);
        content.add(sellAlbumButton, BorderLayout.CENTER);
        sellAlbumButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {

                Album albumSeleted = (Album) comboAllAlbums.getSelectedItem();

                if (albumSeleted != null) {
                    Sales sale = new Sales();

                    sale.setDate_sale(new Date());
                    sale.setPrice(albumSeleted.getPrice());

                    if (salesServices.insert(albumSeleted, sale)) {
                        comboAllAlbums.removeItem(albumSeleted);
                        comboSotanoAlbums.removeItem(albumSeleted);
                        message.setText("Sale successful!");
                        jLabelDateSale.setText("Date Sale " + sale.getDate_sale());
                    } else {
                        message.setText("Error on sale");
                    }
                }
            }
        });
        content.add(sellAlbumButton, BorderLayout.CENTER);

        //--------------------------
        JButton passSotanoButton = new JButton("Send to Bargain Basement");

        passSotanoButton.setBounds(800, 220, 200, 40);
        content.add(passSotanoButton, BorderLayout.CENTER);
        passSotanoButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {

                Album albumSeleted = (Album) comboSotanoAlbums.getSelectedItem();

                if (albumSeleted != null) {

                    if (albumServices.passSotano(albumSeleted)) {
                        comboAllAlbums.removeItem(albumSeleted);
                        comboSotanoAlbums.removeItem(albumSeleted);

                        message.setText("Album  \" " + albumSeleted.getTitle()+ " \" was sent to the Basement ");
                    }

                }
            }
        });
        content.add(passSotanoButton, BorderLayout.CENTER);
        //-----------------------
        JButton searchButton = new JButton("Search...");
        //OKButton.addActionListener(new MyAction());
        searchButton.setBounds(750, 500, 200, 30);
        content.add(searchButton, BorderLayout.CENTER);
        searchButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Album> list = albumServices.getByTitle(textField.getText());
                //This is just in case that I want to implement a search for ID
                //List<Album> list2 = albumServices.getBySellerId(textField.getText());

                model.removeAllElements();

                if (list != null) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        Album next = (Album) it.next();
                        model.addElement(next);
                        //listSearch.add(next);
                    }
                }

            }
        });
        content.add(passSotanoButton, BorderLayout.CENTER);

        //Text Area to Search an Album
        textField = new JTextField(20);
        textField.setBounds(500, 500, 200, 30);
        content.add(textField);

        //Scroll pane to see the results of the search
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(500, 540, 450, 110);
        listSearch = new JList(model);
        listSearch.setBounds(500, 500, 450, 100);

        scrollPane.setViewportView(listSearch);
        content.add(scrollPane);
        //content.add(listSearch);
        ImageIcon icon = null;
        try {

            imageLogo = ImageIO.read(new File("src/logo.jpg"));

            icon = new ImageIcon(imageLogo);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        JLabel logo = new JLabel(icon);

        logo.setBounds(50, 500, imageLogo.getWidth(), imageLogo.getHeight());

        content.add(logo);

        //window.setUndecorated(true);   //Hides the title bar.
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //Quit the program when we close this window
        this.setContentPane(content);
        this.setSize(screenSize + 550, screenSize + 200);
        this.setLocation(40, 40);    //Where on the screen will this window appear?
        this.setVisible(true);
    }

}
