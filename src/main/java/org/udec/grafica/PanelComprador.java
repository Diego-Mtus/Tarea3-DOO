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

        // Ajustes de panelComprador
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

        // Creación de panel de monedas
        this.panelMoneda = new PanelMoneda();
        this.add(panelMoneda);


        // Boton de comprar
        botonComprar = new JButton("Comprar producto");
        botonComprar.setPreferredSize(new Dimension(340, 80));
        botonComprar.setFocusable(false);
        botonComprar.setBackground(PanelPrincipal.OSCURO);
        botonComprar.setForeground(PanelPrincipal.AMARILLO);
        botonComprar.setFont(PanelPrincipal.FUENTE_PERSONALIZADA);
        botonComprar.addActionListener(e -> {
            Moneda ultimaMoneda = panelMoneda.getMonedaSeleccionada();
            try {
                if(ultimoComprado == null) {
                    panelExpendedor.getExpendedor().comprarProducto(ultimaMoneda, panelExpendedor.getSeleccion());
                    panelExpendedor.removeProducto();
                    ultimoComprado = panelExpendedor.getExpendedor().getProducto();
                    Moneda aux = panelExpendedor.getExpendedor().getVuelto();
                    while(aux != null){
                        vueltoDinero += aux.getValor();
                        vueltoMonedas.add(aux);
                        aux = panelExpendedor.getExpendedor().getVuelto();
                    }
                    panelExpendedor.actualizarValores(panelExpendedor.getSeleccion(), panelExpendedor.getStockIndividual(panelExpendedor.getSeleccion().ordinal()));
                    panelMoneda.actualizarTooltipMoneda();
                    botonConseguirProducto.setVisible(true);
                    System.out.println("Se ha comprado un producto: " + ultimoComprado.getSerie() + ", vuelto total: $" + vueltoDinero);
                } else{
                    JOptionPane.showMessageDialog(null, "Recuerda retirar tu última compra", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    panelMoneda.addMoneda(ultimaMoneda);
                }
            } catch (PagoIncorrectoException ex) {
                JOptionPane.showMessageDialog(null, "No tienes de ese tipo de monedas", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } catch (NoHayProductoException ex) {
                JOptionPane.showMessageDialog(null, "El producto seleccionado no está disponible", "Advertencia", JOptionPane.WARNING_MESSAGE);
                panelMoneda.addMoneda(ultimaMoneda);
            } catch (PagoInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero", "Advertencia", JOptionPane.WARNING_MESSAGE);
                panelMoneda.addMoneda(ultimaMoneda);
            } catch (NoSeleccionException ex) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un producto primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
                panelMoneda.addMoneda(ultimaMoneda);
            }

        });
        this.add(botonComprar);

        // Boton de ver vuelto
        botonSaldo = new JButton("Ver vuelto total");
        botonSaldo.setPreferredSize(new Dimension(340, 80));
        botonSaldo.setFocusable(false);
        botonSaldo.setFont(PanelPrincipal.FUENTE_PERSONALIZADA);
        botonSaldo.setForeground(PanelPrincipal.AMARILLO);
        botonSaldo.setBackground(PanelPrincipal.OSCURO);
        this.add(botonSaldo);
        botonSaldo.addActionListener(e -> {
            if(!vueltoMonedas.isEmpty()){
                crearVentanaVuelto();
            } else{
                JOptionPane.showMessageDialog(null, "No has recibido vuelto todavía", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Botón de llevar vuelto a monedero
        JButton botonReinicio = new JButton("Llevar vuelto a monedero");
        botonReinicio.setPreferredSize(new Dimension(340, 40));
        botonReinicio.setFont(PanelPrincipal.FUENTE_PERSONALIZADA);
        botonReinicio.setForeground(PanelPrincipal.AMARILLO);
        botonReinicio.setBackground(PanelPrincipal.OSCURO);
        botonReinicio.setFocusable(false);
        this.add(botonReinicio);
        botonReinicio.addActionListener(e -> {
            for(Moneda moneda : vueltoMonedas){
                panelMoneda.addMoneda(moneda);
            }
            vueltoDinero = 0;
            vueltoMonedas.clear();
        });

        // Botón de intercambiar monedas
        JButton botonReorganizar = new JButton("Intercambiar monedas");
        botonReorganizar.setPreferredSize(new Dimension(340, 40));
        botonReorganizar.setFont(PanelPrincipal.FUENTE_PERSONALIZADA);
        botonReorganizar.setForeground(PanelPrincipal.AMARILLO);
        botonReorganizar.setBackground(PanelPrincipal.OSCURO);
        botonReorganizar.setFocusable(false);
        this.add(botonReorganizar);
        botonReorganizar.addActionListener(e -> {
            panelMoneda.reorganizarMonedas();
            JOptionPane.showMessageDialog(null, "¡Has intercambiado tus monedas!", "Información", JOptionPane.INFORMATION_MESSAGE);
        });

        // Botón de conseguir producto
        botonConseguirProducto = new JButton("Conseguir producto");
        botonConseguirProducto.setPreferredSize(new Dimension(340, 320));
        botonConseguirProducto.setFont(PanelPrincipal.FUENTE_PERSONALIZADA);
        botonConseguirProducto.setForeground(PanelPrincipal.AMARILLO);
        botonConseguirProducto.setBackground(PanelPrincipal.OSCURO);
        botonConseguirProducto.setFocusable(false);
        botonConseguirProducto.setVisible(false);
        this.add(botonConseguirProducto);
        botonConseguirProducto.addActionListener(e -> {
            crearVentanaConseguirProducto();
            botonConseguirProducto.setVisible(false);
            ultimoComprado = null;
        });
    }

    private void crearVentanaConseguirProducto() {
        JDialog ventana = new JDialog((JFrame) SwingUtilities.getWindowAncestor(PanelComprador.this), "Conseguir producto", true);
        ventana.setLayout(new BorderLayout());

        JLabel texto = new JLabel(ultimoComprado.usar() + " Serie: " + ultimoComprado.getSerie());
        texto.setFont(PanelPrincipal.FUENTE_PERSONALIZADA);
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
        // Valor elegido por nosostros para no excederse del tamaño de ventana máximo.
        if(vueltoMonedas.size() < 30){
        JDialog ventana = new JDialog((JFrame) SwingUtilities.getWindowAncestor(PanelComprador.this), "Vuelto", true);
        ventana.setLayout(new FlowLayout(FlowLayout.CENTER));
        ventana.setMaximumSize(new Dimension(1400, 600));
        ventana.setMinimumSize(new Dimension(1400, 600));
        ventana.setPreferredSize(new Dimension(1400, 600));
        recibirVuelto(ventana);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Tienes muchas monedas como para desplegarlas. \nTotal: " + vueltoDinero + " pesos.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void recibirVuelto(JDialog ventana){

        vueltoMonedas.sort(Moneda::compareTo);
            for (Moneda mon : vueltoMonedas) {
                JLabel moneda;
                if (mon instanceof Moneda100) {
                    moneda = new JLabel(imagenMoneda[0]);
                } else if (mon instanceof Moneda500) {
                    moneda = new JLabel(imagenMoneda[1]);
                } else {
                    moneda = new JLabel(imagenMoneda[2]);
                }
                moneda.setText("Serie: " + mon.getSerie());
                moneda.setVerticalTextPosition(SwingConstants.BOTTOM);
                moneda.setHorizontalTextPosition(SwingConstants.CENTER);
                ventana.add(moneda);
            }

            JLabel texto = new JLabel("Total: " + vueltoDinero + " pesos.");
            texto.setFont(PanelPrincipal.FUENTE_PERSONALIZADA);
            ventana.add(texto, BorderLayout.SOUTH);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
