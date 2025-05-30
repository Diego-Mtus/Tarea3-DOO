package org.udec.grafica;

import org.udec.logica.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelExpendedor extends JPanel {

    private Expendedor expendedor;
    private LabelDepositoProducto ldpCoca;
    private LabelDepositoProducto ldpSprite;
    private LabelDepositoProducto ldpFanta;
    private LabelDepositoProducto ldpSnickers;
    private LabelDepositoProducto ldpSuper8;
    private ArrayList<ArrayList<Producto>> depositos;

    private Producto ultimoComprado = null;

    public ImageIcon[] imagenesProductos;

    private int cantidadProductos = 13; // ESTA VARIABLE CONTROLA LA CANTIDAD INICIAL.

    public PanelExpendedor() {
        this.setBackground(Color.darkGray);
        this.setSize(1200, 900);
        this.setLayout(null);

        precargarImagenesProductos();

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
            ldpCoca.addProducto(new LabelProducto(depositos.get(0).get(i), imagenesProductos[0]));
            ldpSprite.addProducto(new LabelProducto(depositos.get(1).get(i), imagenesProductos[1]));
            ldpFanta.addProducto(new LabelProducto(depositos.get(2).get(i), imagenesProductos[2]));
            ldpSnickers.addProducto(new LabelProducto(depositos.get(3).get(i), imagenesProductos[3]));
            ldpSuper8.addProducto(new LabelProducto(depositos.get(4).get(i), imagenesProductos[4]));
        }

        this.add(ldpCoca);
        this.add(ldpSprite);
        this.add(ldpFanta);
        this.add(ldpSnickers);
        this.add(ldpSuper8);

        // - Fin de sección de inicializar depositos de expendedores

        // PRUEBAS: FIXME

        // Eliminar estos botones, es sólo de prueba de métodos removeProducto()
        JButton boton = new JButton("Test removeProducto()");
        boton.setBounds(300, 100, 180, 50);
        boton.addActionListener(e -> {
            System.out.println("Boton apretado");
            try {
                if(ultimoComprado == null) {
                    expendedor.comprarProducto(new Moneda1000(), ProductosEnum.COCACOLA);
                    ldpCoca.removeProducto();

                    ultimoComprado = expendedor.getProducto();
                    System.out.println("Se ha comprado un producto: " + ultimoComprado.getSerie());
                } else{
                    System.out.println("Se debe retirar producto primero.");
                }
            } catch (PagoIncorrectoException | NoHayProductoException | PagoInsuficienteException |
                     ProductoNoRetiradoException ex) {
                throw new RuntimeException(ex);
            }
            repaint(ldpCoca.getX(), ldpCoca.getY(), ldpCoca.getWidth(), ldpCoca.getHeight());
        });
        this.add(boton);

        JButton botonUsar = new JButton("Test usar producto");
        botonUsar.setBounds(500,100,180,50);
        botonUsar.addActionListener(e -> {
            System.out.println("Boton apretado");
            if(ultimoComprado != null){
                System.out.println(ultimoComprado.usar());
                ultimoComprado = null;
            } else{
                System.out.println("No tienes un producto a retirar.");
            }
        });
        this.add(botonUsar);

        // Prueba de funcionalidad de actualizarStock():
        int cantidadAñadir = 10;
        System.out.println("Deberia ir desde " + (100+cantidadProductos) + " hasta " + (99+cantidadProductos + cantidadAñadir));
        actualizarStock(cantidadAñadir);
        // - - -

        PanelSelectorProducto pselector = new PanelSelectorProducto();
        pselector.setBounds(900,450,64,50);
        this.add(pselector);

        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void precargarImagenesProductos(){
        try {
            imagenesProductos = new ImageIcon[ProductosEnum.values().length];
            for(ProductosEnum prod : ProductosEnum.values()){
                imagenesProductos[prod.ordinal()] = new ImageIcon(getClass().getResource("/" + prod.getNombreImagen()));
            }
        } catch (Exception e) {
            System.err.println("Error al cargar imagenes de productos");
        }
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
            ldpCoca.addProducto(new LabelProducto(prod, imagenesProductos[0]));
        }

        // SPRITE
        for (Producto prod : depositosNuevo.get(1)) {
            ldpSprite.addProducto(new LabelProducto(prod, imagenesProductos[1]));
        }

        // FANTA
        for (Producto prod : depositosNuevo.get(2)) {
            ldpFanta.addProducto(new LabelProducto(prod, imagenesProductos[2]));
        }

        // SNICKERS
        for (Producto prod : depositosNuevo.get(3)) {
            ldpSnickers.addProducto(new LabelProducto(prod, imagenesProductos[3]));
        }

        // SUPER8
        for (Producto prod : depositosNuevo.get(4)) {
            ldpSuper8.addProducto(new LabelProducto(prod, imagenesProductos[4]));
        }

    }

}
