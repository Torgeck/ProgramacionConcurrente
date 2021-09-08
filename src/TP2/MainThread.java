package TP2;

public class MainThread {

    public static void main(String[] args) {

        Cliente cliente1 = new  Cliente("Cliente 1", new int[] {2,2,1,5,2,3});
        Cliente cliente2 = new  Cliente("Cliente 2", new int[] {1,3,5,1,1});
        // CajeroThread cajero1 = new CajeroThread("Cajero 1", cliente1);
        // CajeroThread cajero2 = new CajeroThread("Cajero 2", cliente2);

        CajeroRunnable cajero1 = new CajeroRunnable("cajero 1 ", cliente1);
        CajeroRunnable cajero2 = new CajeroRunnable("cajero 2 ", cliente2);

        //Se crean los threads
        Thread c1 = new Thread(cajero1);
        Thread c2 = new Thread(cajero2);

        //Inicio los threads
        c1.start();
        c2.start();
    }


}
