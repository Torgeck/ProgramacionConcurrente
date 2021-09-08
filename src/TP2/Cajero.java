package TP2;

public class Cajero extends Thread {

    private String nombre;

    public Cajero(String nom) {
        this.nombre = nom;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void procesarCompra(Cliente cliente, long timeStamp) {
        System.out.println("El cajero " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " +
                cliente.getNombre() + " EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 + " seg");

        for(int i = 0; i < cliente.getCarroCompra().length; i++){
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesando el producto " + (i + 1) + "->Tiempo: "+
                    (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
        }

        System.out.println("El cajero " + this.nombre + " HA TERMINADO DE PROCESAR " + cliente.getNombre() +
                " EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 + " seg");
    }

    private void esperarXsegundos(int i) {
        try{
            Thread.sleep(i * 1000);
        }catch (InterruptedException exc){
            System.out.println("Le agarro un paro a la cajera");
        }
    }
}
