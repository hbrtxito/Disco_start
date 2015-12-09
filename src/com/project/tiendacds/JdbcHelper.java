/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tiendacds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class JdbcHelper {
    // connection data to the database
    final private String dbURL = "jdbc:mysql://localhost:3306/disc_store_db";
    final private String username = "root";
    final private String password = "itecitec";
    private Connection conn;// connection with th database

    public JdbcHelper() {
        getConnection();
    }
    //to get the connection with the data base
    public Connection getConnection(){
        try{
            // MySQL driver to load and initialize
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection(dbURL, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    // Helper to do a SELECT in a generic way
    public ResultSet select(String nameTable, String[] columnsList, String condition) {

        try {
            String sql = "SELECT ";// This will start the query
            
            for (int i = 0; i < columnsList.length; i++) {
                String column = columnsList[i];
                sql = sql + column;
                if (i + 1 < columnsList.length) {
                    sql = sql + ",";// to construct the query
                }

            }

            sql = sql + " FROM " + nameTable + " ";

            if (!condition.isEmpty()) {
                sql = sql + " WHERE " + condition;// se construye el SQL
            }

            System.out.println("SQL: " + sql);// se construye el SQL
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            return result;// This is to return the query to make it generic
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    // Return the las insert of a specific table
    public int getLastInsert(String nameTable) {

        int maxID = -1;
        
        try {
            String sql = "SELECT MAX(id) FROM " + nameTable;
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                maxID = result.getInt(1);
            }
            return maxID;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return maxID;
    }

    public String getDbURL() {
        return dbURL;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
