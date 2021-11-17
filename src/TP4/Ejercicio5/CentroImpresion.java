package TP4.Ejercicio5;
import java.util.concurrent.*;

public class CentroImpresion {

    private Semaphore[] impresorasA;
    private Semaphore[] impresorasB;

    public CentroImpresion(int A, int B){
        this.impresorasA = new Semaphore[A];
        this.impresorasB = new Semaphore[B];
        for(int i=0; i<A; i++){
            impresorasA[i] = new Semaphore(1);
        }
        for(int i=0; i<B; i++){
            impresorasB[i] = new Semaphore(1);
        }
    }

    public void imprimir(char tipo, String cadena){
        switch(tipo){
            case 'A': imprimirA(cadena);break;
            case 'B': imprimirB(cadena);break;
            default: imprimirAmbas(cadena);break;
        }
    }

    public void imprimirA(String cadena){
        boolean exito = false;
        int i = 0;
        do{
            if(impresorasA[i].tryAcquire()){
                System.out.println("El usuario " + Thread.currentThread().getName() + " ha impreso en la impresora A"+i+": " + cadena);
                exito = true;
                impresorasA[i].release();
            }
            i++;
            if(i >= impresorasA.length){
                i = 0;
            }
        }while(!exito);
    }

    public void imprimirB(String cadena){
        boolean exito = false;
        int i = 0;
        do{
            if(impresorasB[i].tryAcquire()){
                System.out.println("El usuario " + Thread.currentThread().getName() + " ha impreso en la impresora B"+i+": " + cadena);
                exito = true;
                impresorasB[i].release();
            }
            i++;
            if(i >= impresorasB.length){
                i = 0;
            }
        }while(!exito);
    }

    public void imprimirAmbas(String cadena){
        boolean exito = false;
        int i = 0;
        int j = 0;
        do{
            if(impresorasA[i].tryAcquire()){
                System.out.println("El usuario " + Thread.currentThread().getName() + " ha impreso en la impresora A"+i+": " + cadena);
                exito = true;
                impresorasA[i].release();
            }else if(impresorasB[j].tryAcquire()){
                System.out.println("El usuario " + Thread.currentThread().getName() + " ha impreso en la impresora B"+i+": " + cadena);
                exito = true;
                impresorasB[j].release();
            }
            i++;
            j++;
            if(i >= impresorasA.length){
                i = 0;
            }
            if(j >= impresorasB.length){
                j = 0;
            }
        }while(!exito);
    }
}
