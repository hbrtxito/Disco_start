
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

	//Object to Connect the data base
    public JdbcHelper jdbcHelper = new JdbcHelper();
	//Field that We can get from the table
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
 * Method to return a list of albums  by a given Seller
 * params - seller_id
 */
   /* public List<Album> getBySellerId(String seller_id) {

        try {
            // usamos el helper para hacer el select
            ResultSet result = jdbcHelper.select("album", columns, " seller_id = " + seller_id);

            List<Album> allAlbums = null;

            while (result.next()) {

                if (allAlbums == null) {
                    allAlbums = new ArrayList<>();
                }

                Album album = new Album();
                // to return the data
                album.setArtistName(result.getString("artist_name"));
                album.setTitle(result.getString("title"));
                album.setPrice(result.getInt("price"));
                album.setId(result.getInt("id"));
                album.setSeller_id(result.getInt("seller_id"));                
                album.setInSotano(result.getInt("en_sotano"));
                album.setSoldOn(result.getInt("sold_on"));

                allAlbums.add(album);//add the album to the list
            }
            return allAlbums;// return the list of the albums
        } catch (Exception e) {

        }
        return null;
    }*/
/**
 * Methd to get a album list given a condition
 * params - condition
 */
    public List<Album> getAllAlbums(String condition) {

        try {

            ResultSet result = jdbcHelper.select("album", columns, condition);//Using the helper

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
            return allAlbums;// returning the albums list
        } catch (Exception e) {

        }
        return null;
    }

/**
 * Method to insert an album   in the database
 * params - object album y object seller
 */
    public boolean insert(Album album, Seller seller) {

        
        if(album != null && seller != null){
            try {
                    //Query SQL
                String sql = "INSERT INTO album (artist_name, title , price,seller_id) VALUES (?,?,?,?)";

                PreparedStatement statement = jdbcHelper.getConnection().prepareStatement(sql);
                    // Setting the params to entry of the sql query
                statement.setString(1, album.getArtistName());
                statement.setString(2, album.getTitle());
                statement.setDouble(3, album.getPrice());
                statement.setInt(4, seller.getId());

                    //Executing the query  SQL
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {//if it came here is because the insertion was correct
                    System.out.println("A new Album was inserted successfully!");
                    return true;
                }
            } catch (SQLException ex) {
                System.out.println("error: " + ex.getMessage());
            }
        }
        return false;// no se inserto
    }
    
/**
 * Method to send to the Bargain or Sotano an specific album
 * params - object album
 */
    public boolean passSotano(Album album) {

        try {
//Sentencia SQL UPDATE
            String sql = "UPDATE album SET en_sotano=1 WHERE id = " + album.getId();

            PreparedStatement statement = jdbcHelper.getConnection().prepareStatement(sql);
            
            int rowsInserted = statement.executeUpdate();// Executing the update
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
 * Method to return the album list with a given Criteria or condition
 * params - str Given Criteria
 */
    public List<Album> getByTitle(String str) {

        try {
		//Sending a condition to the function to execute in the database
            ResultSet result = jdbcHelper.select("album", columns, " title like '%" + str + "%' OR artist_name like '%" + str +"%'");
            List<Album> allAlbums = null;

            while (result.next()) {// If there are register in the databasewith the condition given  it will enter the loop

                if (allAlbums == null) {
                    allAlbums = new ArrayList<>();
                }

                Album album = new Album();

		        //To get the data from the database
                album.setArtistName(result.getString("artist_name"));
                album.setTitle(result.getString("title"));
                album.setPrice(result.getInt("price"));
                album.setId(result.getInt("id"));
                album.setSeller_id(result.getInt("seller_id"));
                album.setInSotano(result.getInt("en_sotano"));
                album.setSoldOn(result.getInt("sold_on"));
                
                allAlbums.add(album);
            }
		// to return the album list that contain within in the condition
            return allAlbums;
        } catch (Exception e) {

        }
        return null;
    }
    
    /**
 * Method to send the the Bargain store after 30 days
 * params - ninguno
 */
    public boolean passSotanoAutomatically() {

        try {
            //Query Sentence UPDATE to send album that are more than 30 dias registered
            String sql = "update disc_store_db.album set en_sotano = 1 where TIMESTAMPDIFF(DAY, created_on, CURDATE()) > 29 AND sold_on = 0" ;

            PreparedStatement statement = jdbcHelper.getConnection().prepareStatement(sql);
            
            int rowsInserted = statement.executeUpdate();// Executing the update
            if (rowsInserted > 0) {
                System.out.println("passSotanoAutomatically method was executed! " + rowsInserted + " albums were moved to sotano."  );
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return false;
    }
}
