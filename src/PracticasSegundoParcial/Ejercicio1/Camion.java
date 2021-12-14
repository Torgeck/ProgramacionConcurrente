package PracticasSegundoParcial.Ejercicio1;

public class Camion implements Runnable{

    private Planta planta;
    private final String COLOR = "\033[1;34m";
    private final String RESET = "\033[0m";

    public Camion(Planta planta){
        this.planta = planta;
    }

    public void run(){
        try{
            while(true){
                System.out.println(COLOR + "El camion esta esperando en el almacen" + RESET);
                planta.inspeccionAlmacen();
                Thread.sleep(5000);
                planta.cargaRepartoCajas();
                System.out.println(COLOR + "El camion sale a repartir" + RESET);
                Thread.sleep(5000);
                System.out.println(COLOR+"El camion volvio de repartir"+RESET);
            }
        }catch (InterruptedException e){
            System.out.println(COLOR+"El camion choco " + e.getMessage() + RESET);
        }
    }
}
