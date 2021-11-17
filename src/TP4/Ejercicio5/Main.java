package TP4.Ejercicio5;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        CentroImpresion centro = new CentroImpresion(5,5);

        Random r = new Random();
        char caracter;
        Thread[] hilos = new Thread[500];

        // Chequeo de tiempo
        /*
        long startTime = System.nanoTime();
        */

        for(int i=0; i<500; i++){
            caracter = (char)(r.nextInt(3)+67);
            hilos[i] = new Thread(new Usuario("Te amo profe", caracter, centro));
            hilos[i].start();
        }

        // Chequeo tiempo
        /*
        try{
            for(int i=0; i<50; i++){
                hilos[i].join();
            }
        }catch(InterruptedException e){
        }
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/ 1000000 );
        */
    }
}
