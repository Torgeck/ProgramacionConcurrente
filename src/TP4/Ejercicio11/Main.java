package TP4.Ejercicio11;

public class Main {
    public static void main(String[] args){
        int cantEmpleados = 6;
        Confiteria confiteria = new Confiteria(2);
        Thread[] empleados = new Thread[cantEmpleados];
        Thread mozo = new Thread(new Mozo(confiteria));
        Thread cocinero = new Thread(new Cocinero(confiteria));
        mozo.start();
        cocinero.start();

        for(int i = 0; i < cantEmpleados; i++){
            empleados[i] = new Thread(new Empleado(confiteria), "Empleado " + i);
            empleados[i].start();
        }
    }
}
