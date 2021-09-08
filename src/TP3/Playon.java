package TP3;

public class Playon {

    private Hamster plato;
    private Hamster rueda;
    private Hamster hamaca;

    Playon (){
        this.plato = null;
        this.hamaca = null;
        this.rueda = null;
    }

    public synchronized void setPlato(Hamster ham) throws InterruptedException{
        this.plato = ham;
        System.out.println(Thread.currentThread().getName() + " esta en el plato");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " realizo la siguiente actividad: " + ham.getEstado());
    }

    public synchronized void setRueda(Hamster ham) throws InterruptedException{
        this.rueda = ham;
        System.out.println(Thread.currentThread().getName() + " esta en la rueda");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " realizo la siguiente actividad: " + ham.getEstado());
    }

    public synchronized void setHamaca(Hamster ham) throws InterruptedException{
        this.hamaca = ham;
        System.out.println(Thread.currentThread().getName() + " esta en la hamaca");
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + " realizo la siguiente actividad: " + ham.getEstado());
    }
}
