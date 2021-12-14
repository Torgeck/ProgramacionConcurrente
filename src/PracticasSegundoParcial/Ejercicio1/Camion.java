package PracticasSegundoParcial.Ejercicio1;

public class Camion implements Runnable{

    private Planta planta;

    public Camion(Planta planta){
        this.planta = planta;
    }

    public void run(){
        try{
            while(true){
                System.out.println("El camion esta esperando en el almacen------------------");
                planta.inspeccionAlmacen();
                Thread.sleep(5000);
                planta.cargaRepartoCajas();
                System.out.println("El camion sale a repartir ----------------");
                Thread.sleep(5000);
                System.out.println("El camion volvio de repartir----------");
            }
        }catch (InterruptedException e){
            System.out.println("El camion choco " + e.getMessage());
        }
    }
}
