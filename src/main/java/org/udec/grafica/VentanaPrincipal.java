package org.udec.grafica;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal () {
        this.setTitle("Tarea 3 - DOO");
        this.setSize(1600, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.setLayout(null);
        this.add(new PanelPrincipal()); // Se agrega el PanelPrincipal

        this.setVisible(true);

    }
}
