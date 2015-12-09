/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tiendacds;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

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

    static JLabel jLabelallSellers;
    static JLabel jLabelVenta;
    static JLabel jLabelMoneySellers;

    static JButton resolutionButton;
    
    static JList listSearch;
    
    static JTextField textField;
    
    public BufferedImage imageLogo;

    static int screenSize = 500;


    // objetos services (SQL access)
    public static SellerServices sellerServices = new SellerServices();

    public static AlbumServices albumServices = new AlbumServices();
    
    public static SalesServices salesServices = new SalesServices();
    
    final DefaultListModel model = new DefaultListModel();
    

    public SellersView() {

        JPanel content = new JPanel();
        content.setLayout(null);

        content.setBackground(Color.ORANGE);
        
        jLabelNameSeller = new JLabel("Name Seller", JLabel.LEFT);
        jLabelNameSeller.setBounds(20, 10, 300, 30);
        content.add(jLabelNameSeller);

        nameSellerTextArea = new JTextArea("");
        nameSellerTextArea.setBounds(20, 40, 200, 40);
        content.add(nameSellerTextArea);

        jLabelNameSeller = new JLabel("Phone seller", JLabel.LEFT);
        jLabelNameSeller.setBounds(20, 80, 300, 30);
        content.add(jLabelNameSeller);

        phoneSellerTextArea = new JTextArea("");
        phoneSellerTextArea.setBounds(20, 110, 200, 40);
        content.add(phoneSellerTextArea);//

        
        
        
        jLabelMoneySellers = new JLabel("Earnings :", JLabel.LEFT);
        jLabelMoneySellers.setBounds(500, 400, 500, 30);
        content.add(jLabelMoneySellers);
        
        
        JLabel jLabelMoneySellersTitle = new JLabel("Select something for show earnings(selected seller)", JLabel.LEFT);
        jLabelMoneySellersTitle.setBounds(500, 350, 500, 30);
        content.add(jLabelMoneySellersTitle);
        
        List<Seller> sellers = sellerServices.getAllSellers();
        
        if(sellers!= null)
            comboMoneySellers = new JComboBox(sellers.toArray());//send the list
        else
            comboMoneySellers = new JComboBox();
        
        comboMoneySellers.setPreferredSize(new Dimension(285, 20));
        comboMoneySellers.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
        comboMoneySellers.setBounds(500, 380, 400, 20);
        content.add(comboMoneySellers);
        
        
        jLabelallSellers = new JLabel("Register an Album for this seller(Selected)", JLabel.LEFT);
        jLabelallSellers.setBounds(20, 220, 300, 30);
        content.add(jLabelallSellers);

        if(sellers != null){
            comboAllSellers = new JComboBox(sellers.toArray());
        }else{
            comboAllSellers = new JComboBox();
        }
                
        comboAllSellers.setPreferredSize(new Dimension(285, 20));
        comboAllSellers.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
        comboAllSellers.setBounds(20, 260, 400, 20);

        jLabelVenta = new JLabel("Sale this Album(Selected)", JLabel.LEFT);
        jLabelVenta.setBounds(470, 30, 300, 30);
        content.add(jLabelVenta);
        
        List<Album> albumsAll = albumServices.getAllAlbums("en_sotano = 0 AND sold_on = 0");
        if(albumsAll!=null){
            comboAllAlbums = new JComboBox(albumsAll.toArray());
        }else{
            comboAllAlbums = new JComboBox();
        }
        
        comboAllAlbums.setPreferredSize(new Dimension(285, 20));
        comboAllAlbums.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
        comboAllAlbums.setBounds(470, 70, 520, 20);
        content.add(comboAllAlbums);
        
        
        jLabelsotano = new JLabel("Pass to sotano(Selected)", JLabel.LEFT);
        jLabelsotano.setBounds(470, 140, 300, 30);
        content.add(jLabelsotano);
        
        if(albumsAll!=null){
            comboSotanoAlbums = new JComboBox(albumsAll.toArray());
        }else{
            comboSotanoAlbums = new JComboBox();
        }
        
        comboSotanoAlbums.setPreferredSize(new Dimension(285, 20));
        comboSotanoAlbums.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
        comboSotanoAlbums.setBounds(470, 170, 520, 20);
        content.add(comboSotanoAlbums);
        
        
        comboMoneySellers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                //System.out.println("cambio");
                
                Seller seller = (Seller) comboMoneySellers.getSelectedItem();
                
                if(seller!=null){
                    String earnings = "This is 40% of their albums sold = "+sellerServices.getEarnings(seller.getId());
                
                    jLabelMoneySellers.setText(earnings);
                }
                
                
                
            }
        });

        content.add(comboAllSellers);

        albumTitleTextArea = new JTextArea("Enter the title");
        albumTitleTextArea.setBounds(20, 300, 200, 40);
        content.add(albumTitleTextArea);

        albumArtistNameTextArea = new JTextArea("Enter the Artist Name");
        albumArtistNameTextArea.setBounds(20, 350, 200, 40);
        content.add(albumArtistNameTextArea);

        albumPriceTextArea = new JTextArea("Enter the price");
        albumPriceTextArea.setBounds(20, 400, 200, 40);
        content.add(albumPriceTextArea);

        resolutionTextArea = new JTextArea("Enter Resolution");
        resolutionTextArea.setBounds(250, 40, 200, 40);
        resolutionTextArea.setVisible(false);
        content.add(resolutionTextArea);

        message = new JLabel("", JLabel.LEFT);
        message.setBounds(300, 600, 500, 40);
        content.add(message);

        JButton addSellerButton = new JButton("Add new seller");
        
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
                    message.setText("Seller new successfull");
                    newSeller.setId(sellerServices.getLastInsert());
                    comboAllSellers.addItem(newSeller);
                    comboMoneySellers.addItem(newSeller);

                } else {
                    message.setText("Error adding new Seller");
                }

            }
        });

        JButton addAlbumButton = new JButton("Add new album");
        //OKButton.addActionListener(new MyAction());
        addAlbumButton.setBounds(240, 300, 200, 40);
        content.add(addAlbumButton, BorderLayout.CENTER);
        addAlbumButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

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

                    if (albumServices.insert(newAlbum, sellerSeleted)) {
                        message.setText("Album new successfull");
                        newAlbum.setId(albumServices.getLastInsert());
                        
                        comboAllAlbums.addItem(newAlbum);
                        comboSotanoAlbums.addItem(newAlbum);
                        
                        albumTitleTextArea.setText("");
                        albumArtistNameTextArea.setText("");
                        albumPriceTextArea.setText("");

                    } else {
                        message.setText("Error adding new Album");
                    }
                }catch(Exception ex){
                    message.setText("Error adding new Album, please check the parameters");
                }
            }
        });
        content.add(addAlbumButton, BorderLayout.CENTER);

        //---------------
        JButton sellAlbumButton = new JButton("Sell album selected");
        //OKButton.addActionListener(new MyAction());
        sellAlbumButton.setBounds(770, 100, 200, 40);
        content.add(sellAlbumButton, BorderLayout.CENTER);
        sellAlbumButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {

                Album albumSeleted = (Album) comboAllAlbums.getSelectedItem();
                
                if(albumSeleted != null){
                    Sales sale = new Sales();

                    sale.setDate_sale(new Date());
                    sale.setPrice(albumSeleted.getPrice());

                    if(salesServices.insert(albumSeleted, sale)){
                        comboAllAlbums.removeItem(albumSeleted);
                        comboSotanoAlbums.removeItem(albumSeleted);
                        message.setText("Sale successful!");
                    }else{
                        message.setText("Error on sale");
                    }
                }
            }
        });
        content.add(sellAlbumButton, BorderLayout.CENTER);
        
        //--------------------------
        
        JButton passSotanoButton = new JButton("Pass to sotano this Album(Selected)");
        
        passSotanoButton.setBounds(780, 220, 200, 40);
        content.add(passSotanoButton, BorderLayout.CENTER);
        passSotanoButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {

                Album albumSeleted = (Album) comboSotanoAlbums.getSelectedItem();
                
                if(albumSeleted != null){
                
                    if(albumServices.passSotano(albumSeleted)){
                        comboAllAlbums.removeItem(albumSeleted);
                        comboSotanoAlbums.removeItem(albumSeleted);

                        message.setText("Album " + albumSeleted.getTitle()+ "was to pass to the Basement");
                    }
                
                }
            }
        });
        content.add(passSotanoButton, BorderLayout.CENTER);
        //-----------------------
        JButton searchButton = new JButton("Search...");
        //OKButton.addActionListener(new MyAction());
        searchButton.setBounds(750, 450, 200, 40);
        content.add(searchButton, BorderLayout.CENTER);
        searchButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Album> list = albumServices.getByTitle(textField.getText());
                
                model.removeAllElements();
                
                if(list!=null){
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        Album next = (Album)it.next();
                        model.addElement(next);
                        //listSearch.add(next);
                    }
                }
                
            }
        });
        content.add(passSotanoButton, BorderLayout.CENTER);
        
        
        textField = new JTextField(20);
        textField.setBounds(500, 450, 200, 20);
        content.add(textField);
        
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(500, 500, 450, 100);        
        listSearch = new JList(model);
        listSearch.setBounds(500, 500, 450, 100);
        
        scrollPane.setViewportView(listSearch);
        content.add(scrollPane);
        //content.add(listSearch);
        ImageIcon icon= null;
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
        this.setSize(screenSize+550, screenSize+200);
        this.setLocation(20,20);    //Where on the screen will this window appear?
        this.setVisible(true);
    }

}
