package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;


public class newAccount extends JFrame {
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel panel;
    Random rand = new Random();

    int rand_int1 = rand.nextInt(1000);




    public newAccount(){
        setLayout(new FlowLayout());
        setSize(50, 50);
        add(panel);
        ButtonHandler handler = new ButtonHandler();
        cancelButton.addActionListener(handler);
        saveButton.addActionListener(handler);

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
            if (e1.getSource() == saveButton) {
                String name = textField1.getText();
                String address = textField3.getText();
                int age = Integer.parseInt(textField4.getText());
                try {
                    int zero=0;
                    Class.forName("org.postgresql.Driver");
                    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "qwerty123");
                    String sql = "insert into \"account_reg\" values(?,?,?,?);";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setInt(1,rand_int1);
                    stmt.setString(2, name);
                    stmt.setString(3, address);
                    stmt.setInt(4, age);

                    String sql1 = "insert into \"funds\" values(?,?);";
                    PreparedStatement stm = con.prepareStatement(sql1);
                    stm.setInt(1,rand_int1);
                    stm.setInt(2, zero);

                    int i=stmt.executeUpdate();
                    int j=stm.executeUpdate();
                    System.out.println(i+" records inserted");
                    JOptionPane.showMessageDialog(null, "Account created" + (" ") + " ");

                    stmt.close();
                    con.commit();
                    con.close();

                }

                catch(Exception e){

                    System.out.println(e);

                }
            }
        }
    }
}
