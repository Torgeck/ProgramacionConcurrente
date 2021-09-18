package TP3.Ejercicio3;

public class MiHilo implements Runnable{

    Thread hilo;
    static SumaMatriz sumaM = new SumaMatriz();
    int [] arr;
    int resp;

    //Contruye un nuevo hilo
    MiHilo(String nombre, int [] nums){
        hilo = new Thread(this,nombre);
        arr = nums;
    }

    public static MiHilo creaEInicia(String nombre, int nums[]){
        MiHilo miHilo = new MiHilo(nombre, nums);
        //Inicia el hilo
        miHilo.hilo.start();
        return miHilo;
    }

    public void run(){
        System.out.println(hilo.getName() + " iniciando.");
        resp = sumaM.sumMatriz(arr);
        System.out.println("Suma para "+hilo.getName()+" es "+resp);
        System.out.println(hilo.getName() + " terminado.");
    }
}
