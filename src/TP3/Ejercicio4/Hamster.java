package TP3.Ejercicio4;

import java.util.Random;

public class Hamster implements Runnable{

    Playon playon;
    String estado;

    Hamster(Playon p){
    playon = p;
    estado = " dando vueltas";
    }

    public String getEstado() {
        return this.estado;
    }

    public void run(){

        for(int i=0; i<5; i++) {
            System.out.println(Thread.currentThread().getName() + " esta: " + this.getEstado());
            try {
                hacerActividad();
            } catch (InterruptedException exc) {
                System.out.println("Le agarro un paro al hamster");
            }
        }
    }

    private void hacerActividad() throws InterruptedException{
        Random random = new Random();
        int num = random.nextInt(3);

        System.out.println(Thread.currentThread().getName() + " va a realizar una actividad");
        switch (num) {
            case 0 -> comer();
            case 1 -> rueda();
            case 2 -> dormir();
        }
    }

    public void comer() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " va a comer");
        this.estado = "Comiendo";
        this.playon.setPlato(this);
        Thread.sleep(2000);
    }

    public void rueda() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " va a ejercitar");
        this.estado = "Ejercitando";
        this.playon.setRueda(this);
        Thread.sleep(3000);
    }

    public void dormir() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " va a dormir");
        this.estado = "Durmiendo";
        this.playon.setHamaca(this);
        Thread.sleep(5000);
    }
}
