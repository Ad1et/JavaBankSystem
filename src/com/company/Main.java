package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args)
    {
        Menu gui = new Menu();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(70, 50);
        gui.setVisible(true);
        gui.pack();

    }
}

