package org.udec.grafica;

import org.udec.logica.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelMoneda extends JPanel {

    private JButton botonMoneda100;
    private JButton botonMoneda500;
    private JButton botonMoneda1000;
    private Moneda monedaSeleccionada;
    private int monedaTipo;
    private Font fuentePersonalizada;

    // Inicializamos monedas
    Moneda moneda100 = new Moneda100();
    Moneda moneda500 = new Moneda500();
    Moneda moneda1000 = new Moneda1000();

    public PanelMoneda(){

        this.setPreferredSize(new Dimension(360, 124));
        this.setOpaque(false);
        this.setVisible(true);

        fuentePersonalizada = new Font("Serif", Font.BOLD, 24);

        botonMoneda100 = new JButton();
        botonMoneda100.setPreferredSize(new Dimension(110, 120));
        botonMoneda100.setOpaque(true);
        botonMoneda100.setVisible(true);
        botonMoneda100.setFocusable(false);
        botonMoneda100.setFont(fuentePersonalizada);
        botonMoneda100.setText("$100");
        this.add(botonMoneda100);

        botonMoneda500 = new JButton();
        botonMoneda500.setPreferredSize(new Dimension(110, 120));
        botonMoneda500.setOpaque(true);
        botonMoneda500.setVisible(true);
        botonMoneda500.setFocusable(false);
        botonMoneda500.setFont(fuentePersonalizada);
        botonMoneda500.setText("$500");
        this.add(botonMoneda500);

        botonMoneda1000 = new JButton();
        botonMoneda1000.setPreferredSize(new Dimension(110, 120));
        botonMoneda1000.setOpaque(true);
        botonMoneda1000.setVisible(true);
        botonMoneda1000.setFocusable(false);
        botonMoneda1000.setFont(fuentePersonalizada);
        botonMoneda1000.setText("$1000");
        this.add(botonMoneda1000);


        botonMoneda100.addActionListener(e -> {
            monedaSeleccionada = moneda100;
            monedaTipo = 100;
            repaint(1200,0,400,400);
        });
        botonMoneda500.addActionListener(e -> {
            monedaSeleccionada = moneda500;
            monedaTipo = 500;
            repaint(1200,0,400,400);
        });
        botonMoneda1000.addActionListener(e -> {
            monedaSeleccionada = moneda1000;
            monedaTipo = 1000;
            repaint(1200,0,400,400);
        });

    }

    public Moneda getMonedaSeleccionada(){
        return monedaSeleccionada;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch(monedaTipo){
            case 100:
                botonMoneda100.setBackground(PanelPrincipal.AMARILLO);
                botonMoneda100.setForeground(PanelPrincipal.OSCURO);
                botonMoneda500.setBackground(PanelPrincipal.OSCURO);
                botonMoneda1000.setBackground(PanelPrincipal.OSCURO);
                break;
            case 500:
                botonMoneda100.setBackground(PanelPrincipal.OSCURO);
                botonMoneda500.setBackground(PanelPrincipal.AMARILLO);
                botonMoneda1000.setBackground(PanelPrincipal.OSCURO);
                break;
            case 1000:
                botonMoneda100.setBackground(PanelPrincipal.OSCURO);
                botonMoneda500.setBackground(PanelPrincipal.OSCURO);
                botonMoneda1000.setBackground(PanelPrincipal.AMARILLO);
                break;

            default:
                botonMoneda100.setBackground(PanelPrincipal.OSCURO);
                botonMoneda500.setBackground(PanelPrincipal.OSCURO);
                botonMoneda1000.setBackground(PanelPrincipal.OSCURO);
                break;
        }
    }
}
