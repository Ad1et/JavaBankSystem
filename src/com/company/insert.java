package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class insert extends JFrame {
    private JButton cancelButton;
    private JButton insertButton;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel panel;

    public insert(){
        setLayout(new FlowLayout());
        setSize(50, 50);
        add(panel);
        ButtonHandler handler = new ButtonHandler();
        cancelButton.addActionListener(handler);
        insertButton.addActionListener(handler);
    }
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e1) {
            if (e1.getSource() == cancelButton) {
                setVisible(false);
                new deposite();
                deposite gui = new deposite();
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setSize(70, 50);
                gui.setVisible(true);
                gui.pack();
            }
            if (e1.getSource() == insertButton) {
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
                            funds1 = money_amount + funds;

                    }
                    String sql = "update \"funds\" set money_amount=? where acc_num=?;";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setInt(2,account_number);
                    stmt.setInt(1, funds1);

                    int i=stmt.executeUpdate();
                    System.out.println(i+" records inserted");
                    JOptionPane.showMessageDialog(null, "Data inserted" + ("") + " ");

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
