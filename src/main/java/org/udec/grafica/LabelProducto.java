package org.udec.grafica;

import org.udec.logica.Producto;
import javax.swing.*;

public class LabelProducto extends JLabel {

    public LabelProducto(Producto producto, ImageIcon imagenProducto) {

        this.setIcon(imagenProducto);
        this.setSize(imagenProducto.getIconWidth(), imagenProducto.getIconHeight());

        this.setToolTipText("Serie: " + producto.getSerie());
    }

    public void setXY(int x, int y) {
        this.setLocation(x, y);
    }

}
