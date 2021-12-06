package PracticasSegundoParcial.Ejercicio2;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Salon {

    private final int CUPO_ACTIVDAD = 4;
    private final int CAPACIDAD_SALON = 12;
    private final int ACTIVIDADES = 3;
    private int turno;
    private int capActual;
    private Semaphore actividad[] = new Semaphore[ACTIVIDADES];
    private Semaphore iniciarClase = new Semaphore(0);
    private Semaphore terminarClase = new Semaphore(0);
    private Semaphore mutexCliente = new Semaphore(1);

    public Salon(){
        //Inicializa los semaforos para las cuatro actividades
        this.turno = 0;
        this.capActual = 0;
        for(int i=0; i < ACTIVIDADES; i++){
            actividad[i] = new Semaphore(CUPO_ACTIVDAD);
        }
    }

    //Cliente
    public void hacerActvidad(int act) throws InterruptedException {
        //Entra a su actividad si es que hay lugar
        actividad[act].acquire();

        //Espera a que esten todos en su actividad
        iniciarClase.release();
    }

    public int hacerSegundaActividad(Queue actividadPlaneada) throws InterruptedException{
        mutexCliente.acquire();
        boolean entro = false;
        int actividadRealizada = 0, i;

        while(!entro){
            i = (int) actividadPlaneada.remove();
            if(actividad[i].tryAcquire()){
                actividadRealizada = i;
                entro = true;
            }
            actividadPlaneada.add(i);
        }

        mutexCliente.release();

        iniciarClase.release();

        return actividadRealizada;
    }

    public void salirActividad(int act) throws InterruptedException {
        terminarClase.acquire();
        actividad[act].release();
        System.out.println(Thread.currentThread().getName() + " salio de la actividad "+ act);
    }



    //Alarma
    public void iniciarClase() throws InterruptedException{
        System.out.println("-------------TURNO "+ turno);
        iniciarClase.acquire(CAPACIDAD_SALON);
    }

    public void terminarClase(){
        turno++;
        terminarClase.release(CAPACIDAD_SALON);
    }
}
