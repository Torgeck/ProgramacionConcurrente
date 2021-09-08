package Ejercicio2;

import java.util.Date;

public class Velero extends Barco{

    public int cantMastiles;

    public Velero(int cantMastiles, String matricula, int eslora, Date anioFabricacion) {
        super(matricula, eslora, anioFabricacion);
        this.cantMastiles = cantMastiles;
    }

    public int getCantMastiles() {
        return cantMastiles;
    }

    public void setCantMastiles(int cantMastiles) {
        this.cantMastiles = cantMastiles;
    }

    @Override
    public double calcularModulo (){
        return super.calcularModulo() + this.cantMastiles;       //Para usar metodos de la superClase es con super.<metodoSuperClase>
    }
}
