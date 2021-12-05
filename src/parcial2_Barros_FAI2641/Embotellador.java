package parcial2_Barros_FAI2641;

import java.util.Random;

public class Embotellador implements Runnable{

    private Planta p;
    private int n;      //Numero del embotellador
    private boolean v;  //Variable para saber si empaqueta o no vino

    Embotellador(Planta p, int n){
        this.p = p;
        this.n = n;
        this.v = randomVino();
    }

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();
        int i = 0;
        try{
            while(true){
                //Cada vez que llena una caja se resetea que va a embotellar aleatoriamente
                while(i != 9) {
                    System.out.println(nombre + " Prepara una botella de vino?:" + v);
                    p.prepararBotella(this.n, this.v);
                    Thread.sleep(500);
                    System.out.println(nombre + " Guarda una botella ");
                    p.guardarBotella(this.n);
                }
                this.v = randomVino();
            }
        }catch(InterruptedException e){
            System.out.println("Embotellador colapso "+ e);
        }
    }

    private static boolean randomVino(){
        //Metodo que retorna un bolean, true para empaquetar vino, false para empaquetar agua
        Random r = new Random();
        return r.nextInt(2) == 0;
    }
}

