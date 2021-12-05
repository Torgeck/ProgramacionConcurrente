package parcial2_Barros_FAI2641;

public class Empaquetador implements Runnable{

    private Planta p;

    public Empaquetador(Planta p){
        this.p = p;
    }

    @Override
    public void run() {
        int caja;
        try{
            while(true){
                System.out.println("Esta esperando a que se llene una caja");
                caja = p.retirarCaja();
                Thread.sleep(1000);
                System.out.println("Repone caja "+ caja);
                p.reponerCaja(caja);
            }
        }catch(InterruptedException e){
            System.out.println("Brazo colapso "+ e);
        }
    }
}
