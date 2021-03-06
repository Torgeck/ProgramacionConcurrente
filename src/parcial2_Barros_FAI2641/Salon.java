package parcial2_Barros_FAI2641;

import java.util.concurrent.Semaphore;

public class Salon {

    private final int CUPO = 4;
    private final int ACTIVIDADES = 3;
    private final int CAPACIDAD_SALON = 12;
    private Semaphore[] actividad = new Semaphore[ACTIVIDADES];    //Capacidad actual de cada actividad
    private Semaphore comienzoActividad = new Semaphore(0);       //Se usa para realizar la actividad
    private Semaphore mutex = new Semaphore(1);
    private int turno;
    private int capActActividad;

    public Salon(){
        //Inicializa los actividades con 4 cupos cada una
        turno = 0;
        capActActividad = 0;
        for (int i = 0; i < 3; i++){
            actividad[i] = new Semaphore(CUPO);
        }
    }

    //Cliente------------
    public int anotarseActividad(int tipoActividad) throws InterruptedException {
        //Elije una actividad si esta libre retorna true y espera a que se ocupen las demas
        boolean libre = switch (tipoActividad) {
            case 0 -> actividad[0].tryAcquire();
            case 1 -> actividad[1].tryAcquire();
            case 2 -> actividad[2].tryAcquire();
            default -> false;
        };
        //Si pudo anotarse se aumenta la capacidad, si no intenta con otra actividad
        if(libre){
            mutex.acquire();
            capActActividad++;
            mutex.release();
        }
        else
            anotarseActividad((tipoActividad+1)%3);
        return tipoActividad;
    }

    public void hacerActividad(int act) throws InterruptedException {
        mutex.acquire();
        //Si se llena el salon avisa que se pueden comenzar a hacer las actividades
        if(capActActividad == CAPACIDAD_SALON) {
            System.out.println("COMIENZA ACTIVIDAD TURNO "+ turno);
            comienzoActividad.release(CAPACIDAD_SALON);
            turno++;
        }
        mutex.release();

        comienzoActividad.acquire();
        actividad[act].release();
    }


}
