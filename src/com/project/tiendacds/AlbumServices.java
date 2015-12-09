/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tiendacds;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is to implement and management data for the Album table
 * This class is use JDBHelper to access the data
 * @author
 */
public class AlbumServices {

    // Helper object to connect the database
    public JdbcHelper jdbcHelper = new JdbcHelper();
	//Fields in the database that we can retrieve
    String[] columns = {"artist_name", "title", "price", "id", "seller_id","en_sotano" ,"sold_on"};

    public Album getByIndex(String id) {

        try {
            //Execute the query and get the result set, which contains
            // all the results returned from the database.
            ResultSet result = jdbcHelper.select("album", columns, " id = " + id);
            Album album = null;
            // We loop through the rows that were returned, and we can access the information
            // depending on the type of each column.
            while (result.next()) {

                album = new Album();

                album.setArtistName(result.getString("artist_name"));
                album.setTitle(result.getString("title"));
                album.setPrice(result.getInt("price"));
                album.setId(result.getInt("id"));
                album.setSeller_id(result.getInt("seller_id"));
                album.setInSotano(result.getInt("en_sotano"));
                album.setSoldOn(result.getInt("sold_on"));
                
            }
            return album;
        } catch (Exception e) {

        }
        return null;
    }
/**
 * Metodo para retornar un listado de albunes por seller dado
 * params - seller_id
 */
    public List<Album> getBySellerId(String seller_id) {

        try {
            // Same methodology as before
            ResultSet result = jdbcHelper.select("album", columns, " seller_id = " + seller_id);

            List<Album> allAlbums = null;

            while (result.next()) {

                if (allAlbums == null) {
                    allAlbums = new ArrayList<>();
                }

                Album album = new Album();
                // retrieving data
                album.setArtistName(result.getString("artist_name"));
                album.setTitle(result.getString("title"));
                album.setPrice(result.getInt("price"));
                album.setId(result.getInt("id"));
                album.setSeller_id(result.getInt("seller_id"));                
                album.setInSotano(result.getInt("en_sotano"));
                album.setSoldOn(result.getInt("sold_on"));

                allAlbums.add(album);//add the album to the list
            }
            return allAlbums;// return the list of album
        } catch (Exception e) {

        }
        return null;
    }
/**
 * GETALL ALBUMS - look for all the albums with a given condition
 *
 */
    public List<Album> getAllAlbums(String condition) {

        try {

            ResultSet result = jdbcHelper.select("album", columns, condition);//helper need it

            List<Album> allAlbums = null;

            while (result.next()) {// We check that we have albums

                if (allAlbums == null) {
                    allAlbums = new ArrayList<>();
                }

                Album album = new Album();
                album.setArtistName(result.getString("artist_name"));
                album.setTitle(result.getString("title"));
                album.setPrice(result.getInt("price"));
                album.setId(result.getInt("id"));
                album.setSeller_id(result.getInt("seller_id"));
                album.setInSotano(result.getInt("en_sotano"));
                album.setSoldOn(result.getInt("sold_on"));

                allAlbums.add(album);//add it to the returned list
            }
            return allAlbums;//return all the list
        } catch (Exception e) {

        }
        return null;
    }

/**
 * MEthod to insert an Album in the database
 *
 * params - object album and seller object
 */
    public boolean insert(Album album, Seller seller) {

        try {
		//Sql insert query
            String sql = "INSERT INTO album (artist_name, title , price,seller_id) VALUES (?,?,?,?)";

            PreparedStatement statement = jdbcHelper.getConnection().prepareStatement(sql);
		    // Stabilising the entry parameters of the query sql
            statement.setString(1, album.getArtistName());
            statement.setString(2, album.getTitle());
            statement.setDouble(3, album.getPrice());
            statement.setInt(4, seller.getId());

		    //Executing query
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {//if enter here is because the execution was correct
                System.out.println("A new Album was inserted successfully!");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return false;// Insertion incorrect
    }
    
/**
 * To put an album to the sotano or basemant
 * params - object album
 */
    public boolean passSotano(Album album) {

        try {
            //Sentencia SQL UPDATE
            String sql = "UPDATE album SET en_sotano=1 WHERE id = " + album.getId();

            PreparedStatement statement = jdbcHelper.getConnection().prepareStatement(sql);
            
            int rowsInserted = statement.executeUpdate();// Execution of the update
            if (rowsInserted > 0) {
                System.out.println("Update Album - pass sotano!");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return false;
    }
    
    public int getLastInsert() {

        return jdbcHelper.getLastInsert("album");

    }
    /**
 * To return a album list with a given condition (String)
 * params - str criterio de busqueda
 */
    public List<Album> getByTitle(String str) {

        try {
		    //this will execute a condition in the database
            ResultSet result = jdbcHelper.select("album", columns, " title like '%" + str + "%' OR artist_name like '%" + str +"%'");
            List<Album> allAlbums = null;

            while (result.next()) {// if in the database there are some album that contains the search , enters here

                if (allAlbums == null) {
                    allAlbums = new ArrayList<>();
                }

                Album album = new Album();

		        //Get the result of the data base
                album.setArtistName(result.getString("artist_name"));
                album.setTitle(result.getString("title"));
                album.setPrice(result.getInt("price"));
                album.setId(result.getInt("id"));
                album.setSeller_id(result.getInt("seller_id"));
                album.setInSotano(result.getInt("en_sotano"));
                album.setSoldOn(result.getInt("sold_on"));
                
                allAlbums.add(album);
            }
		// return albums
            return allAlbums;
        } catch (Exception e) {

        }
        return null;
    }
}
