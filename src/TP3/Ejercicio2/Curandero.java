package TP3.Ejercicio2;

import TP3.Ejercicio2.Orco;
import TP3.Ejercicio2.Personaje;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Curandero implements Runnable{

    private int cura = 3;
    private Personaje personaje;

    public Curandero(Personaje per){
        this.personaje = per;
    }

    public void run(){
        try {
            this.curarVida(this.personaje);
        }catch (InterruptedException exc){
            Logger.getLogger(Orco.class.getName()).log(Level.SEVERE,null,exc);
        }
    }

    private void curarVida(Personaje personaje) throws InterruptedException {

        for(int i = 0; i < 5 ; i++) {
            System.out.println("Curandero va a sanar por un total de: " + this.cura + " puntos");

            if (personaje.getVida() == 0) {
                Thread.sleep(1000);
                System.out.println("Has sido revivido por curandero");
                personaje.curar(cura);
                System.out.println("Tu total de vida es de: " + personaje.getVida());
            } else {
                Thread.sleep(1000);
                personaje.curar(cura);
                System.out.println("Has sido curado, tu vida total es de: " + personaje.getVida());
            }
        }
    }
}
