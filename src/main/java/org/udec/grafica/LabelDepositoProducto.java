package org.udec.grafica;

import org.udec.logica.Expendedor;
import org.udec.logica.ProductosEnum;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

public class LabelDepositoProducto extends JLabel {
    private List<LabelProducto> productos;
    private final int MAX_PRODUCTOS = 14;

    public LabelDepositoProducto (int x, int y) {

        // cambiar después
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


        int segundos = 5; // decisegundos creo ?? 0.1 segundo, lo dejaré como segundo para no confundir
        int periodo = 100;
        int totalIteraciones = (segundos * 100) / periodo;
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

    public void addProducto(LabelProducto p) {
        this.productos.add(p);
        int posX = (this.getWidth() - p.getWidth()) / 2;
        // Si es el primer producto añadido al deposito.
        if (this.productos.size() == 1) {
            // FIXME: VER POSICIONES
            p.setXY(posX, this.getHeight() - p.getHeight() - 5);
            p.setVisible(true);
        } else{
            // Si ya habian productos antes
            p.setXY(posX, productos.get(productos.size() - 2).getY() - p.getHeight() - 5);
            p.setVisible(productos.size() <= MAX_PRODUCTOS); // Limita la cantidad de productos visibles a 14.
        }

        this.add(p);
    }

    public void removeProducto() {
        if (!productos.isEmpty()) {
            this.productos.removeFirst();
            this.remove(0);
            for (LabelProducto p : productos) {
                animacionProductos(p);
            }
        }
    }

}
