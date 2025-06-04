package org.udec.grafica;

import org.udec.logica.*;

import javax.swing.*;
import java.awt.*;

public class PanelMoneda extends JPanel {

    private JButton botonMoneda100;
    private JButton botonMoneda500;
    private JButton botonMoneda1000;
    private int monedaTipo;

    // Creamos depositos
    private Deposito<Moneda> depositoMoneda100;
    private Deposito<Moneda> depositoMoneda500;
    private Deposito<Moneda> depositoMoneda1000;

    public PanelMoneda(){

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(360, 150));
        this.setMaximumSize(new Dimension(360, 150));
        this.setOpaque(false);
        this.setVisible(true);

        JLabel texto = new JLabel("Deposito de monedas");
        texto.setFont(PanelPrincipal.fuentePersonalizadaBotones);
        texto.setForeground(PanelPrincipal.AMARILLO);
        this.add(texto, BorderLayout.NORTH);
        JPanel botones = new JPanel();
        botones.setOpaque(false);
        botones.setBorder(null);
        botones.setMaximumSize(new Dimension(360, 120));

        depositoMoneda100 = new Deposito<>();
        depositoMoneda500 = new Deposito<>();
        depositoMoneda1000 = new Deposito<>();

        // Cantidad inicial de monedas: "El comprador debe empezar con una suma de dinero suficiente para varias compras y ser capaz de recoger el
        // vuelto a medida que avanza"
        depositoMoneda100.add(new Moneda100());
        depositoMoneda500.add(new Moneda500());
        depositoMoneda500.add(new Moneda500());
        depositoMoneda1000.add(new Moneda1000());
        depositoMoneda1000.add(new Moneda1000());
        depositoMoneda1000.add(new Moneda1000());

        botonMoneda100 = new JButton();
        botonMoneda100.setPreferredSize(new Dimension(110, 110));
        botonMoneda100.setOpaque(true);
        botonMoneda100.setVisible(true);
        botonMoneda100.setFocusable(false);
        botonMoneda100.setFont(PanelPrincipal.fuentePersonalizadaBotones);
        botonMoneda100.setText("$100");
        botonMoneda100.setToolTipText("Cantidad: " + depositoMoneda100.getSize());
        //this.add(botonMoneda100, BorderLayout.WEST);
        botones.add(botonMoneda100);

        botonMoneda500 = new JButton();
        botonMoneda500.setPreferredSize(new Dimension(110, 110));
        botonMoneda500.setOpaque(true);
        botonMoneda500.setVisible(true);
        botonMoneda500.setFocusable(false);
        botonMoneda500.setFont(PanelPrincipal.fuentePersonalizadaBotones);
        botonMoneda500.setText("$500");
        botonMoneda500.setToolTipText("Cantidad: " + depositoMoneda500.getSize());
        //this.add(botonMoneda500, BorderLayout.CENTER);
        botones.add(botonMoneda500);

        botonMoneda1000 = new JButton();
        botonMoneda1000.setPreferredSize(new Dimension(110, 110));
        botonMoneda1000.setOpaque(true);
        botonMoneda1000.setVisible(true);
        botonMoneda1000.setFocusable(false);
        botonMoneda1000.setFont(PanelPrincipal.fuentePersonalizadaBotones);
        botonMoneda1000.setText("$1000");
        botonMoneda1000.setToolTipText("Cantidad: " + depositoMoneda1000.getSize());
        //this.add(botonMoneda1000, BorderLayout.EAST);
        botones.add(botonMoneda1000);
        this.add(botones, BorderLayout.CENTER);

