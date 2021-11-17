package Barros_FAI2641.Ejercicio1;

public class Supervisor implements Runnable{

    private Parque parque;

    Supervisor(Parque p){
        this.parque = p;
    }

    public void run(){
        try {
            //Espera a que terminen de cruzar para dar los resultados
            parque.parqueToString();
        }catch (InterruptedException e){}
    }
}
