package Barros_FAI2641.Ejercicio2;

import java.util.Random;

public class Atleta implements Runnable{

    private Carrera carrera;
    private int posicion;

    Atleta(Carrera c,int p){
        this.carrera = c;
        this.posicion = p;
    }

    public void run(){
        correr();

    }

    private void correr() {
        String nombre = Thread.currentThread().getName();
        boolean corrio = false;
        try{
            System.out.println("El " + nombre + " esta esperando a correr en posicion "+this.posicion);
            do {
                //Intenta correr si es su turno [ESPERA ACTIVA]
                if (carrera.leToca(this.posicion)) {
                    carrera.correr();
                    //Simula el tiempo que tarda en correr
                    corriendo(nombre);
                    //Pasa el testigo al siguiente atleta
                    terminaCorrer(nombre);
                    corrio = true;
                } else
                    espera();
            }while(!corrio);

        }catch (InterruptedException e){}
    }

    private void terminaCorrer(String nombre) {
        //Termina de correr y le pasa el testigo al siguiente atleta
        System.out.println("El " + nombre + " termino de correr");
        carrera.pasaTestigo();
    }

    private void espera() throws InterruptedException {
        Thread.sleep(1000);
    }

    public void corriendo(String n) throws InterruptedException {
        Random r = new Random();
        int tiempo = (10+r.nextInt(3))*100;
        long ini = System.currentTimeMillis();
        System.out.println("El " + n + " esta corriendo");
        Thread.sleep(tiempo);
        long total = System.currentTimeMillis() - ini;
        System.out.println("El "+n+" tardo " + total +"ms.");

    }


}
