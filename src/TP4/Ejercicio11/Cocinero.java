package TP4.Ejercicio11;

public class Cocinero implements Runnable{
    private Confiteria confiteria;
    public static final String verde = "\u001B[32m";
    public static final String reset = "\u001B[0m";

    public Cocinero(Confiteria confiteria){
        this.confiteria = confiteria;
    }

    public void run() {
        trabajar();
    }

    public void trabajar() {
        while (true) {
            try {
                System.out.println(verde+"El cocinero ahora esta en su hobbie."+reset);
                confiteria.atenderCocinero();
                // Atiende al empleado
                System.out.println(verde+"El cocinero atendió a un empleado."+reset);
                // Prepara el pollo y el cafe
                Thread.sleep(1000);
                // Le sirve la comida
                confiteria.servirComida(); // Sin bloqueo, libera al cliente para que coma
            } catch (Exception e) {
                System.out.println("F por el cocinero.");
            }
        }
    }
}
