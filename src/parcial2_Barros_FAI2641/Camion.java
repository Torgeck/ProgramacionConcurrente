package parcial2_Barros_FAI2641;

public class Camion implements Runnable{

    private Planta p;

    public Camion(Planta p){
        this.p = p;
    }

    @Override
    public void run() {
        int tiempo = 0;
        try{
            while(true){
                System.out.println("Camion esperando a que se llene el almacen");
                tiempo = p.liberarAlmacen();
                if(tiempo != 0){
                    System.out.println("Camion esperando maduracion 🍇🍇");
                    Thread.sleep(tiempo);
                }
                p.repartir();
                System.out.println("Camion salio a repartir🚚🚚");
                Thread.sleep(1000);
                System.out.println("Camion esta volviendo de repartir");
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
            System.out.println("Camion colapso "+ e);
        }
    }
}

