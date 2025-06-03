package org.udec.grafica;

import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {

    PanelMoneda panelMoneda;

    public PanelComprador () {
        this.setBackground(PanelPrincipal.ROJO);
        this.setSize(400, 900);
        this.setBounds(1200, 0, 400, 900);
        this.setVisible(true);
        this.panelMoneda = new PanelMoneda();
        this.add(panelMoneda);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
