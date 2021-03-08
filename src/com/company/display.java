package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class display extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JButton searchButton;
    private JButton cancelButton;
    private JPanel Panel;

    public display(){
        setLayout(new FlowLayout());
        setSize(50, 50);
        add(Panel);
        ButtonHandler handler = new ButtonHandler();
        cancelButton.addActionListener(handler);
        searchButton.addActionListener(handler);
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
            if (e1.getSource() == searchButton) {
                String name = textField1.getText();
                String address = textField2.getText();

                Connection c = null;
                Statement stmt = null;

                try {

                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/postgres",
                                    "postgres", "qwerty123");
                    c.setAutoCommit(false);
                    System.out.println("Opened database successfully");

                    stmt = c.createStatement();
                    String selectSQL = "SELECT acc_num FROM account_reg WHERE user_name=? AND address=?";
                    PreparedStatement stmtsql = c.prepareStatement(selectSQL);
                    stmtsql.setString(1, name);
                    stmtsql.setString(2, address);
                    ResultSet rs = stmtsql.executeQuery();
                    while ( rs.next() ) {
                        String acc_num = rs.getString("acc_num");
                        JOptionPane.showMessageDialog(null, "Account number: " + (acc_num) + "");
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
