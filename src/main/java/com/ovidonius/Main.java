package com.ovidonius;

import com.ovidonius.ui.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow("src/main/resources/tickets.txt").setVisible(true));
    }
}
