package TP2;

public class CajeroThread extends Thread{

    private String nombre;
    private Cliente cliente;
    private long initialTime;

    public CajeroThread(String nom, Cliente cliente){
        super (nom);
        this.cliente = cliente;
        this.initialTime = System.currentTimeMillis();
    }

    public String getNombre() {
        return nombre;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public long getInitialTime() {
        return initialTime;
    }

    //Implementar metodo run()

    public void run() {
        System.out.println("El cajero " + getName() + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
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
