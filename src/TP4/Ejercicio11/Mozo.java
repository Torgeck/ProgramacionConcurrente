package TP4.Ejercicio11;

public class Mozo implements Runnable{
    private Confiteria confiteria;

    public static final String rojo = "\u001B[31m";
    public static final String reset = "\u001B[0m";

    Mozo(Confiteria conf){
        this.confiteria = conf;
    }

    public void run(){
        trabajar();
    }

    public void trabajar(){
        while(true){
            try{
                System.out.println(rojo+"El mozo ahora esta en su hobbie."+reset);
                confiteria.atenderMozo(); // Se bloquea esperando a que un cliente entre
                //Atiende al empleado
                System.out.println(rojo+"El mozo atendió a un empleado."+reset);
                //Prepara el pollo y el cafe
                Thread.sleep(1000);
                //Le sirve la comida
                confiteria.servirBebida(); // Sin bloqueo, libera al cliente para que coma
            }catch(Exception e){
                System.out.println("F por el mozo.");
            }
        }
    }
}
