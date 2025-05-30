package org.udec.grafica;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    private PanelComprador com;
    private PanelExpendedor exp;
    public PanelPrincipal () {

        this.setSize(1600, 900);
        this.setLayout(null); // Usamos posiciones absolutas

        exp = new PanelExpendedor();
        com = new PanelComprador();

        // Agregamos los paneles
        this.add(exp);
        this.add(com);

        this.setVisible(true);
    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        com.paintComponent(g);
        exp.paintComponent(g);
    }
}
