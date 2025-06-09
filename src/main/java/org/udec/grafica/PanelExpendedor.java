package org.udec.grafica;

import org.udec.logica.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Clase que representa el panel de interacción con un expendedor de productos.
 * Está diseñada para mostrar visualmente los diferentes depósitos de productos,
 * actualizar el stock en tiempo real y manejar las selecciones realizadas por el usuario.
 */
public class PanelExpendedor extends JPanel {

    private Expendedor expendedor;
    private LabelDepositoProducto ldpCoca;
    private LabelDepositoProducto ldpSprite;
    private LabelDepositoProducto ldpFanta;
    private LabelDepositoProducto ldpSnickers;
    private LabelDepositoProducto ldpSuper8;
    private ArrayList<ArrayList<Producto>> depositos;
    private int[] stockIndividual;
    private LabelInformacion labelInformativo;

    private ProductosEnum seleccion;

    public ImageIcon[] imagenesProductos;
    private ImageIcon imagenExpendedor;

    private int cantidadProductos = LabelDepositoProducto.MAX_PRODUCTOS; // ESTA VARIABLE CONTROLA LA CANTIDAD INICIAL.

    public PanelExpendedor() {
        this.setBackground(PanelPrincipal.CELESTE);
        this.setSize(1000, 800);
        this.setLayout(null);

        precargarImagenesProductos();
        expendedor = new Expendedor(cantidadProductos);
        stockIndividual = new int[5];
        for(int i = 0; i < 5; i++){
            stockIndividual[i] = cantidadProductos;
        }

        depositos = expendedor.getDepositos();

        // Sección de inicializar depositos de expendedores

        // Distancia de separacion en x entre cada ldp de momento 120
        ldpCoca = new LabelDepositoProducto(120, 140);
        ldpSprite = new LabelDepositoProducto(240, 140);
        ldpFanta = new LabelDepositoProducto(360, 140);
        ldpSnickers = new LabelDepositoProducto(480, 140);
        ldpSuper8 = new LabelDepositoProducto(600, 140);
        for(int i = 0; i < cantidadProductos; i++) {
            ldpCoca.addProducto(new LabelProducto(depositos.get(0).get(i), imagenesProductos[0]));
            ldpSprite.addProducto(new LabelProducto(depositos.get(1).get(i), imagenesProductos[1]));
            ldpFanta.addProducto(new LabelProducto(depositos.get(2).get(i), imagenesProductos[2]));
            ldpSnickers.addProducto(new LabelProducto(depositos.get(3).get(i), imagenesProductos[3]));
            ldpSuper8.addProducto(new LabelProducto(depositos.get(4).get(i), imagenesProductos[4]));
        }

        this.add(ldpCoca);
        this.add(ldpSprite);
        this.add(ldpFanta);
        this.add(ldpSnickers);
        this.add(ldpSuper8);

        // - Fin de sección de inicializar depositos de expendedores

        // Label que muestra la información de producto seleccionado
        labelInformativo = new LabelInformacion(160, 64);
        this.add(labelInformativo);


        // Panel que maneja los botones de selección
        PanelSelectorProducto pselector = new PanelSelectorProducto(this);
        pselector.setBounds(734, 254, 120,600);
        this.add(pselector);

        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenExpendedor.getImage(), 50, 28, imagenExpendedor.getIconWidth()+1, imagenExpendedor.getIconHeight(), this);
    }


    /**
     * Llama al método actualizarValores() del labelInfomativo.
     *
     * @param producto El producto a actualizar, de tipo ProductosEnum.
     * @param stock La cantidad de stock disponible para el producto.
     */
    public void actualizarValores(ProductosEnum producto, int stock){
        labelInformativo.actualizarValores(producto, stock);
    }

    /**
     * Actualiza la interfaz gráfica para reflejar la recarga de stock e información.
     *<p>
     * Este método realiza dos acciones principales:
     * </p>
     * 1. Llama al método mostrarRenovacion() de labelInformativo para
     *    notificar al usuario que los depósitos vacíos han sido repuestos.
     * <p>
     * 2. Repinta las áreas específicas de la interfaz correspondientes a cada
     *    depósito de producto, asegurando que los cambios sean visibles en pantalla.
     * </p>
     */
    public void mostrarRenovacion(){
        labelInformativo.mostrarRenovacion();
        repaint(ldpCoca.getX(), ldpCoca.getY(), ldpCoca.getWidth(), ldpCoca.getHeight());
        repaint(ldpSprite.getX(), ldpSprite.getY(), ldpSprite.getWidth(), ldpSprite.getHeight());
        repaint(ldpFanta.getX(), ldpFanta.getY(), ldpFanta.getWidth(), ldpFanta.getHeight());
        repaint(ldpSnickers.getX(), ldpSnickers.getY(), ldpSnickers.getWidth(), ldpSnickers.getHeight());
        repaint(ldpSuper8.getX(), ldpSuper8.getY(), ldpSuper8.getWidth(), ldpSuper8.getHeight());
    }

    private void precargarImagenesProductos(){
        try {
            imagenesProductos = new ImageIcon[ProductosEnum.values().length];
            for(ProductosEnum prod : ProductosEnum.values()){
                imagenesProductos[prod.ordinal()] = new ImageIcon(getClass().getResource("/" + prod.getNombreImagen()));
            }
            imagenExpendedor = new ImageIcon(getClass().getResource("/expendedor.png"));
        } catch (Exception e) {
            System.err.println("Error al cargar imagenes de productos");
        }
    }



    /**
     * Método que verifica los depósitos de productos en el expendedor y recarga aquellos
     * que se encuentren vacíos, actualizando el stock y la interfaz gráfica del panel.
     */
    public void actualizarStock() {

        ArrayList<ArrayList<Producto>> depositosRevisarVacio = expendedor.getDepositos();

        for(int i = 0; i < 5; i++){
            if (depositosRevisarVacio.get(i).isEmpty()){
                // Se recarga si estaba vacío
                expendedor.recargarStock(cantidadProductos, ProductosEnum.values()[i]);
                stockIndividual[i] = cantidadProductos;

                // Auxiliar para ir añadiendo los productos nuevos del expendedor al label de depósito.
                ArrayList<ArrayList<Producto>> depositosAuxiliar = expendedor.getDepositos();
                for(Producto prod: depositosAuxiliar.get(i)){
                    switch (ProductosEnum.values()[i]){
                        case COCACOLA:
                            ldpCoca.addProducto(new LabelProducto(prod, imagenesProductos[0]));
                            break;
                        case SPRITE:
                            ldpSprite.addProducto(new LabelProducto(prod, imagenesProductos[1]));
                            break;
                        case FANTA:
                            ldpFanta.addProducto(new LabelProducto(prod, imagenesProductos[2]));
                            break;
                        case SNICKERS:
                            ldpSnickers.addProducto(new LabelProducto(prod, imagenesProductos[3]));
                            break;
                        case SUPER8:
                            ldpSuper8.addProducto(new LabelProducto(prod, imagenesProductos[4]));
                            break;
                    }
                }

            }
        }
    }

    /**
     * Establece el producto seleccionado en el panel.
     *
     * @param seleccion El producto a seleccionar, de tipo ProductosEnum.
     */
    // Para ser usado en PanelSelectorProducto
    public void setSeleccion(ProductosEnum seleccion) {
        this.seleccion = seleccion;
    }

    /**
     * Obtiene el producto actualmente seleccionado en el panel expendedor.
     *
     * @return El producto seleccionado, de tipo ProductosEnum.
     */
    public ProductosEnum getSeleccion(){
        return seleccion;
    }

    /**
     * Devuelve la cantidad de stock disponible para un producto específico.
     *
     * @param i El índice del producto para el cual se desea obtener el stock.
     * @return La cantidad de stock disponible para el producto indicado por el índice.
     */
    public int getStockIndividual(int i){
        return stockIndividual[i];
    }


    /**
     * Devuelve el objeto Expendedor asociado al panel.
     *
     * @return el objeto Expendedor que representa el expendedor en el panel.
     */
    public Expendedor getExpendedor(){
        return expendedor;
    }

    /**
     * Elimina un producto del inventario y actualiza la interfaz gráfica del depósito correspondiente
     * basado en el último producto seleccionado.
     */
    public void removeProducto(){
        switch (seleccion){
            case COCACOLA:
                stockIndividual[0]--;
                ldpCoca.removeProducto();
                repaint(ldpCoca.getX(), ldpCoca.getY(), ldpCoca.getWidth(), ldpCoca.getHeight());
                break;
            case FANTA:
                stockIndividual[2]--;
                ldpFanta.removeProducto();
                repaint(ldpFanta.getX(), ldpFanta.getY(), ldpFanta.getWidth(), ldpFanta.getHeight());
                break;
            case SPRITE:
                stockIndividual[1]--;
                ldpSprite.removeProducto();
                repaint(ldpSprite.getX(), ldpSprite.getY(), ldpSprite.getWidth(), ldpSprite.getHeight());
                break;
            case SNICKERS:
                stockIndividual[3]--;
                ldpSnickers.removeProducto();
                repaint(ldpSnickers.getX(), ldpSnickers.getY(), ldpSnickers.getWidth(), ldpSnickers.getHeight());
                break;
            case SUPER8:
                stockIndividual[4]--;
                ldpSuper8.removeProducto();
                repaint(ldpSuper8.getX(), ldpSuper8.getY(), ldpSuper8.getWidth(), ldpSuper8.getHeight());
                break;
        }
    }

}
