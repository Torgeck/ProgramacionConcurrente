package EjerciciosRediseñados;

public class Main {

    public static void main(String[] args) {
        int capacidadMax = 10;
        int cantPasajeros = capacidadMax * 3;
        Tren tren = new Tren();
        Thread vendedor = new Thread(new Vendedor(tren));
        Thread control = new Thread(new ControlTren(tren));
        Thread[] pasajeros = new Thread[cantPasajeros];
        vendedor.start();
        control.start();
        for (int i = 0; i < cantPasajeros; i++) {
            pasajeros[i] = new Thread(new Pasajero(tren), "Pasajero" + i);
            pasajeros[i].start();
        }
    }
}
