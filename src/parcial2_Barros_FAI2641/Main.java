package parcial2_Barros_FAI2641;

public class Main {

    public static void main(String[] args){
        /*int cantEmbo = 3;
        Planta p = new Planta(cantEmbo, 2);
        Thread[] embotelladores = new Thread[cantEmbo];
        Thread empaquetador = new Thread(new Empaquetador(p), "Empaquetador");
        Thread camion = new Thread(new Camion(p), "Camion");

        for(int i=0;i<cantEmbo;i++) {
            embotelladores[i] = new Thread(new Embotellador(p, i), "Embotellador " + i);
            embotelladores[i].start();
        }
        empaquetador.start();
        camion.start();

         */

        int cantClientes = 12;
        Salon s = new Salon();
        Thread[] clientes = new Thread[cantClientes];

        for (int i=0; i < cantClientes; i++){
            clientes[i] = new Thread(new Cliente(s), "Cliente " + i);
            clientes[i].start();
        }
    }
}
