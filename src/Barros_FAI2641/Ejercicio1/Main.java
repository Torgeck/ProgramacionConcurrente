package Barros_FAI2641.Ejercicio1;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int cantidadBabuinos = 10;
        Parque parque = new Parque(cantidadBabuinos);
        Thread[] babuinos = new Thread[cantidadBabuinos];
        Thread supervisor = new Thread(new Supervisor(parque));
        supervisor.start();

        System.out.println("En el parque hay "+ parque.getCantBabuinos() +" de Babuinos");

        creaIniciaBabuinos(cantidadBabuinos, parque, babuinos);
    }

    private static void creaIniciaBabuinos(int cantidadBabuinos, Parque parque, Thread[] babuinos) {
        for(int i = 0; i < cantidadBabuinos; i++){
            char lado = ladoRandom();
            babuinos[i] = new Thread(new Babuino(lado, parque), "Babuino " + i);
            babuinos[i].start();
        }
    }

    public static char ladoRandom(){
        //Retorna un lado random
        Random r = new Random();
        char lado = 'D';
        if(r.nextInt(2) == 0 )
            lado = 'I';

        return lado;
    }
}
