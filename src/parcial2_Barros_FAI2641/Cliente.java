package parcial2_Barros_FAI2641;

import java.util.Random;

public class Cliente implements Runnable{

    private Salon s;
    private int actividad;

    public Cliente(Salon s){
        this.s = s;
        actividad = actividadRandom();
    }

    public void run(){
        String nombre = Thread.currentThread().getName();
        int i;
        try{
            while(true){
                System.out.println(nombre + " se quiere anotar a "+ actividad);
                i = s.anotarseActividad(actividad);
                System.out.println(nombre + " esta esperando a hacer la actividad: "+ i);
                s.hacerActividad(i);
                Thread.sleep(2000);
            }
        }catch(InterruptedException e){
            System.out.println("El cliente colapso "+ e);
        }
    }

    public static int actividadRandom(){
        //Devuelve una actividad del 0 al 2
        Random r = new Random();
        return r.nextInt(3);
    }
}
