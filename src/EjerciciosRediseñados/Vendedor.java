package EjerciciosRediseñados;

public class Vendedor implements Runnable{

    private Tren tren;

    Vendedor(Tren tren){
        this.tren = tren;
    }

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();
        try {
            while(true){
                System.out.println("El vendedor esta esperando clientes");
                tren.esperarCliente();
                Thread.sleep(500);
                System.out.println("El vendedor vendio un ticket");
                tren.venderTicket();
            }
        }catch (Exception e){}
    }
}