        botonMoneda100.addActionListener(e -> {
            monedaTipo = 100;
            repaint(1200,0,400,400);
        });
        botonMoneda500.addActionListener(e -> {
            monedaTipo = 500;
            repaint(1200,0,400,400);
        });
        botonMoneda1000.addActionListener(e -> {
            monedaTipo = 1000;
            repaint(1200,0,400,400);
        });

    }

    public Moneda getMonedaSeleccionada() {
        return switch (monedaTipo) {
            case 100 -> depositoMoneda100.get();
            case 500 -> depositoMoneda500.get();
            case 1000 -> depositoMoneda1000.get();
            default -> null;
        };
    }

    public void addMoneda(Moneda moneda) {
        if (moneda instanceof Moneda100) {
            depositoMoneda100.add(moneda);
            botonMoneda100.setToolTipText("Cantidad: " + depositoMoneda100.getSize());
        } else if (moneda instanceof Moneda500) {
            depositoMoneda500.add(moneda);
            botonMoneda500.setToolTipText("Cantidad: " + depositoMoneda500.getSize());
        } else if (moneda instanceof Moneda1000) {
            depositoMoneda1000.add(moneda);
            botonMoneda1000.setToolTipText("Cantidad: " + depositoMoneda1000.getSize());
        }
    }

    public void actualizarTooltipMoneda(){
        botonMoneda100.setToolTipText("Cantidad: " + depositoMoneda100.getSize());
        botonMoneda500.setToolTipText("Cantidad: " + depositoMoneda500.getSize());
        botonMoneda1000.setToolTipText("Cantidad: " + depositoMoneda1000.getSize());
    }

    public void reorganizarMonedas(){
        while(depositoMoneda100.getSize() >= 5){
            // Si la cantidad de monedas de 100 es suficiente para hacer un billete de mil
            if(depositoMoneda100.getSize() >= 10){
                // "Intercambiamos" monedas
                for(int i = 0; i < 10; i++){
                    depositoMoneda100.get();
                }
                depositoMoneda1000.add(new Moneda1000());
            } else{
                for(int i = 0; i < 5; i++){
                    depositoMoneda100.get();
                }
                depositoMoneda500.add(new Moneda500());
            }
        }
        while(depositoMoneda500.getSize() >= 2){
            depositoMoneda500.get();
            depositoMoneda500.get();
            depositoMoneda1000.add(new Moneda1000());
        }

        actualizarTooltipMoneda();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch(monedaTipo){
            case 100:
                botonMoneda100.setBackground(PanelPrincipal.AMARILLO);
                botonMoneda100.setForeground(PanelPrincipal.OSCURO);

                botonMoneda500.setBackground(PanelPrincipal.OSCURO);
                botonMoneda500.setForeground(PanelPrincipal.AMARILLO);

                botonMoneda1000.setBackground(PanelPrincipal.OSCURO);
                botonMoneda1000.setForeground(PanelPrincipal.AMARILLO);
                break;
            case 500:
                botonMoneda100.setBackground(PanelPrincipal.OSCURO);
                botonMoneda100.setForeground(PanelPrincipal.AMARILLO);

                botonMoneda500.setBackground(PanelPrincipal.AMARILLO);
                botonMoneda500.setForeground(PanelPrincipal.OSCURO);

                botonMoneda1000.setBackground(PanelPrincipal.OSCURO);
                botonMoneda1000.setForeground(PanelPrincipal.AMARILLO);
                break;
            case 1000:
                botonMoneda100.setBackground(PanelPrincipal.OSCURO);
                botonMoneda100.setForeground(PanelPrincipal.AMARILLO);

                botonMoneda500.setBackground(PanelPrincipal.OSCURO);
                botonMoneda500.setForeground(PanelPrincipal.AMARILLO);

                botonMoneda1000.setBackground(PanelPrincipal.AMARILLO);
                botonMoneda1000.setForeground(PanelPrincipal.OSCURO);

                break;

            default:
                botonMoneda100.setBackground(PanelPrincipal.OSCURO);
                botonMoneda100.setForeground(PanelPrincipal.AMARILLO);

                botonMoneda500.setBackground(PanelPrincipal.OSCURO);
                botonMoneda500.setForeground(PanelPrincipal.AMARILLO);

                botonMoneda1000.setBackground(PanelPrincipal.OSCURO);
                botonMoneda1000.setForeground(PanelPrincipal.AMARILLO);
                break;
        }
    }
}
