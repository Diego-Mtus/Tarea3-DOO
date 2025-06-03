package org.udec.grafica;

import org.udec.logica.*;

import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {

    PanelMoneda panelMoneda;
    PanelExpendedor panelExpendedor;

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
                if(panelExpendedor.getUltimoComprado() == null) {
                    panelExpendedor.getExpendedor().comprarProducto(panelMoneda.getMonedaSeleccionada(), panelExpendedor.getSeleccion());
                    panelExpendedor.removeProducto();
                    panelExpendedor.setUltimoCompradoDesdeExpendedor();
                    System.out.println("Se ha comprado un producto: " + panelExpendedor.getUltimoComprado().getSerie());
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

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
