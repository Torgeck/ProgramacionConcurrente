package TP3;

public class Personaje {

    private int vida = 10;

    public Personaje() {

    }

    public int getVida(){
        return this.vida;
    }

    public synchronized void curar(int curacion){
        this.vida += curacion;
    }

    public synchronized void dañar(int daño){
        this.vida -= daño;
    }




}
