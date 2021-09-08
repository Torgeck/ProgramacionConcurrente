package TP2;

public class MiHilo extends Thread{

    public MiHilo(String nombre){
        super(nombre);
    }

    public void run(){
        System.out.println("Comenzando "+ getName());
        for(int contar=0; contar<10; contar++){
            try {
                Thread.sleep(400);
                System.out.println("En "+ getName() + ", el recuento "+ contar);
            } catch (InterruptedException exc) {
                System.out.println(getName() + " Interrumpido");
            }
        }
        System.out.println("Terminado "+ getName());
    }
}
