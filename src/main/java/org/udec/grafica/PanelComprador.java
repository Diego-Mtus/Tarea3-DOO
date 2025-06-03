package org.udec.grafica;

import org.udec.logica.*;

import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {

    private PanelMoneda panelMoneda;
    private PanelExpendedor panelExpendedor;
    private JButton botonConseguirProducto;

    private Producto ultimoComprado = null;
    public ImageIcon[] imagenesVentanaProducto;

    public PanelComprador (PanelExpendedor panelExpendedor) {
        this.setBackground(PanelPrincipal.ROJO);
        this.setSize(400, 900);
        this.setBounds(1200, 0, 400, 900);
        this.setVisible(true);
        this.panelExpendedor = panelExpendedor;

        // Precargar imagenes que se desplegan al apretar botón de usar
        try {
            imagenesVentanaProducto = new ImageIcon[6];
            imagenesVentanaProducto[0] = new ImageIcon(getClass().getResource("/1.png"));
            imagenesVentanaProducto[1] = new ImageIcon(getClass().getResource("/2.png"));
            imagenesVentanaProducto[2] = new ImageIcon(getClass().getResource("/3.png"));
            imagenesVentanaProducto[3] = new ImageIcon(getClass().getResource("/4.png"));
            imagenesVentanaProducto[4] = new ImageIcon(getClass().getResource("/5.png"));
        } catch (Exception e) {
            System.err.println("Error al cargar imagenes de productos usar");
        }

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
                    botonConseguirProducto.setVisible(true);
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


        // Botón de conseguir producto
        botonConseguirProducto = new JButton("Conseguir producto");
        botonConseguirProducto.setPreferredSize(new Dimension(340, 340));
        botonConseguirProducto.setFont(PanelPrincipal.fuentePersonalizadaBotones);
        botonConseguirProducto.setForeground(PanelPrincipal.AMARILLO);
        botonConseguirProducto.setBackground(PanelPrincipal.OSCURO);
        botonConseguirProducto.setFocusable(false);
        botonConseguirProducto.setVisible(false);
        this.add(botonConseguirProducto);
        botonConseguirProducto.addActionListener(e -> {
            System.out.println("Boton apretado: " + ultimoComprado.usar());
            crearVentanaConseguirProducto();
            botonConseguirProducto.setVisible(false);
            ultimoComprado = null;
        });
    }

    private void crearVentanaConseguirProducto() {
        JDialog ventana = new JDialog((JFrame) SwingUtilities.getWindowAncestor(PanelComprador.this), "Conseguir producto", true);
        ventana.setLayout(new BorderLayout());

        JLabel texto = new JLabel(ultimoComprado.usar() + " Serie: " + ultimoComprado.getSerie());
        texto.setFont(PanelPrincipal.fuentePersonalizadaBotones);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        ventana.add(texto, BorderLayout.SOUTH);


        if(ultimoComprado instanceof CocaCola){
            ventana.add(new JLabel(imagenesVentanaProducto[0]), BorderLayout.CENTER);
        } else if(ultimoComprado instanceof Sprite){
            ventana.add(new JLabel(imagenesVentanaProducto[1]), BorderLayout.CENTER);
        } else if(ultimoComprado instanceof Fanta){
            ventana.add(new JLabel(imagenesVentanaProducto[2]), BorderLayout.CENTER);
        } else if(ultimoComprado instanceof Snickers){
            ventana.add(new JLabel(imagenesVentanaProducto[3]), BorderLayout.CENTER);
        } else if(ultimoComprado instanceof Super8){
            ventana.add(new JLabel(imagenesVentanaProducto[4]), BorderLayout.CENTER);
        }

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
