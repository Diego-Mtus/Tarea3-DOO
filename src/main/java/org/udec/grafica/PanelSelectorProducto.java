package org.udec.grafica;

import org.udec.logica.ProductosEnum;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;


public class PanelSelectorProducto extends JPanel {

    private ImageIcon[][] imagenesLogoProducto;
    private JLabel botonCocacola, botonSprite, botonFanta, botonSnickers, botonSuper8, botonRestock;
    private PanelExpendedor panelExpendedor;

    private Clip clipClick;
    private Clip clipHover;
    private boolean sonidoHoverYaReproducido = false;

    public PanelSelectorProducto(PanelExpendedor panelExpendedor){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);

        this.panelExpendedor = panelExpendedor;

        // Cargar sonido
        try{
            // Sonido de pasar mouse por encima
            InputStream sonidoStreamHover = getClass().getResourceAsStream("/hover.wav");
            if (sonidoStreamHover == null) {
                throw new IllegalArgumentException("Recurso no encontrado: /hover.wav");
            }
            AudioInputStream audioInputStreamHover = AudioSystem.getAudioInputStream(new BufferedInputStream(sonidoStreamHover));
            clipHover = AudioSystem.getClip();
            clipHover.open(audioInputStreamHover);

            // Sonido de clickear boton
            InputStream sonidoStreamClick = getClass().getResourceAsStream("/click.wav");
            if (sonidoStreamClick == null) {
                throw new IllegalArgumentException("Recurso no encontrado: /click.wav");
            }
            AudioInputStream audioInputStreamClick = AudioSystem.getAudioInputStream(new BufferedInputStream(sonidoStreamClick));
            clipClick = AudioSystem.getClip();
            clipClick.open(audioInputStreamClick);

            // Volumen
            FloatControl controlVolumenHover = (FloatControl) clipHover.getControl(FloatControl.Type.MASTER_GAIN);
            controlVolumenHover.setValue(-18.0f);
            FloatControl controlVolumenClick = (FloatControl) clipClick.getControl(FloatControl.Type.MASTER_GAIN);
            controlVolumenClick.setValue(-18.0f);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        // Precargar logos
        try {
            this.imagenesLogoProducto = new ImageIcon[6][2];
            imagenesLogoProducto[0][0] = new ImageIcon(getClass().getResource("/cocacolalogo1.png"));
            imagenesLogoProducto[1][0] = new ImageIcon(getClass().getResource("/spritelogo1.png"));
            imagenesLogoProducto[2][0] = new ImageIcon(getClass().getResource("/fantalogo1.png"));
            imagenesLogoProducto[3][0] = new ImageIcon(getClass().getResource("/snickerslogo1.png"));
            imagenesLogoProducto[4][0] = new ImageIcon(getClass().getResource("/super8logo1.png"));
            imagenesLogoProducto[5][0] = new ImageIcon(getClass().getResource("/restocklogo1.png"));

            imagenesLogoProducto[0][1] = new ImageIcon(getClass().getResource("/cocacolalogo2.png"));
            imagenesLogoProducto[1][1] = new ImageIcon(getClass().getResource("/spritelogo2.png"));
            imagenesLogoProducto[2][1] = new ImageIcon(getClass().getResource("/fantalogo2.png"));
            imagenesLogoProducto[3][1] = new ImageIcon(getClass().getResource("/snickerslogo2.png"));
            imagenesLogoProducto[4][1] = new ImageIcon(getClass().getResource("/super8logo2.png"));
            imagenesLogoProducto[5][1] = new ImageIcon(getClass().getResource("/restocklogo2.png"));

        } catch (Exception e) {
            System.err.println("Error al cargar imagenes de panel selector de productos");
        }


        // Inicialización y funcionamiento de botones.
        botonCocacola = new JLabel(imagenesLogoProducto[0][0]);
        botonCocacola.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonCocacola.setIcon(imagenesLogoProducto[0][1]);
                if(!sonidoHoverYaReproducido){
                    reproducirSonido(clipHover);
                    sonidoHoverYaReproducido = true;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonCocacola.setIcon(imagenesLogoProducto[0][0]);
                sonidoHoverYaReproducido = false;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                panelExpendedor.setSeleccion(ProductosEnum.COCACOLA);
                panelExpendedor.actualizarValores(ProductosEnum.COCACOLA, panelExpendedor.getStockIndividual(0));
                reproducirSonido(clipClick);
            }
        });
        this.add(botonCocacola);
        this.add(Box.createRigidArea(new Dimension(0,4)));

        botonSprite = new JLabel(imagenesLogoProducto[1][0]);
        botonSprite.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonSprite.setIcon(imagenesLogoProducto[1][1]);
                if(!sonidoHoverYaReproducido){
                    reproducirSonido(clipHover);
                    sonidoHoverYaReproducido = true;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonSprite.setIcon(imagenesLogoProducto[1][0]);
                sonidoHoverYaReproducido = false;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                panelExpendedor.setSeleccion(ProductosEnum.SPRITE);
                panelExpendedor.actualizarValores(ProductosEnum.SPRITE, panelExpendedor.getStockIndividual(1));
                reproducirSonido(clipClick);
            }
        });
        this.add(botonSprite);
        this.add(Box.createRigidArea(new Dimension(0,4)));
        
        botonFanta = new JLabel(imagenesLogoProducto[2][0]);
        botonFanta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonFanta.setIcon(imagenesLogoProducto[2][1]);
                if(!sonidoHoverYaReproducido){
                    reproducirSonido(clipHover);
                    sonidoHoverYaReproducido = true;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonFanta.setIcon(imagenesLogoProducto[2][0]);
                sonidoHoverYaReproducido = false;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                panelExpendedor.setSeleccion(ProductosEnum.FANTA);
                panelExpendedor.actualizarValores(ProductosEnum.FANTA, panelExpendedor.getStockIndividual(2));
                reproducirSonido(clipClick);
            }
        });
        this.add(botonFanta);
        this.add(Box.createRigidArea(new Dimension(0,4)));

        botonSnickers = new JLabel(imagenesLogoProducto[3][0]);
        botonSnickers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonSnickers.setIcon(imagenesLogoProducto[3][1]);
                if(!sonidoHoverYaReproducido){
                    reproducirSonido(clipHover);
                    sonidoHoverYaReproducido = true;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonSnickers.setIcon(imagenesLogoProducto[3][0]);
                sonidoHoverYaReproducido = false;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                panelExpendedor.setSeleccion(ProductosEnum.SNICKERS);
                panelExpendedor.actualizarValores(ProductosEnum.SNICKERS, panelExpendedor.getStockIndividual(3));
                reproducirSonido(clipClick);
            }
        });
        this.add(botonSnickers);
        this.add(Box.createRigidArea(new Dimension(0,4)));

        botonSuper8 = new JLabel(imagenesLogoProducto[4][0]);
        botonSuper8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonSuper8.setIcon(imagenesLogoProducto[4][1]);
                if(!sonidoHoverYaReproducido){
                    reproducirSonido(clipHover);
                    sonidoHoverYaReproducido = true;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonSuper8.setIcon(imagenesLogoProducto[4][0]);
                sonidoHoverYaReproducido = false;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                panelExpendedor.setSeleccion(ProductosEnum.SUPER8);
                panelExpendedor.actualizarValores(ProductosEnum.SUPER8, panelExpendedor.getStockIndividual(4));
                reproducirSonido(clipClick);
            }
        });
        this.add(botonSuper8);
        this.add(Box.createRigidArea(new Dimension(0,190)));

        botonRestock = new JLabel(imagenesLogoProducto[5][0]);
        botonRestock.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonRestock.setIcon(imagenesLogoProducto[5][1]);
                if(!sonidoHoverYaReproducido){
                    reproducirSonido(clipHover);
                    sonidoHoverYaReproducido = true;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonRestock.setIcon(imagenesLogoProducto[5][0]);
                sonidoHoverYaReproducido = false;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                panelExpendedor.actualizarStock();
                panelExpendedor.mostrarRenovacion();
                reproducirSonido(clipClick);
            }
        });
        this.add(botonRestock);

        this.setVisible(true);
    }

    private void reproducirSonido(Clip clip){
        if(clip != null){
            if(clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
