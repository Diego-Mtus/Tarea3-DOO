package org.udec.logica;

/** Excepción que se lanza cuando se intenta comprar algo sin elegir producto primero.*/
public class NoSeleccionException extends Exception {

    /** Constructor que inicializa la excepción con un mensaje predeterminado.
     * <p>
     * El mensaje es "no ha elegido producto.", indicando que{@link Producto} no ha sido elegido..
     * </p>*/
    public NoSeleccionException() {
        super("no ha elegido producto.");
    }
}
