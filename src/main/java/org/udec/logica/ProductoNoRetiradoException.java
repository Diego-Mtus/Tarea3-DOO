package org.udec.logica;

/** Excepción que se lanza cuando un producto comprado no se ha retirado de expendedor*/
public class ProductoNoRetiradoException extends Exception {

    /** Constructor que inicializa la excepción con un mensaje predeterminado.
     * <p>
     * El mensaje es "producto no retirado.", indicando que no hay más productos.
     * </p> */
    public ProductoNoRetiradoException() {
        super("producto anterior no retirado.");
    }
}
