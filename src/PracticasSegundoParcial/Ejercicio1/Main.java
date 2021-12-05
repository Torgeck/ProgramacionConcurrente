package PracticasSegundoParcial.Ejercicio1;

public class Main {

    public static void main(String[] args) {
        int tiempoMad = 10000,cantEmb = 5;
        Planta p = new Planta(cantEmb,tiempoMad);
        Thread camion = new Thread(new Camion(p));
        Thread empaquetador = new Thread(new Empaquetador(p));
        Thread embotellador[] = new Thread[cantEmb];

        camion.start();
        empaquetador.start();

        for (int i= 0; i < embotellador.length; i++){
            embotellador[i] = new Thread(new Embotellador(p,i) ,"Embotellador "+i);
            embotellador[i].start();
        }
    }
}
