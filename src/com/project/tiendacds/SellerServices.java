/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tiendacds;

import com.project.tiendacds.JdbcHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementacion para recuperacion y manejo de los datos para la tabla Seller,
 * Esta clase se apoya en la clase JdbcHelper para poder accesar a los datos.
 * @author
 */
public class SellerServices {

//object to help the connection
    public JdbcHelper jdbcHelper = new JdbcHelper();

/**
 * Metodo para obtener un SELLER
 * params - id de seller
 */
    public Seller getByIndex(String id) {

        String[] array = {"name", "phone", "id"};
        try {

            ResultSet result = jdbcHelper.select("seller", array, " id = " + id);
            Seller seller = null;
            while (result.next()) {

                seller = new Seller();

                seller.setName(result.getString("name"));
                seller.setPhone(result.getString("phone"));
                seller.setId(result.getInt("id"));

            }
            return seller;
        } catch (Exception e) {

        }
        return null;
    }


/**
 * Metodo para obtener todos los SELLERs de la base de datos
 * params - ninguno
 */
    public List<Seller> getAllSellers() {
	//campos en la db recuperables
        String[] array = {"name", "phone", "id"};
        try {

            ResultSet result = jdbcHelper.select("seller", array, "");

            List<Seller> allSellers = null;

            while (result.next()) {

                if (allSellers == null) {
                    allSellers = new ArrayList<>();
                }

                Seller seller = new Seller();

                seller.setName(result.getString("name"));
                seller.setPhone(result.getString("phone"));
                seller.setId(result.getInt("id"));

                allSellers.add(seller);
            }
            return allSellers;
        } catch (Exception e) {

        }
        return null;
    }

/**
 * Method to insert a SELLER in the db
 * params - objeto seller
 */
    public boolean insert(Seller seller) {

        try {
            String sql = "INSERT INTO seller (name, phone) VALUES (?, ?)";

            JdbcHelper jdbcHelper = new JdbcHelper();

            PreparedStatement statement = jdbcHelper.getConnection().prepareStatement(sql);
            statement.setString(1, seller.getName());
            statement.setString(2, seller.getPhone());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new seller was inserted successfully!");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellerServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
/**
 * Metodo para retornar el id del ultimo seller insertado
 * params - ninguno
 */
    public int getLastInsert() {

        return jdbcHelper.getLastInsert("seller");

    }
    
/**
 * Metodo para retornar las ganancias de un seller especifico
 * params - seller_id
 */
    public double getEarnings(int seller_id) {
       
        try {
		//calculation to get the earning for me :)
		// Query SQL que usa la funcion SUM()
            String sql = "SELECT sum(s.price)*0.4 FROM disc_store_db.sales s join album a on s.album_id = a.id where seller_id = " + seller_id; 
            
            PreparedStatement statement = jdbcHelper.getConnection().prepareStatement(sql);

            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()) {
                return result.getDouble(1);//Se obtiene el calculo de la ganancia desde la base de datos
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }
    
}
