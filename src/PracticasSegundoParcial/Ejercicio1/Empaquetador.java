package PracticasSegundoParcial.Ejercicio1;

public class Empaquetador implements Runnable{

    private Planta planta;

    public Empaquetador(Planta planta){
        this.planta = planta;
    }

    public void run() {
        int idEmbotellador;
        Caja cajaActual;
        try {
            while (true) {

                System.out.println("El empaquetador esta esperando una caja llena");
                idEmbotellador = planta.preparaCaja();
                System.out.println("El empaquetador saco la caja a embotellador " + idEmbotellador);
                cajaActual = planta.guardarCaja(idEmbotellador);
                System.out.println("El empaquetador guardo la caja en el almacen");
                Thread.sleep(1000);
                planta.reponeCaja(idEmbotellador,cajaActual);
                System.out.println("El empaquetador repuso la caja al embotellador " + idEmbotellador);

            }
        } catch (InterruptedException e) {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + e.getMessage());
        }
    }
}
