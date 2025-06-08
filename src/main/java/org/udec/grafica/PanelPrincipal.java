package org.udec.grafica;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.InputStream;


public class PanelPrincipal extends JPanel {

    private PanelComprador com;
    private PanelExpendedor exp;
    private Clip loop;

    // VARIABLES GLOBALES DE COLOR Y FUENTE
    public final static Color OSCURO = new Color(100, 40, 40);
    public final static Color CELESTE = new Color(140, 188, 185);
    public final static Color AMARILLO = new Color(221, 164, 72);
    public final static Color ROJO = new Color(187, 52, 47);
    public final static Font FUENTE_PERSONALIZADA = new Font("Dialog", Font.BOLD, 24);

    public PanelPrincipal () {

        this.setSize(1400, 800);
        this.setLayout(null); // Usamos posiciones absolutas

        exp = new PanelExpendedor();
        com = new PanelComprador(exp);
        try {
            InputStream sonidoStreamHover = getClass().getResourceAsStream("/loop.wav");
            if (sonidoStreamHover == null) {
                throw new IllegalArgumentException("Recurso no encontrado: /loop.wav");
            }
            AudioInputStream audioInputStreamLoop = AudioSystem.getAudioInputStream(new BufferedInputStream(sonidoStreamHover));

            loop = AudioSystem.getClip();
            loop.open(audioInputStreamLoop);

            FloatControl controlVolumenLoop = (FloatControl) loop.getControl(FloatControl.Type.MASTER_GAIN);
            controlVolumenLoop.setValue(-18.0f);

            loop.start();
            loop.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Agregamos los paneles
        this.add(exp);
        this.add(com);

        this.setVisible(true);

        // Se muestra mensaje de bienvenida después de que haya cargado panel principal.
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, "¡Bienvenido a nuestro expendedor de productos!\n" +
                            "Puedes ver tus monedas disponibles dejando el mouse encima de los botones de moneda.\n" +
                            "También puedes ver el número de serie de los productos de la misma forma.\n" +
                            "Además, si tienes suficientes monedas, puedes intercambiarlas por monedas de coste más alto:\n" +
                            "5 x 100 → 1 x 500, y 2 x 500 → 1 x 1000",
                    "Bienvenida", JOptionPane.PLAIN_MESSAGE);
        });

    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        com.paintComponent(g);
        exp.paintComponent(g);
    }
}
