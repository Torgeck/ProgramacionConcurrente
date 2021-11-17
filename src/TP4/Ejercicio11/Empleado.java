package TP4.Ejercicio11;

import java.util.Random;

public class Empleado implements Runnable{

    private Confiteria confiteria;

    public Empleado(Confiteria confiteria) {
        this.confiteria = confiteria;
    }

    public void run() {
        int sillaUsada = -1;
        try {
            do {
                sillaUsada = confiteria.sentarse();
                if (sillaUsada != -1) {
                    System.out.println(Thread.currentThread().getName() + " se sentó en la silla " + sillaUsada);
                    pedir();
                    confiteria.liberarSilla(sillaUsada);

                } else {
                    System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  "
                            + Thread.currentThread().getName() + " sigue trabajando.");
                    dormir(1000);
                }
            } while (sillaUsada == -1);
        } catch (Exception e) {
            System.out.println("F por el empleado x.");
        }
    }

    public void pedir() {

        switch (accionRandom()) {
            case 0:
                tomar();
                System.out.println(Thread.currentThread().getName() + " dejó de beber.");
                break;
            case 1:
                comer();
                System.out.println(Thread.currentThread().getName() + " dejó de comer.");
                break;
            case 2:
                tomar();
                comer();
                System.out.println(Thread.currentThread().getName() + " dejó de hacer ambas.");
        }
    }

    public void dormir(int i) {
        try {
            Thread.sleep(i);
        } catch (Exception e) {
        }
    }

    private void comer() {
        try {
            confiteria.comer();
        } catch (Exception e) {
        }
        System.out.println(Thread.currentThread().getName() + " está comiendo.");
        dormir(3000);
    }

    private void tomar() {
        try {
            confiteria.beber();
        } catch (Exception e) {
        }
        System.out.println(Thread.currentThread().getName() + " está bebiendo.");
        dormir(1200);
    }

    private int accionRandom() {
        Random rand = new Random();
        return rand.nextInt(3);
    }
}
