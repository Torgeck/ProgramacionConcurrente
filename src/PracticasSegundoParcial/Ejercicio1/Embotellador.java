package PracticasSegundoParcial.Ejercicio1;

public class Embotellador implements Runnable{

    private int id;
    private Planta planta;

    public Embotellador(Planta planta, int id){
        this.planta = planta;
        this.id = id;
    }

    public void run(){
        boolean bot;
        String nombre = Thread.currentThread().getName();
        try{
            while(true){
                bot = planta.preparaCargaBotella(this.id);
                System.out.println("El "+ nombre +" esta preparando una botella de "+ (bot ? "vino":"gaseosa"));
                Thread.sleep(500);
                System.out.println("El "+ nombre +" agrego una botella a su caja. Era de " + (bot ? "vino":"gaseosa"));
                planta.verificaEstadoCaja(this.id, bot);

            }
        }catch (InterruptedException e){
            System.out.println("El "+ nombre +" se quemo "+ e.getMessage());
        }
    }
}
