package com.ovidonius;

import com.ovidonius.graph.CityGraph;
import com.ovidonius.ui.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CityGraph.init();
        SwingUtilities.invokeLater(() -> new MainWindow("src/main/resources").setVisible(true));
    }
}
