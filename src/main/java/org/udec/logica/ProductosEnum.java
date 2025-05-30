package org.udec.logica;

/**
 * Enumeración que representa los productos disponibles, que incluyen tanto bebidas como dulces.
 * Cada producto tiene un índice único, un precio modificable y un nombre.
 * <p>
 * Productos existentes:
 * </p>
 * <ul>
 *     <li><strong>Bebidas:</strong> Coca-Cola, Sprite, Fanta.</li>
 *     <li><strong>Dulces:</strong> Snickers, Super8.</li>
 * </ul>
 *
 */

public enum ProductosEnum {

    // Bebidas
    /** Representa el producto Coca-Cola con un índice 1, un precio de 500 y el nombre "Coca-Cola". */
    COCACOLA("productoejemplo.png", 800, "Coca-Cola"),
    /** Representa el producto Fanta con un índice 3, un precio de 400 y el nombre "Fanta". */
    FANTA("productoejemplo2.png", 400, "Fanta"),
    /** Representa el producto Sprite con un índice 2, un precio de 500 y el nombre "Sprite". */
    SPRITE("productoejemplo.png", 500, "Sprite"),

    // Dulces
    /** Representa el producto Snickers con un índice 4, un precio de 500 y el nombre "Snickers". */
    SNICKERS("productoejemplo2.png", 500, "Snickers"),
    /** Representa el producto Super8 con un índice 5, un precio de 300 y el nombre "Super8". */
    SUPER8("productoejemplo.png", 300, "Super8");


    private final String nombreImagen;
    private int precio;
    private final String nombre;

    /** Constructor que inicializa valores de la enumeración.
     * @param nombreImagen El nombre de la imagen en resources.
     * @param precio El precio del producto.
     * @param nombre El nombre del producto.*/
    ProductosEnum(String nombreImagen, int precio, String nombre){
        this.nombreImagen = nombreImagen;
        this.precio = precio;
        this.nombre = nombre;
    }

    /**
     * Establece el precio del producto.
     * @param precio El nuevo precio del producto.
     */
    public void setPrecio(int precio){
        this.precio = precio;
    }

    /**
     * Obtiene el precio del producto.
     * @return El precio del producto.
     */
    public int getPrecio(){
        return this.precio;
    }

    /**
     * Obtiene el índice único del producto.
     * @return El índice único del producto.
     */
    public String getNombreImagen() {
        return this.nombreImagen;
    }

    /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre(){
        return this.nombre;
    }

}
