package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.*;

public class deleteAccount extends JFrame {
    private JButton deleteButton;
    private JButton cancelButton;
    private JTextField textField1;
    private JPanel Panel;

    public deleteAccount(){
        setLayout(new FlowLayout());
        setSize(100, 100);
        add(Panel);
        ButtonHandler handler = new ButtonHandler();
        deleteButton.addActionListener(handler);
        cancelButton.addActionListener(handler);
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
            if (e1.getSource() == deleteButton) {
                int acc_number = Integer.parseInt(textField1.getText());
                try {
                    Class.forName("org.postgresql.Driver");
                    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "qwerty123");
                    con.setAutoCommit(false);
                    String sql = "DELETE from account_reg where acc_num = ?;";
                    String MySql = "DELETE from funds where acc_num = ?;";
                    PreparedStatement stmtsl = con.prepareStatement(MySql);
                    stmtsl.setInt(1, acc_number);
                    int i=stmtsl.executeUpdate();
                    System.out.println(i+" records deleted");
                    PreparedStatement stmtsql = con.prepareStatement(sql);
                    stmtsql.setInt(1, acc_number);
                    int j=stmtsql.executeUpdate();
                    System.out.println(i+" records deleted");
                    con.commit();

                    stmtsl.close();
                    stmtsql.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "Account deleted" + (" ") + " ");

                }

                catch(Exception e){

                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
                }
            }
        }
    }
}
