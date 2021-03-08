package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.*;

public class withdrawal extends JFrame {
    private JPanel panel;
    private JButton withdrawalButton;
    private JButton cancelButton;
    private JTextField textField2;
    private JTextField textField1;

    public withdrawal(){
        setLayout(new FlowLayout());
        setSize(100, 100);
        add(panel);
        ButtonHandler handler = new ButtonHandler();
        withdrawalButton.addActionListener(handler);
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
            if (e1.getSource() == withdrawalButton) {
                int account_number = Integer.parseInt(textField1.getText());
                int funds = Integer.parseInt(textField2.getText());
                Statement stm = null;
                try {

                    Class.forName("org.postgresql.Driver");
                    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "qwerty123");
                    String selectSQL = "SELECT money_amount FROM funds WHERE acc_num=?";
                    PreparedStatement stmtsql = con.prepareStatement(selectSQL);
                    stmtsql.setInt(1, account_number);
                    ResultSet rs = stmtsql.executeQuery();
                    int funds1 = 0;

                    while ( rs.next() ) {
                        int money_amount = rs.getInt("money_amount");
                        funds1 = money_amount - funds;

                    }
                    String sql = "update \"funds\" set money_amount=? where acc_num=?;";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setInt(2,account_number);
                    stmt.setInt(1, funds1);

                    int i=stmt.executeUpdate();
                    System.out.println(i+" records inserted");
                    JOptionPane.showMessageDialog(null, "money withdrawal" + (" ") + " ");

                    stmt.close();
                    con.commit();
                    con.close();

                }

                catch(Exception e){

                    System.out.println(e);

                }
                return;
            }
        }
    }

}
