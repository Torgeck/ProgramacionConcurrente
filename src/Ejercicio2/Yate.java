package Ejercicio2;

import java.util.Date;

public class Yate extends Deportivos{

    private int nroCamarotes;

    public Yate(int potenciaCV, int nroCamarotes, String matricula, int eslora, Date anioFabricacion) {
        super(potenciaCV,matricula, eslora, anioFabricacion);
        this.nroCamarotes = nroCamarotes;
    }

    public int getNroCamarotes() {
        return nroCamarotes;
    }

    public void setNroCamarotes(int nroCamarotes) {
        this.nroCamarotes = nroCamarotes;
    }

    @Override
    public double calcularModulo (){
        return super.calcularModulo() + this.nroCamarotes;
    }
}
