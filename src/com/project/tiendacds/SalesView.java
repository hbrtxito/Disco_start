/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tiendacds;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class SalesView extends JFrame{
    static JTextArea problemTextArea;
    static JTextArea clientTextArea;
    static JTextArea resolutionTextArea;
    static JComboBox comboPriority;
    static JComboBox comboAllTickets;
    static JLabel message;
    
    static JButton resolutionButton;
    
    static int screenSize = 500;

    public SalesView() {
        
         JPanel content = new JPanel();
        content.setLayout(null);
        //content.add(gamePanel, BorderLayout.CENTER);

     

        problemTextArea = new JTextArea("Enter problem");
        problemTextArea.setBounds(20, 40, 200, 40);
        content.add(problemTextArea);//Who reported this issue?

        clientTextArea = new JTextArea("Who reported this issue?");
        clientTextArea.setBounds(20, 90, 200, 40);
        content.add(clientTextArea);//
        
        comboPriority = new JComboBox();//To send the list
        comboPriority.setPreferredSize(new Dimension(285, 20));
        comboPriority.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
        comboPriority.setBounds(20, 150, 200, 20);
        content.add(comboPriority);
        
        
        comboAllTickets = new JComboBox();//to send the list
        comboAllTickets.setPreferredSize(new Dimension(285, 20));
        comboAllTickets.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
        comboAllTickets.setBounds(20, 450, 400, 20);
        
        /*comboAllTickets.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                
               
            }
        });*/
        
        content.add(comboAllTickets);
        
        resolutionTextArea = new JTextArea("Enter Resolution");
        resolutionTextArea.setBounds(250, 40, 200, 40);
        resolutionTextArea.setVisible(false);
        content.add(resolutionTextArea);
        
        message = new JLabel("", JLabel.LEFT);
        message.setBounds(100, 0, 300, 40);
        content.add(message);
        
        JButton OKButton = new JButton("Enter Ticket");
        //OKButton.addActionListener(new MyAction());
        OKButton.setBounds(20, 200, 200, 40);
        content.add(OKButton, BorderLayout.CENTER);
        OKButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                
            }
        });
        
        
        resolutionButton = new JButton("Update resolution");
        //OKButton.addActionListener(new MyAction());
        resolutionButton.setBounds(250, 200, 200, 40);
        resolutionButton.setVisible(false);
        content.add(OKButton, BorderLayout.CENTER);
        OKButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {
                                
            }
        });
        content.add(resolutionButton);
        
        

        //window.setUndecorated(true);   //Hides the title bar.

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //Quit the program when we close this window
        this.setContentPane(content);
        this.setSize(screenSize, screenSize);
        this.setLocation(0, 100);    //Where on the screen will this window appear?
        this.setVisible(true);
        
    }
    
    
}
