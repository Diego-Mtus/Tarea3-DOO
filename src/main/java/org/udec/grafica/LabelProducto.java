package org.udec.grafica;

import org.udec.logica.Producto;
import javax.swing.*;

/**
 * Clase LabelProducto que extiende JLabel y se utiliza para representar un producto con su imagen,
 * así como un tooltip con la serie del producto.
 */
public class LabelProducto extends JLabel {

    public LabelProducto(Producto producto, ImageIcon imagenProducto) {

        this.setIcon(imagenProducto);
        this.setSize(imagenProducto.getIconWidth(), imagenProducto.getIconHeight());

        this.setToolTipText("Serie: " + producto.getSerie());
    }

    /**
     * Establece las coordenadas (x, y) de ubicación del producto.
     *
     * @param x Coordenada horizontal en la que se establecerá la posición del producto.
     * @param y Coordenada vertical en la que se establecerá la posición del producto.
     */
    public void setXY(int x, int y) {
        this.setLocation(x, y);
    }

}
