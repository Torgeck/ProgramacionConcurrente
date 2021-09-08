package Ejercicio2;

import java.util.Date;

public class Barco {

    public String matricula;
    public int eslora;
    public Date anioFabricacion;
    public static final int VALOR_FIJO = 200;


    public Barco(String matricula, int eslora, Date anioFabricacion) {
        this.matricula = matricula;
        this.eslora = eslora;
        this.anioFabricacion = anioFabricacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getEslora() {
        return eslora;
    }

    public void setEslora(int eslora) {
        this.eslora = eslora;
    }

    public Date getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(Date anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public double calcularModulo(){
        //Calcula el modulo de un barco
        return this.eslora*10 + VALOR_FIJO;
    }
}
