package Barros_FAI2641.Ejercicio2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Carrera {

    private Lock testigo = new ReentrantLock();             //Representa el testigo
    private Lock mutex = new ReentrantLock();               //Se usa para proteger la modificacion de posicionActual
    private int cantCorredores;                             //Se usa para saber la cantidad total de corredores
    private int posicionActual;                           //Representa la posicion actual del testigo

    Carrera(int c){
        this.cantCorredores = c;
        this.posicionActual = 0;
    }

    public void correr(){
        testigo.lock();
    }

    public boolean leToca(int posAtleta){
        return posicionActual == posAtleta;
    }

    public void pasaTestigo(){
        //Aumenta la posicion y pasa el testigo
        aumentaPos();
        testigo.unlock();
    }

    private void aumentaPos(){
        try {
            mutex.lock();
            posicionActual++;
        }
        finally {
            mutex.unlock();
        }
    }




}
