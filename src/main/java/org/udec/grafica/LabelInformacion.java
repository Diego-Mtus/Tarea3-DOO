package org.udec.grafica;

import org.udec.logica.ProductosEnum;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Clase LabelInformacion que extiende JLabel para mostrar información
 * sobre los productos disponibles y seleccionados en una interfaz gráfica.
 */
public class LabelInformacion extends JLabel {

    private String textoInformativo = "¿Qué producto desea comprar?";
    private ProductosEnum productoSeleccionado;
    private int stockProductoSeleccionado;
    private Font fuenteInformacion;

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

        // Cargar fuente
        try {
            // Ruta hacia el archivo .otf (en la carpeta resources)
            InputStream fuenteStream = getClass().getResourceAsStream("/Skip.otf");

            if(fuenteStream == null) {
                throw new IOException("No se pudo cargar la fuente");
            }

            Font fuenteBase = Font.createFont(Font.TRUETYPE_FONT, fuenteStream);
            fuenteInformacion = fuenteBase.deriveFont(20f); // Tamaño 30

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            fuenteInformacion = PanelPrincipal.FUENTE_PERSONALIZADA;
        }

        this.setText(textoInformativo);
        this.setFont(fuenteInformacion);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.BOTTOM);
        this.setIconTextGap(-40);



        this.setVisible(true);
    }

    /**
     * Actualiza los valores del producto seleccionado y su stock disponible, y luego
     * actualiza el texto informativo que se muestra en la interfaz.
     *
     * @param productoSeleccionado El producto seleccionado de tipo ProductosEnum.
     * @param stockProductoSeleccionado La cantidad de stock disponible para el producto seleccionado.
     */
    public void actualizarValores(ProductosEnum productoSeleccionado, int stockProductoSeleccionado){
        this.productoSeleccionado = productoSeleccionado;
        this.stockProductoSeleccionado = stockProductoSeleccionado;
        this.actualizarTextoInformativo();
    }

    /**
     * Actualiza el texto informativo mostrado en la etiqueta con los detalles del producto seleccionado,
     * incluyendo su nombre, stock y precio.
     */
    public void actualizarTextoInformativo(){
        this.textoInformativo = "Producto seleccionado: " + productoSeleccionado.getNombre() + " ("+ stockProductoSeleccionado +"): $" + productoSeleccionado.getPrecio();
        this.setText(textoInformativo);
    }

    /**
     * Muestra un mensaje en la etiqueta indicando que los depósitos vacíos
     * han sido repuestos.
     */
    public void mostrarRenovacion(){
        this.setText("¡Se han repuesto depósitos vacíos!");
    }

}
