package TP3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Orco implements Runnable{

    private int daño = 3;
    private Personaje personaje;

    public Orco(Personaje per){
        this.personaje = per;
    }

    public void run(){
        try {
            this.hacerDaño(this.personaje);
        }catch (InterruptedException exc){
            Logger.getLogger(Orco.class.getName()).log(Level.SEVERE,null,exc);
        }
    }

    private void hacerDaño(Personaje personaje) throws InterruptedException {

        for(int i=0; i < 5; i++) {

            System.out.println("Orco va a atacar por un total de: " + this.daño + " puntos");

            if (personaje.getVida() >= daño) {
                Thread.sleep(1000);
                personaje.dañar(daño);
                System.out.println("El total de vida de vida es de: " + personaje.getVida());
            } else {
                personaje.dañar(personaje.getVida());
                System.out.println("Has sido asesinado por orco, vida total: " + personaje.getVida());
                Thread.sleep(1000);
            }
        }
    }

}
