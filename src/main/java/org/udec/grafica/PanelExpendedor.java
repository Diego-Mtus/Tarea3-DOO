package org.udec.grafica;

import org.udec.logica.Deposito;
import org.udec.logica.Expendedor;
import org.udec.logica.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelExpendedor extends JPanel {

    private Expendedor expendedor;

    private int cantidadProductos = 13; // ESTA VARIABLE CONTROLA LA CANTIDAD DE CADA PRODUCTO.

    public PanelExpendedor() {
        this.setBackground(Color.darkGray);
        this.setSize(1200, 900);
        this.setLayout(null);
        expendedor = new Expendedor(cantidadProductos);

        ArrayList<Deposito<Producto>> depositos = expendedor.getDepositos();
        LabelDepositoProducto ldp1 = new LabelDepositoProducto(100, 200);
        LabelDepositoProducto ldp2 = new LabelDepositoProducto(250, 200);
        for(int i = 0; i < cantidadProductos; i++) {
            ldp1.addProducto(new LabelProducto(depositos.get(0).get(), "productoejemplo.png"));
            ldp2.addProducto(new LabelProducto(depositos.get(1).get(), "productoejemplo2.png"));
        }

        this.add(ldp1);
        this.add(ldp2);

        // Eliminar este botón, es sólo de prueba de métodos.
        JButton boton = new JButton("Test removeProducto()");
        boton.setBounds(400, 500, 200, 50);
        boton.addActionListener(e -> {
            ldp1.removeProducto();
            System.out.println("Boton apretado");
            repaint();
        });
        this.add(boton);

        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
