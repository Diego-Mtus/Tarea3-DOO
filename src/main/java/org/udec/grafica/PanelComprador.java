package org.udec.grafica;

import org.udec.logica.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelComprador extends JPanel {

    private PanelMoneda panelMoneda;
    private PanelExpendedor panelExpendedor;
    private JButton botonConseguirProducto;
    private JButton botonSaldo;
    private JButton botonComprar;

    private int vueltoDinero = 0;
    private ArrayList<Moneda> vueltoMonedas;
    private Producto ultimoComprado = null;
    private ImageIcon[] imagenMoneda;

    private ImageIcon[] imagenesVentanaProducto;


    public PanelComprador (PanelExpendedor panelExpendedor) {
        this.setBackground(PanelPrincipal.ROJO);
        this.setSize(400, 800);
        this.setBounds(1000, 0, 400, 800);
        this.setVisible(true);
        this.panelExpendedor = panelExpendedor;
        this.vueltoMonedas = new ArrayList<>();

        // Precargar imagenes
        try {
            imagenesVentanaProducto = new ImageIcon[6];
            imagenesVentanaProducto[0] = new ImageIcon(getClass().getResource("/1.png"));
            imagenesVentanaProducto[1] = new ImageIcon(getClass().getResource("/2.png"));
            imagenesVentanaProducto[2] = new ImageIcon(getClass().getResource("/3.png"));
            imagenesVentanaProducto[3] = new ImageIcon(getClass().getResource("/4.png"));
            imagenesVentanaProducto[4] = new ImageIcon(getClass().getResource("/5.png"));

            imagenMoneda = new ImageIcon[3];
            imagenMoneda[0] = new ImageIcon(getClass().getResource("/100.png"));
            imagenMoneda[1] = new ImageIcon(getClass().getResource("/500.png"));
            imagenMoneda[2] = new ImageIcon(getClass().getResource("/1000.png"));
        } catch (Exception e) {
            System.err.println("Error al cargar imagenes de panel comprador");
        }

        this.panelMoneda = new PanelMoneda();
        this.add(panelMoneda);

        botonComprar = new JButton("Comprar producto");
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
                    Moneda aux = panelExpendedor.getExpendedor().getVuelto();
                    while(aux != null){
                        vueltoDinero += aux.getValor();
                        vueltoMonedas.add(aux);
                        aux = panelExpendedor.getExpendedor().getVuelto();
                    }
                    botonConseguirProducto.setVisible(true);
                    System.out.println("Se ha comprado un producto: " + ultimoComprado.getSerie() + ", vuelto total: $" + vueltoDinero);
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

        // Boton de ver vuelto
        botonSaldo = new JButton("Ver vuelto total");
        botonSaldo.setPreferredSize(new Dimension(340, 100));
        botonSaldo.setFocusable(false);
        botonSaldo.setFont(PanelPrincipal.fuentePersonalizadaBotones);
        botonSaldo.setForeground(PanelPrincipal.AMARILLO);
        botonSaldo.setBackground(PanelPrincipal.OSCURO);
        this.add(botonSaldo);
        botonSaldo.addActionListener(e -> {
            System.out.println("Boton apretado: Boton ver vuelto");
            if(!vueltoMonedas.isEmpty()){
                crearVentanaVuelto();
            } else{
                JOptionPane.showMessageDialog(null, "No has recibido vuelto todavía", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

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

    private void crearVentanaVuelto(){
        JDialog ventana = new JDialog((JFrame) SwingUtilities.getWindowAncestor(PanelComprador.this), "Vuelto", true);
        ventana.setLayout(new FlowLayout(FlowLayout.CENTER));
        ventana.setMaximumSize(new Dimension(1400, 600));
        ventana.setMinimumSize(new Dimension(1400, 600));
        ventana.setPreferredSize(new Dimension(1400, 600));
        recibirVuelto(ventana);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private void recibirVuelto(JDialog ventana){
        for(Moneda mon : vueltoMonedas){
            JLabel moneda;
            if(mon instanceof Moneda100){
                moneda = new JLabel(imagenMoneda[0]);
            } else if(mon instanceof Moneda500){
                moneda = new JLabel(imagenMoneda[1]);
            } else{
                moneda = new JLabel(imagenMoneda[2]);
            }
            moneda.setText("Serie: " +mon.getSerie());
            moneda.setVerticalTextPosition(SwingConstants.BOTTOM);
            moneda.setHorizontalTextPosition(SwingConstants.CENTER);
            ventana.add(moneda);
        }

        ventana.add(new JLabel("Total: " + vueltoDinero + " pesos."));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
