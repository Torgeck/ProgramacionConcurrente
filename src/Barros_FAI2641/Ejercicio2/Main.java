package Barros_FAI2641.Ejercicio2;

public class Main {

    public static void main(String[] args) {

        int cantCorredores = 4;
        Carrera carrera = new Carrera(cantCorredores);
        Thread[] atletas = new Thread[cantCorredores];

        for(int i = 0; i < cantCorredores; i++){
            atletas[i] = new Thread(new Atleta(carrera, i), "Atleta " + i);
            atletas[i].start();
        }
    }
}
