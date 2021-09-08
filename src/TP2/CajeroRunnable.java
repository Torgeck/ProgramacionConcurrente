package TP2;

public class CajeroRunnable implements Runnable{

    private String nombre;
    private Cliente cliente;
    private long initialTime;

    public CajeroRunnable(String nombre,Cliente cliente) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.initialTime = System.currentTimeMillis();
    }



    public Cliente getCliente() {
        return cliente;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public void run(){
        System.out.println("El cajero " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
                + this.cliente.getNombre() + " EN EL TIEMPO: "
                + (System.currentTimeMillis() - this.initialTime) / 1000 + " seg");

        for(int i = 0; i < this.cliente.getCarroCompra().length; i++){
            this.esperarXsegundos(this.cliente.getCarroCompra()[i]);
            System.out.println("Procesando el producto " + (i + 1) + " ->Tiempo: "+
                    (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
        }

        System.out.println("El cajero " + this.nombre + " HA TERMINADO DE PROCESAR " + this.cliente.getNombre() +
                " EN EL TIEMPO: " + (System.currentTimeMillis() - this.initialTime) / 1000 + " seg");
    }

    private void esperarXsegundos(int i) {
        try{
            Thread.sleep(i * 1000);
        }catch (InterruptedException exc){
            System.out.println("Le agarro un paro a la cajera");
        }
    }

}
