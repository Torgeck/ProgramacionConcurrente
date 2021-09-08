package Ejercicio2;

import java.util.Date;

public class Deportivos extends Barco{

    public int potenciaCV;

    public Deportivos(int potenciaCV, String matricula, int eslora , Date anioFabricacion) {
        super(matricula, eslora, anioFabricacion);
        this.potenciaCV = potenciaCV;
    }

    public int getPotenciaCV() {
        return potenciaCV;
    }

    public void setPotenciaCV(int potenciaCV) {
        this.potenciaCV = potenciaCV;
    }

    @Override
    public double calcularModulo (){
        return super.calcularModulo() + this.potenciaCV;       //Para usar metodos de la superClase es con super.<metodoSuperClase>
    }
}
