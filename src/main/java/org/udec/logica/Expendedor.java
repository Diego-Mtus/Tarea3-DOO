package org.udec.logica;

import java.util.ArrayList;

/**
 * Simulación de un expendedor con una cantidad finita de productos que se pueden comprar
*/
public class Expendedor {

    /** Depositos para gestionar las Coca-colas*/
    private Deposito<Producto> depositoCocaCola;
    /** Depositos para gestionar las Sprites*/
    private Deposito<Producto> depositoSprite;
    /** Depositos para gestionar las Fanta*/
    private Deposito<Producto> depositoFanta;
    /** Depositos para gestionar los Snickers*/
    private Deposito<Producto> depositoSnickers;
    /** Depositos para gestionar los Super 8*/
    private Deposito<Producto> depositoSuper8;
    /** Depositos para gestionar las monedas*/
    private Deposito<Moneda> depositoMonedas;
    /** Simula el depósito de retirar el producto comprado */
    private Producto depositoRetiro;

    private static int serieContador = 0;



    /** Constructor que crea los productos contenidos en cada depósito.
     * @param cantidad Cantidad de productos por cada depósito.
     */
    public Expendedor(int cantidad) {
        depositoCocaCola = new Deposito<Producto>();
        depositoSprite = new Deposito<Producto>();
        depositoFanta = new Deposito<Producto>();
        depositoSnickers = new Deposito<Producto>();
        depositoSuper8 = new Deposito<Producto>();
        depositoMonedas = new Deposito<Moneda>();
        for(int i = 0; i < cantidad; i++){
            depositoCocaCola.add(new CocaCola(100+serieContador));
            depositoSprite.add(new Sprite(200+serieContador));
            depositoFanta.add(new Fanta(300+serieContador));
            depositoSnickers.add(new Snickers(400+serieContador));
            depositoSuper8.add(new Super8(500+serieContador));

            // Esto nos garantiza que al renovar stock se mantenga el orden de la serie de productos.
            serieContador++;
        }

    }


    /** Método que recarga el stock de los productos del expendedor con
     * una generación de números de serie continua.
     *
     * @param cantidad Cantidad de productos que se quieren añadir
     * */
    public void recargarStock(int cantidad){
        for(int i = 0; i < cantidad; i++){
            depositoCocaCola.add(new CocaCola(100+serieContador));
            depositoSprite.add(new Sprite(200+serieContador));
            depositoFanta.add(new Fanta(300+serieContador));
            depositoSnickers.add(new Snickers(400+serieContador));
            depositoSuper8.add(new Super8(500+serieContador));
            serieContador++;
        }
    }

    /** Método que compra un producto del expendedor si se dan condiciones necesarias,
     * en caso de no cumplir con condiciones, se lanza una excepción.
     * @param m {@link Moneda} con la que se va a pagar el producto.
     * @param eleccion Elección del producto a comprar.
     * @throws PagoIncorrectoException Se lanza si se intenta pagar con una moneda null.
     * @throws NoHayProductoException Se lanza si ya no quedan más de ese producto en su correspondiente depósito.
     * @throws PagoInsuficienteException Se lanza si la moneda usada no es suficiente para comprar producto.
     * @throws NoSeleccionException Se lanza si se intenta comprar un producto null.
     * */
    public void comprarProducto(Moneda m, ProductosEnum eleccion) throws PagoIncorrectoException, NoHayProductoException, PagoInsuficienteException, NoSeleccionException{
        if(m == null){
            throw new PagoIncorrectoException();
        }

        if(eleccion == null){
            throw new NoSeleccionException();
        }

        if(m.getValor() < eleccion.getPrecio()){
            throw new PagoInsuficienteException();

        } else {
            Producto auxiliar;
            switch (eleccion){
                case COCACOLA -> {
                    auxiliar = depositoCocaCola.get();
                    if(auxiliar != null){
                        procesarMonedas(ProductosEnum.COCACOLA.getPrecio(), depositoMonedas, m);
                        depositoRetiro = auxiliar;
                    }
                }
                case FANTA -> {
                    auxiliar = depositoFanta.get();
                    if(auxiliar != null){
                        procesarMonedas(ProductosEnum.FANTA.getPrecio(), depositoMonedas, m);
                        depositoRetiro = auxiliar;
                    }
                }
                case SPRITE -> {
                    auxiliar = depositoSprite.get();
                    if(auxiliar != null){
                        procesarMonedas(ProductosEnum.SPRITE.getPrecio(), depositoMonedas, m);
                        depositoRetiro = auxiliar;
                    }
                }
                case SUPER8 -> {
                    auxiliar = depositoSuper8.get();
                    if(auxiliar != null){
                        procesarMonedas(ProductosEnum.SUPER8.getPrecio(), depositoMonedas, m);
                        depositoRetiro = auxiliar;
                    }
                }
                case SNICKERS -> {
                    auxiliar = depositoSnickers.get();
                    if(auxiliar != null){
                        procesarMonedas(ProductosEnum.SNICKERS.getPrecio(), depositoMonedas, m);
                        depositoRetiro = auxiliar;
                    }
                }
            }

            if(depositoRetiro == null){
                throw new NoHayProductoException();
            }
        }
    }

    /** Método usado dentro de {@code comprarProductos()} para gestionar el vuelto en monedas de 100 que se irán almacenando
     *  en el deposito de Monedas.
     * @param precioEnum Precio del producto que se quiere comprar.
     * @param depositoMoneda Depósito de monedas donde se almacenara el vuelto.
     * @param m Moneda con la que se quiere pagar el producto.
     * */
    private void procesarMonedas(int precioEnum, Deposito<Moneda> depositoMoneda, Moneda m){
        int auxPrecio = m.getValor() - precioEnum;
        while (auxPrecio > 0) {

            if(auxPrecio >= 1000){
                depositoMoneda.add(new Moneda1000());
                auxPrecio -= 1000;
            } else if(auxPrecio >= 500){
                depositoMoneda.add(new Moneda500());
                auxPrecio -= 500;
            } else if(auxPrecio >= 100){
                depositoMoneda.add(new Moneda100());
                auxPrecio -= 100;
            }

        }
    }

    /** Método para obtener el vuelto una vez que se haya comprado un producto.
     * Para un correcto uso, se debe implementar dentro de un ciclo para obtener todas las monedas.
     * @return Retorna la primera moneda almacenada en el deposito de monedas.
     * */
    public Moneda getVuelto(){
        return this.depositoMonedas.get();
    }


    /** Método que retira el producto y lo "elimina" del deposito.
     *
     * @return Retorta el producto del depósito especial.
     */
    public Producto getProducto(){
        Producto aux = depositoRetiro;
        depositoRetiro = null;
        return aux;
    }


    /** Método que entrega los depositos del expendedor para poder "ver" sus productos en LabelDepositoProductos
     * */
    public ArrayList<ArrayList<Producto>> getDepositos(){
        ArrayList<ArrayList<Producto>> depositos = new ArrayList<>();
        depositos.add(depositoCocaCola.getArrayList());
        depositos.add(depositoSprite.getArrayList());
        depositos.add(depositoFanta.getArrayList());
        depositos.add(depositoSnickers.getArrayList());
        depositos.add(depositoSuper8.getArrayList());
        return depositos;
    }

}
