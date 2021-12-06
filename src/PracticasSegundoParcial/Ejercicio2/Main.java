package PracticasSegundoParcial.Ejercicio2;

public class Main {

    public static void main(String[] args) {
        int cantClientes = 24;
        Salon s = new Salon();
        Thread alarma = new Thread(new Alarma(s));
        Thread[] clientes = new Thread[cantClientes];

        alarma.start();

        for (int i = 0; i < cantClientes; i++) {
            clientes[i] = new Thread(new Cliente(s, i % 3), "Cliente " + i);
            clientes[i].start();
        }
    }
}
