package PracticasSegundoParcial.Ejercicio2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Cliente implements Runnable{

    private Salon salon;
    private int actividad1;
    private Queue <Integer> segundasActividades = new LinkedList<>();

    public Cliente(Salon salon, int actividad1) {
        this.salon = salon;
        this.actividad1 = actividad1;
        encolaActividades(actividad1);
    }

    public void encolaActividades(int actividad1){
        int max = 2, act;
        Random r = new Random();

        //Añade de manera aleatoria las otras actividades
        while(this.segundasActividades.size() < max){
            act = r.nextInt(3);

            if(act != actividad1 && !segundasActividades.contains(act))
                segundasActividades.add(act);
        }
        //Añade como ultima opcion la primer actividad
        segundasActividades.add(actividad1);
    }

    public void run(){
        try{
            String nombre = Thread.currentThread().getName();
            System.out.println("El "+nombre+ " va a realizar la actividad "+ actividad1);
            salon.hacerActvidad(actividad1);
            salon.salirActividad(actividad1);
            System.out.println("El "+nombre+ " va a realizar la actividad "+ segundasActividades.peek());
            this.actividad1 = salon.hacerSegundaActividad(this.segundasActividades);
            salon.salirActividad(actividad1);
            System.out.println("El "+nombre+ " se va a su casa");
        }catch (InterruptedException e) {
            System.out.println("Se murio el pibe");
        }
    }
}
