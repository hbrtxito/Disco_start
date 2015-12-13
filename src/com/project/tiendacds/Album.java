/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tiendacds;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class Album, data will be save in the Album table
 * * @author Hbrtxito
 */
public class Album {
    private int id;// This is the id in the table
    
    //fields on the table
    private String artistName;
    private String title;
    private double price;
    private int inSotano;
    private int soldOn;


    //relationship with the sellers table
    private int seller_id;

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInSotano() {
        return inSotano;
    }

    public void setInSotano(int inSotano) {
        this.inSotano = inSotano;
    }

    public int getSoldOn() {
        return soldOn;
    }

    public void setSoldOn(int soldOn) {
        this.soldOn = soldOn;
    }
    
//To string Method
    @Override
    public String toString() {
        return "ID: " + id +  "    Artist: " + artistName + "    Album: " + title + "    Price: " + price + "    Seller ID: " + seller_id ;
    }

}
