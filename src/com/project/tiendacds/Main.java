
package com.project.tiendacds;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static SellersView sellersView;
    
    public static void main(String[] args) {

        sellersView = new SellersView();// To show the Gui Sellers
        System.out.println(java.util.Calendar.getInstance().getTime());
    }
    
}
