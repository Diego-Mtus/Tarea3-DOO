package org.udec.grafica;

import org.udec.logica.Producto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LabelProducto extends JLabel {

    private Producto producto;

    public LabelProducto(Producto producto, String nombreImagen) {
        this.producto = producto;

        try {
            ImageIcon imagen = new ImageIcon(getClass().getResource("/" + nombreImagen));
            this.setIcon(imagen);
            this.setSize(imagen.getIconWidth(), imagen.getIconHeight());
        } catch (Exception e) {
            System.err.println("Error al cargar imagen desde la ruta: /" + nombreImagen + ". " + e.getMessage());
            this.setText("Imagen no disponible");
        }

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
