package TP4.Ejercicio11;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Confiteria {

    private Semaphore servirComida = new Semaphore(0);
    private Semaphore comer = new Semaphore(0);
    private Semaphore beber = new Semaphore(0);
    private Semaphore servirBebida = new Semaphore(0);
    private Lock[] sillas;

    public Confiteria(int cantSillas) {
        sillas = new ReentrantLock[cantSillas];
        for(int i = 0; i < cantSillas; i++){
            sillas[i] = new ReentrantLock();
        }
    }

    // Empleado
    public int sentarse() {
        int sillaUsada = -1;
        int i = 0;

        do{
            if(sillas[i].tryLock()){
                sillaUsada = i;
            }
            i++;
        }while(sillaUsada == -1 && i < sillas.length);

        return sillaUsada;
    }

    public void comer() throws InterruptedException{
        servirComida.release();
        //Espera que le sirvan la comida
        comer.acquire();

    }
    public void beber() throws InterruptedException{
        servirBebida.release();
        // Espera que le sirvan la bebida
        beber.acquire();
        // Se pone a comer
    }
    public void liberarSilla(int sillaUsada){
        // Deja de comer y libera la silla
        sillas[sillaUsada].unlock();
    }

    // Mozo
    public void atenderMozo() throws InterruptedException{
        // Esta en su hobbie esperando atender
        servirBebida.acquire();
    }
    // ---- El mozo prepara la comida ----
    public void servirBebida(){
        // Le dice al empleado que beba
        beber.release();
    }

    //Cocinero
    public void atenderCocinero() throws InterruptedException{
        //Esta en su hobbie esperando atender
        servirComida.acquire();
    }
    public void servirComida() {
        //Le dice al empleado que coma
        comer.release();
    }
}
