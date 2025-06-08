package org.udec.grafica;

import javax.swing.*;

/**
 * La clase VentanaPrincipal es responsable de crear la ventana principal de la aplicación.
 * Extiende la clase JFrame y configura las propiedades básicas de la ventana,
 * como el título, tamaño, posición.
 */
public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal () {
        this.setTitle("Tarea 3 - DOO");
        this.setSize(1400, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.setLayout(null);
        this.add(new PanelPrincipal()); // Se agrega el PanelPrincipal

        try{
            ImageIcon imagen = new ImageIcon(getClass().getResource("/icon.png"));
            this.setIconImage(imagen.getImage());
        } catch (Exception e) {
            System.err.println("Error al cargar imagen desde la ruta: /icon.png" + e.getMessage());
        }

        this.setVisible(true);

    }
}
