/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tiendacds;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author 
 */
public class SalesServices {
    public JdbcHelper jdbcHelper = new JdbcHelper();

    String[] columns = {"price", "date_sale", "album_id", "id"};
    
    public boolean insert(Album album, Sales sale) {

        try {
            // http://www.w3schools.com/php/php_mysql_prepared_statements.asp

            String sql = "INSERT INTO sales (price, date_sale , album_id) VALUES (?,?,?)";
            //To Use the statement repeatedly
            PreparedStatement statement = jdbcHelper.getConnection().prepareStatement(sql);
            statement.setDouble(1, sale.getPrice());
            statement.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()) );
            statement.setInt(3, album.getId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                
                System.out.println("A new sale was inserted successfully!");

                //Updating the database
                sql = "UPDATE album SET sold_on = 1 where id = " + album.getId();

                statement = jdbcHelper.getConnection().prepareStatement(sql);
                
                rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Update album sold_on!");
                }
                
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return false;
    }
}
