package TP3.Ejercicio7;

public class Sumador implements Runnable {

    private Contador contador;
    private int sumaParcial;
    private int[] arr;
    private int indInicio;
    private int indFinal;

    Sumador(Contador c, int[] a, int ini, int fin){
        this.arr = a;
        this.contador = c;
        this.sumaParcial = 0;
        this.indInicio = ini;
        this.indFinal = fin;
    }

    public int sumaArreglo(){
        int fin = this.indFinal - indInicio, x = indInicio;
        for(int i=0; i <= fin; i++){
            sumaParcial += arr[x];
            x++;
        }
        return sumaParcial;
    }

    public void run(){
        sumaArreglo();
    }



}
