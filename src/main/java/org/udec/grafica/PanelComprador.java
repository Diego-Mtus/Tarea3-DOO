package org.udec.grafica;

import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {
    public PanelComprador () {
        this.setBackground(Color.YELLOW);
        this.setSize(400, 900);
        this.setBounds(1200, 0, 400, 900);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
