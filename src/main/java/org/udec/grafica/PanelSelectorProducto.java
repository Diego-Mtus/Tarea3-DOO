package org.udec.grafica;

import javax.swing.*;
import java.awt.*;

public class PanelSelectorProducto extends JPanel {

    private ButtonGroup grupoSeleccion;

    public PanelSelectorProducto(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);


        this.setVisible(true);
    }
}
