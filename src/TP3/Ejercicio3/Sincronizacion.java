package TP3.Ejercicio3;

import TP3.Ejercicio3.MiHilo;

public class Sincronizacion {

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5};
        MiHilo mh1 = MiHilo.creaEInicia("#1", arr);
        MiHilo mh2 = MiHilo.creaEInicia("#2", arr);

        /*try{
            mh1.hilo.join();
            mh2.hilo.join();
        }catch (InterruptedException exc){
            System.out.println("Hilo principal interrumpido");
        }*/
    }
}
