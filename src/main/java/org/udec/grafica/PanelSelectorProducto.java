package org.udec.grafica;

import javax.swing.*;
import java.awt.*;

public class PanelSelectorProducto extends JPanel {

    private ButtonGroup grupoSeleccion;

    public PanelSelectorProducto(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);

        grupoSeleccion = new ButtonGroup();

        JButton cocacola = new JButton("Cocacola");
        JButton sprite = new JButton("Sprite");
        JButton fanta = new JButton("Fanta");
        JButton snickers = new JButton("Snickers");
        JButton super8 = new JButton("Super 8");

        grupoSeleccion.add(cocacola);
        grupoSeleccion.add(sprite);
        grupoSeleccion.add(fanta);
        grupoSeleccion.add(snickers);
        grupoSeleccion.add(super8);

        this.add(cocacola);
        this.add(sprite);
        this.add(fanta);
        this.add(snickers);
        this.add(super8);


        this.setVisible(true);
    }
}
