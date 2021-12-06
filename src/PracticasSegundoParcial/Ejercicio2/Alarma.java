package PracticasSegundoParcial.Ejercicio2;

public class Alarma implements Runnable{

    private Salon salon;

    public Alarma(Salon salon){
        this.salon = salon;
    }

    public void run(){
        try {
            while (true) {
                System.out.println("ESPERANDO A QUE SE LLENE EL SALON");
                salon.iniciarClase();
                System.out.println("SE INICIAN LAS ACTIVIDADES");
                Thread.sleep(5000);
                System.out.println("SE TERMINAN LAS ACTIVIDADES");
                salon.terminarClase();
            }
        }catch (InterruptedException e){
            System.out.println("Se murio la alarma");
        }
    }
}
