package org.udec.grafica;

import org.udec.logica.*;

import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {

    PanelMoneda panelMoneda;
    PanelExpendedor panelExpendedor;

    private Producto ultimoComprado = null;

    public PanelComprador (PanelExpendedor panelExpendedor) {
        this.setBackground(PanelPrincipal.ROJO);
        this.setSize(400, 900);
        this.setBounds(1200, 0, 400, 900);
        this.setVisible(true);
        this.panelExpendedor = panelExpendedor;

        this.panelMoneda = new PanelMoneda();
        this.add(panelMoneda);

        JButton botonComprar = new JButton("Comprar producto");
        botonComprar.setPreferredSize(new Dimension(340, 100));
        botonComprar.setFocusable(false);
        botonComprar.setBackground(PanelPrincipal.OSCURO);
        botonComprar.setForeground(PanelPrincipal.AMARILLO);
        botonComprar.setFont(PanelPrincipal.fuentePersonalizadaBotones);
        botonComprar.addActionListener(e -> {
            System.out.println("Boton apretado");
            try {
                if(ultimoComprado == null) {
                    panelExpendedor.getExpendedor().comprarProducto(panelMoneda.getMonedaSeleccionada(), panelExpendedor.getSeleccion());
                    panelExpendedor.removeProducto();
                    ultimoComprado = panelExpendedor.getExpendedor().getProducto();
                    System.out.println("Se ha comprado un producto: " + ultimoComprado.getSerie());
                } else{
                    JOptionPane.showMessageDialog(null, "Recuerda retirar tu última compra", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } catch (PagoIncorrectoException ex) {
                JOptionPane.showMessageDialog(null, "Debes elegir una moneda para pagar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } catch (NoHayProductoException ex) {
                JOptionPane.showMessageDialog(null, "El producto seleccionado no está disponible", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } catch (PagoInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        });
        this.add(botonComprar);


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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
