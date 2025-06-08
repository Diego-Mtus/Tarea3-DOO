package org.udec.grafica;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

/**
 * Clase LabelDepositoProducto que representa un depósito gráfico en el que se almacenan
 * objetos de tipo LabelProducto. Hereda de la clase JLabel y permite manejar animaciones
 * y visibilidades de los productos añadidos al depósito.
 */
public class LabelDepositoProducto extends JLabel {
    private List<LabelProducto> productos;
    public static final int MAX_PRODUCTOS = 15;

    public LabelDepositoProducto (int x, int y) {

        try {
            ImageIcon imagen = new ImageIcon(getClass().getResource("/deposito.png"));
            this.setIcon(imagen);
            this.setSize(imagen.getIconWidth(), imagen.getIconHeight());
            this.setBounds(x, y, imagen.getIconWidth(), imagen.getIconHeight());
        } catch (Exception e) {
            System.err.println("Error al cargar imagen desde la ruta: /deposito.png" + e.getMessage());
            this.setText("Imagen no disponible");
        }

        this.productos = new ArrayList<>();

        this.setVisible(true);

    }

    private void animacionProductos(LabelProducto p) {

        Timer timerAnim = new Timer();
        int pixY = p.getHeight();
        int posX = (this.getWidth() - p.getWidth()) / 2;

        int segundos = 1;
        int periodo = 100;
        int totalIteraciones = (segundos * 1000) / periodo;
        int posFinalY = p.getY() + pixY + 5;

        TimerTask accion = new TimerTask() {
            private int i = 0;
            @Override
            public void run() {
                i++;
                int nuevaPosY = p.getY() + (posFinalY - p.getY()) * i / totalIteraciones;
                p.setXY(posX, nuevaPosY);
                if (i >= totalIteraciones) {
                    i = 0;
                    timerAnim.cancel();
                }
            }
        };
        timerAnim.scheduleAtFixedRate(accion, 250, periodo);
    }

    /**
     * Añade un producto al depósito, colocándolo en la posición correspondiente dentro del contenedor.
     * Si el producto es el primero, se posiciona en la base del depósito.
     * Para los productos siguientes, se posicionan sobre el último producto añadido.
     * Se limita la visibilidad de los productos a un máximo definido por MAX_PRODUCTOS.
     *
     * @param p LabelProducto que se añadirá al depósito.
     */
    public void addProducto(LabelProducto p) {
        this.productos.add(p);
        int posX = (this.getWidth() - p.getWidth()) / 2;
        // Si es el primer producto añadido al deposito.
        if (this.productos.size() == 1) {
            p.setXY(posX, this.getHeight() - p.getHeight() - 5);
            p.setVisible(true);
        } else{
            // Si ya habian productos antes
            p.setXY(posX, productos.get(productos.size() - 2).getY() - p.getHeight() - 5);
            p.setVisible(productos.size() <= MAX_PRODUCTOS); // Limita la cantidad de productos visibles a 14.
        }

        this.add(p);
    }

    /**
     * Elimina un producto del depósito, retirando el producto que se encuentra
     * en la primera posición de la lista de productos (parte inferior del depósito).
     *
     * <p>
     * Si la lista de productos no está vacía:
     * - Elimina el primer producto de la lista y del contenedor.
     * - Si hay un producto oculto debido a MAX_PRODUCTO, se hace visible.
     * - Aplica una animación para ajustarlos a sus nuevas posiciones.
     * </p>
     */
    public void removeProducto() {
        if (!productos.isEmpty()) {
            this.productos.removeFirst();
            this.remove(0);

            // Activar visibilidad de producto que está arriba
            if((this.productos.size() >= MAX_PRODUCTOS) && !(this.productos.get(MAX_PRODUCTOS - 1).isVisible())){
                this.productos.get(MAX_PRODUCTOS - 1).setVisible(true);
            }

            for (LabelProducto p : productos) {
                animacionProductos(p);
            }
        }
    }

}
