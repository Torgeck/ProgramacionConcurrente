package PracticasSegundoParcial.Ejercicio1;

import java.util.Random;

public class Embotellador implements Runnable{

    private int id;
    private Planta planta;
    private Random botella = new Random();

    public Embotellador(Planta planta, int id){
        this.planta = planta;
        this.id = id;
    }

    public void run(){
        boolean bot;
        String nombre = Thread.currentThread().getName();
        try{
            while(true){
                bot = botella.nextBoolean();
                System.out.println("El "+ nombre +" esta preparando una botella de vino: " + bot);
                planta.preparaBotella(this.id, bot);
                Thread.sleep(500);
                System.out.println("El "+ nombre +" agrego una botella a su caja. Era de vino: "+ bot);
                planta.cargaBotellas(this.id, bot);

            }
        }catch (InterruptedException e){
            System.out.println("El embotellador se quemo 🤑🤑🤑🤑 " + e.getMessage());
        }
    }
}
