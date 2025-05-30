package org.udec.grafica;

import org.udec.logica.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelExpendedor extends JPanel {

    private Expendedor expendedor;
    LabelDepositoProducto ldpCoca;
    LabelDepositoProducto ldpSprite;
    LabelDepositoProducto ldpFanta;
    LabelDepositoProducto ldpSnickers;
    LabelDepositoProducto ldpSuper8;
    ArrayList<ArrayList<Producto>> depositos;

    private int cantidadProductos = 13; // ESTA VARIABLE CONTROLA LA CANTIDAD INICIAL.

    public PanelExpendedor() {
        this.setBackground(Color.darkGray);
        this.setSize(1200, 900);
        this.setLayout(null);
        expendedor = new Expendedor(cantidadProductos);

        depositos = expendedor.getDepositos();

        // Sección de inicializar depositos de expendedores

        // Distancia de separacion en x entre cada ldp de momento 150
        ldpCoca = new LabelDepositoProducto(150, 200);
        ldpSprite = new LabelDepositoProducto(300, 200);
        ldpFanta = new LabelDepositoProducto(450, 200);
        ldpSnickers = new LabelDepositoProducto(600, 200);
        ldpSuper8 = new LabelDepositoProducto(750, 200);
        for(int i = 0; i < cantidadProductos; i++) {
            ldpCoca.addProducto(new LabelProducto(depositos.get(0).get(i), "productoejemplo.png"));
            ldpSprite.addProducto(new LabelProducto(depositos.get(1).get(i), "productoejemplo2.png"));
            ldpFanta.addProducto(new LabelProducto(depositos.get(2).get(i), "productoejemplo.png"));
            ldpSnickers.addProducto(new LabelProducto(depositos.get(3).get(i), "productoejemplo2.png"));
            ldpSuper8.addProducto(new LabelProducto(depositos.get(4).get(i), "productoejemplo.png"));
        }

        this.add(ldpCoca);
        this.add(ldpSprite);
        this.add(ldpFanta);
        this.add(ldpSnickers);
        this.add(ldpSuper8);

        // - Fin de sección de inicializar depositos de expendedores

        // Eliminar este botón, es sólo de prueba de métodos.
        JButton boton = new JButton("Test removeProducto()");
        boton.setBounds(400, 100, 200, 50);
        boton.addActionListener(e -> {
            ldpCoca.removeProducto();
            System.out.println("Boton apretado");
            try {
                expendedor.comprarProducto(new Moneda1000(), ProductosEnum.COCACOLA);
                System.out.println("Se ha comprado un producto: " + expendedor.getProducto().usar());
            } catch (PagoIncorrectoException | NoHayProductoException | PagoInsuficienteException |
                     ProductoNoRetiradoException ex) {
                throw new RuntimeException(ex);
            }
            repaint();
        });
        this.add(boton);

        actualizarStock(10);
        // - - -
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    private void actualizarStock(int cantidadAdd) {
        expendedor.recargarStock(cantidadAdd);

        ArrayList<ArrayList<Producto>> depositosNuevo = expendedor.getDepositos();

        for (ArrayList<Producto> subdeposito : depositosNuevo) {
            for (Producto prod : subdeposito) {
                if(depositos.get(0).contains(prod)){
                    subdeposito.remove(prod);
                }
            }
        }
        for (Producto prod : depositosNuevo.get(0)) {
            System.out.println(prod.getSerie());
        }

    }

}
