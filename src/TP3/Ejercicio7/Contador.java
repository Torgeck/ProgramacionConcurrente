package TP3.Ejercicio7;

public class Contador {

    int contador;

    Contador(){
        this.contador = 0;
    }

    public int getContador(){
        return this.contador;
    }

    public synchronized void sumarContador(int sumaParcial){
        this.contador += sumaParcial;
    }
}
