package EjerciciosRediseñados;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Tren {
    private CyclicBarrier barreraEntrar = new CyclicBarrier(10);
    private CyclicBarrier barreraSalir = new CyclicBarrier(10);
    private Semaphore mutex = new Semaphore(1);
    private Semaphore vendedor = new Semaphore(1);
    private Semaphore ticket = new Semaphore(0,true);
    private Semaphore prenderTren = new Semaphore(0);
    private Semaphore vaciarTren = new Semaphore(0);
    private int asientos;

    public Tren (){
        asientos = 0;
    }

    // Pasajero

    public void filaTicket() throws InterruptedException{
        ticket.acquire();
        vendedor.release();
    }

    public void subirTren() throws InterruptedException, BrokenBarrierException {
        //Espera a que se le abran las puertas del tren
        barreraEntrar.await();
        //Ocupa un asiento
        mutex.acquire();
        asientos++;
        mutex.release();
        //El ultimo avisa al tren
        if(asientos == 10)
            prenderTren.release();

    }

    public void bajarTren() throws InterruptedException, BrokenBarrierException {
        vaciarTren.acquire();
        //Espera a que se detenga el tren
        barreraSalir.await();
    }

    // Vendedor
    public void esperarCliente() throws InterruptedException{
        vendedor.acquire();
    }

    public void venderTicket(){
        ticket.release();
    }

    // Control Tren
    public void viajar() throws InterruptedException{
        prenderTren.acquire();
        asientos = 0;
    }

    public void destino() throws InterruptedException{
        vaciarTren.release(10);
    }

}
