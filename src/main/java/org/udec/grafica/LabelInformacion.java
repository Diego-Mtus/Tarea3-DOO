package org.udec.grafica;

import org.udec.logica.ProductosEnum;

import javax.swing.*;

public class LabelInformacion extends JLabel {
    private String textoInformativo = "¿Qué producto desea comprar?";
    private ProductosEnum productoSeleccionado;
    private int stockProductoSeleccionado;

    public LabelInformacion(int x, int y) {
        try {
            ImageIcon imagen = new ImageIcon(getClass().getResource("/informacion.png"));
            this.setIcon(imagen);
            this.setSize(imagen.getIconWidth(), imagen.getIconHeight());
            this.setBounds(x, y, imagen.getIconWidth(), imagen.getIconHeight());
        } catch (Exception e) {
            System.err.println("Error al cargar imagen desde la ruta: /informacion.png" + e.getMessage());
            this.setText("Imagen no disponible");
        }

        this.setText(textoInformativo);
        this.setFont(PanelPrincipal.fuentePersonalizadaBotones);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.CENTER);

        this.setVisible(true);
    }

    public void actualizarValores(ProductosEnum productoSeleccionado, int stockProductoSeleccionado){
        this.productoSeleccionado = productoSeleccionado;
        this.stockProductoSeleccionado = stockProductoSeleccionado;
        this.actualizarTextoInformativo();
    }

    public void actualizarTextoInformativo(){
        this.textoInformativo = "Producto seleccionado: " + productoSeleccionado.getNombre() + " ("+ stockProductoSeleccionado +"): $" + productoSeleccionado.getPrecio();
        this.setText(textoInformativo);
    }

}
