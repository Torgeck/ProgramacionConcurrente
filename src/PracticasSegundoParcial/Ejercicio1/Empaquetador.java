package PracticasSegundoParcial.Ejercicio1;

public class Empaquetador implements Runnable{

    private Planta planta;
    private final String COLOR = "\033[1;32m";
    private final String RESET = "\033[0m";


    public Empaquetador(Planta planta){
        this.planta = planta;
    }

    public void run() {
        int idEmbotellador;
        Caja cajaActual;
        try {
            while (true) {

                System.out.println(COLOR + "El Empaquetador esta esperando una caja llena" + RESET);
                idEmbotellador = planta.preparaCaja();
                System.out.println(COLOR +"El Empaquetador saco la caja a Embotellador " + idEmbotellador + RESET);
                cajaActual = planta.guardarCaja(idEmbotellador);
                System.out.println(COLOR +"El Empaquetador guardo la caja en el almacen" + RESET);
                Thread.sleep(1000);
                planta.reponeCaja(idEmbotellador,cajaActual);
                System.out.println(COLOR +"El Empaquetador repuso la caja al embotellador " + idEmbotellador + RESET);

            }
        } catch (InterruptedException e) {
            System.out.println(COLOR + "El empaquetador se quemo " + e.getMessage() + RESET);
        }
    }
}
