/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tiendacds;

import java.util.Date;

/**
 *
 * @author 
 */
public class Sales {
    
    private double price;
    private int album_id;
    private Date date_sale;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public Date getDate_sale() {
        return date_sale;
    }

    public void setDate_sale(Date date_sale) {
        this.date_sale = date_sale;
    }

    @Override
    public String toString() {
        return "Sales{" + "price=" + price + ", album_id=" + album_id + ", date_sale=" + date_sale + '}';
    }
}
