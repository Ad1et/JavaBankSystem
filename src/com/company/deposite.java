package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class deposite extends JFrame {
    private JButton cancelButton;
    private JButton insertButton;
    private JButton fundsAvailableButton;
    private JPanel PANEL;

    public deposite(){
        setLayout(new FlowLayout());
        setSize(50, 50);
        add(PANEL);
        ButtonHandler handler = new ButtonHandler();
        cancelButton.addActionListener(handler);
        insertButton.addActionListener(handler);
        fundsAvailableButton.addActionListener(handler);
    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e1) {
            if (e1.getSource() == cancelButton) {
                setVisible(false);
                new Menu();
                Menu gui = new Menu();
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setSize(70, 50);
                gui.setVisible(true);
                gui.pack();
            }
            if (e1.getSource() == insertButton){
                setVisible(false);
                new insert();
                insert gui = new insert();
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setSize(70,50);
                gui.setVisible(true);
                gui.pack();
            }
            if (e1.getSource() == fundsAvailableButton){
                setVisible(false);
                new Funds_available();
                Funds_available gui = new Funds_available();
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setSize(70,50);
                gui.setVisible(true);
                gui.pack();
            }
        }
    }
}
