package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame {
    private JPanel panel1;
    private JLabel Lable1;
    private JButton newAccountButton;
    private JButton withdrawalButton;
    private JButton displayButton;
    private JButton depositeButton;
    private JButton deleteAccountButton;
    private JButton exitButton;
    private JPanel panel2;

    public Menu() {
        setLayout(new FlowLayout());
        setSize(50, 50);
        add(panel1);
        panel2.setSize(70, 50);
        validate();
        ButtonHandler handler = new ButtonHandler();
        newAccountButton.addActionListener(handler);
        withdrawalButton.addActionListener(handler);
        displayButton.addActionListener(handler);
        depositeButton.addActionListener(handler);
        deleteAccountButton.addActionListener(handler);
        exitButton.addActionListener(handler);
    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == newAccountButton)
            {
                setVisible(false);
                new newAccount();
                newAccount acc = new newAccount();
                acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                acc.setSize(70, 50);
                acc.setVisible(true);
                acc.pack();
            }
            if(e.getSource() == depositeButton)
            {
                setVisible(false);
                new deposite();
                deposite acc = new deposite();
                acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                acc.setSize(70, 50);
                acc.setVisible(true);
                acc.pack();
            }
            if(e.getSource() == displayButton)
            {
                setVisible(false);
                new display();
                display acc = new display();
                acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                acc.setSize(70, 50);
                acc.setVisible(true);
                acc.pack();
            }
            if(e.getSource() == withdrawalButton)
            {
                setVisible(false);
                new display();
                withdrawal acc = new withdrawal();
                acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                acc.setSize(70, 50);
                acc.setVisible(true);
                acc.pack();
            }
            if(e.getSource() == deleteAccountButton)
            {
                setVisible(false);
                new deleteAccount();
                deleteAccount acc = new deleteAccount();
                acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                acc.setSize(70, 50);
                acc.setVisible(true);
                acc.pack();
            }
            if(e.getSource() == exitButton)
            {
                System.exit(0);
            }
        }
    }
}
