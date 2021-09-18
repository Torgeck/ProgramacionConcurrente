package TP3.Ejercicio4;

public class Playon {

    private Hamster plato;
    private Hamster rueda;
    private Hamster hamaca;

    Playon (){
        this.plato = null;
        this.hamaca = null;
        this.rueda = null;
    }

    public synchronized void setPlato(Hamster ham){
        this.plato = ham;
        System.out.println(Thread.currentThread().getName() + " esta en el plato");
        System.out.println(Thread.currentThread().getName() + " realizo la siguiente actividad: " + ham.getEstado());
    }

    public synchronized void setRueda(Hamster ham){
        this.rueda = ham;
        System.out.println(Thread.currentThread().getName() + " esta en la rueda");
        System.out.println(Thread.currentThread().getName() + " realizo la siguiente actividad: " + ham.getEstado());
    }

    public synchronized void setHamaca(Hamster ham){
        this.hamaca = ham;
        System.out.println(Thread.currentThread().getName() + " esta en la hamaca");
        System.out.println(Thread.currentThread().getName() + " realizo la siguiente actividad: " + ham.getEstado());
    }
}
