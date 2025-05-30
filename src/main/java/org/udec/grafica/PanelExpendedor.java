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
            ldpCoca.addProducto(new LabelProducto(depositos.get(0).get(i), ProductosEnum.COCACOLA.getNombreImagen()));
            ldpSprite.addProducto(new LabelProducto(depositos.get(1).get(i), ProductosEnum.SPRITE.getNombreImagen()));
            ldpFanta.addProducto(new LabelProducto(depositos.get(2).get(i), ProductosEnum.FANTA.getNombreImagen()));
            ldpSnickers.addProducto(new LabelProducto(depositos.get(3).get(i), ProductosEnum.SNICKERS.getNombreImagen()));
            ldpSuper8.addProducto(new LabelProducto(depositos.get(4).get(i), ProductosEnum.SUPER8.getNombreImagen()));
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

        // Prueba de funcionalidad:
        int cantidadAñadir = 10;
        System.out.println("Deberia ir desde " + (100+cantidadProductos) + " hasta " + (99+cantidadProductos + cantidadAñadir));
        actualizarStock(cantidadAñadir);
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

        // Revisa si elemento ya estaba en deposito antes
        for (ArrayList<Producto> subdeposito : depositosNuevo) {
            for (int i = 0; i < subdeposito.size(); i++) {
                if(depositos.get(depositosNuevo.indexOf(subdeposito)).contains(subdeposito.get(i))){
                    subdeposito.remove(i);
                    i--;
                }
            }
        }

        // Añade productos al labels de deposito de productos.

        // COCACOLA
        for (Producto prod : depositosNuevo.get(0)) {
            // Borrar comentario después.
            System.out.println(prod.getSerie());
            ldpCoca.addProducto(new LabelProducto(prod, ProductosEnum.COCACOLA.getNombreImagen()));
        }

        // SPRITE
        for (Producto prod : depositosNuevo.get(1)) {
            ldpSprite.addProducto(new LabelProducto(prod, ProductosEnum.SPRITE.getNombreImagen()));
        }

        // FANTA
        for (Producto prod : depositosNuevo.get(2)) {
            ldpFanta.addProducto(new LabelProducto(prod, ProductosEnum.FANTA.getNombreImagen()));
        }

        // SNICKERS
        for (Producto prod : depositosNuevo.get(3)) {
            ldpSnickers.addProducto(new LabelProducto(prod, ProductosEnum.SNICKERS.getNombreImagen()));
        }

        // SUPER8
        for (Producto prod : depositosNuevo.get(4)) {
            ldpSuper8.addProducto(new LabelProducto(prod, ProductosEnum.SUPER8.getNombreImagen()));
        }

    }

}
