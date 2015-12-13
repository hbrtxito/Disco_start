/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tiendacds;

import java.io.Serializable;

/**
 * Clase de dominio, refleja los datos que se guardan en la TABLA SELLER
 * @author 
 */
public class Seller implements Serializable{
    
    private int id;// id en la tabla

    //fields in the table
    private String name;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

	// Override To String Method
    @Override
    public String toString() {
        return "Seller ID: " + id + "  Name: " + name + "   Phone: " + phone ;
    }
    
}
