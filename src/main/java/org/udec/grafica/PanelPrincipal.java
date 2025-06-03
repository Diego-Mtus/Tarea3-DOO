package org.udec.grafica;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    private PanelComprador com;
    private PanelExpendedor exp;

    // VARIABLES GLOBALES DE COLOR Y FUENTE
    public final static Color OSCURO = new Color(114, 83, 82);
    public final static Color GRIS = new Color(197,203,211);
    public final static Color CELESTE = new Color(140, 188, 185);
    public final static Color AMARILLO = new Color(221, 164, 72);
    public final static Color ROJO = new Color(187, 52, 47);
    public final static Font fuentePersonalizadaBotones = new Font("Serif", Font.BOLD, 24);

    public PanelPrincipal () {

        this.setSize(1600, 900);
        this.setLayout(null); // Usamos posiciones absolutas

        exp = new PanelExpendedor();
        com = new PanelComprador(exp);

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
