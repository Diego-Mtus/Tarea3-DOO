package org.udec.grafica;

import javax.swing.*;
import java.awt.*;


public class PanelPrincipal extends JPanel {
    private PanelComprador com;
    private PanelExpendedor exp;

    // VARIABLES GLOBALES DE COLOR Y FUENTE
    public final static Color OSCURO = new Color(100, 40, 40);
    public final static Color GRIS = new Color(197,203,211);
    public final static Color CELESTE = new Color(140, 188, 185);
    public final static Color AMARILLO = new Color(221, 164, 72);
    public final static Color ROJO = new Color(187, 52, 47);
    public final static Font FUENTE_PERSONALIZADA = new Font("Dialog", Font.BOLD, 24);

    public PanelPrincipal () {

        this.setSize(1400, 800);
        this.setLayout(null); // Usamos posiciones absolutas

        exp = new PanelExpendedor();
        com = new PanelComprador(exp);

        // Agregamos los paneles
        this.add(exp);
        this.add(com);

        this.setVisible(true);

        // Se muestra mensaje de bienvenida después de que haya cargado panel principal.
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, "¡Bienvenido a nuestro expendedor de productos!\n" +
                            "Puedes ver tus monedas disponibles dejando el mouse encima de los botones de moneda.\n" +
                            "También puedes ver el número de serie de los productos de la misma forma.",
                    "Bienvenida", JOptionPane.PLAIN_MESSAGE);
        });

    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        com.paintComponent(g);
        exp.paintComponent(g);
    }
}
