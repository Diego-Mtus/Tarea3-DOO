package org.udec.grafica;

import javax.swing.*;

public class PanelSelectorProducto extends JPanel {

    public PanelSelectorProducto(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new JButton("test1"));
        this.add(new JButton("test2"));
        this.setVisible(true);
    }
}
