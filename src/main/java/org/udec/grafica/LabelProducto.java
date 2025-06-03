package org.udec.grafica;

import org.udec.logica.Producto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LabelProducto extends JLabel {

    private Producto producto;

    public LabelProducto(Producto producto, ImageIcon imagenProducto) {

        this.producto = producto;
        this.setIcon(imagenProducto);
        this.setSize(imagenProducto.getIconWidth(), imagenProducto.getIconHeight());

        this.setToolTipText("Serie: " + producto.getSerie());

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                presionarProducto();
            }
        });
    }

    public void setXY(int x, int y) {
        this.setLocation(x, y);
    }

    // TODO: Modificar para hacer acción que veamos indicada.
    private void presionarProducto() {
        System.out.println("Serie de producto: " + producto.getSerie());
    }
}
