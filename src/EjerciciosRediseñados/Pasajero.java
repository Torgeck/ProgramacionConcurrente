package EjerciciosRediseñados;

public class Pasajero implements Runnable{

    private Tren tren;

    Pasajero(Tren tren){
        this.tren = tren;
    }

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();
        try {
            System.out.println("El "+nombre+" esta por comprar ticket 😎");
            tren.filaTicket();
            System.out.println("El " +nombre+" compro un ticket ahora esta esperando al tren");
            tren.subirTren();
            System.out.println("El " +nombre+" se subio al tren--------------------------");
            tren.bajarTren();
            System.out.println("El "+nombre +" se bajo del tren");
        }catch (Exception e){}
    }
}
