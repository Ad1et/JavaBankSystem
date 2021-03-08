package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.*;

public class Funds_available extends JFrame {
    private JButton returnButton;
    private JButton checkButton;
    private JPanel Panel;
    private JTextField textField1;

    public Funds_available(){
        setLayout(new FlowLayout());
        setSize(50, 50);
        add(Panel);
        ButtonHandler handler = new ButtonHandler();
        checkButton.addActionListener(handler);
        returnButton.addActionListener(handler);
    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e1) {
            if (e1.getSource() == returnButton) {
                setVisible(false);
                new deposite();
                deposite gui = new deposite();
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setSize(70, 50);
                gui.setVisible(true);
                gui.pack();
            }
            if (e1.getSource() == checkButton) {
                Connection c = null;
                Statement stmt = null;

                try {
                    int acc_num = Integer.parseInt(textField1.getText());
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/postgres",
                                    "postgres", "qwerty123");
                    c.setAutoCommit(false);
                    System.out.println("Opened database successfully");

                    stmt = c.createStatement();
                    String selectSQL = "SELECT money_amount FROM funds WHERE acc_num=?";
                    PreparedStatement stmtsql = c.prepareStatement(selectSQL);
                    stmtsql.setInt(1, acc_num);
                    ResultSet rs = stmtsql.executeQuery();
                    while ( rs.next() ) {
                        int money_amount = rs.getInt("money_amount");
                        JOptionPane.showMessageDialog(null, "Fund available: " + (money_amount) + " tenge");

                    }

                    rs.close();
                    stmt.close();
                    c.close();

                }

                catch ( Exception e ) {

                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);

                }
            }
        }
    }
}
