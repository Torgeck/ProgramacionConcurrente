package EjerciciosRediseñados;

public class ControlTren implements Runnable{

    private Tren tren;

    public ControlTren(Tren tren){
        this.tren = tren;
    }

    @Override
    public void run() {
        try{
            while (true) {
                System.out.println("El tren esta esperando pasajeros====================");
                tren.viajar();
                System.out.println("El tren esta viajando=======================");
                Thread.sleep(3000);
                tren.destino();
            }
        }catch (Exception e){}
    }
}
