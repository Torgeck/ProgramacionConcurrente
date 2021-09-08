package Ejercicio3;

import java.util.Date;

public class Empleado extends Persona{

    private String legajo;
    private int antiguedad;

    public Empleado(String nombre, String DNI, String direccion, Date fechaNacimiento, char sexo, String legajo, int porcentajeAntiguedad) {
        super(nombre, DNI, direccion, fechaNacimiento, sexo);
        this.legajo = legajo;
        this.antiguedad = porcentajeAntiguedad;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public int getjeAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int porcentajeAntiguedad) {
        this.antiguedad = porcentajeAntiguedad;
    }

    @Override
    public boolean antiguedadMayor10() {
        //Retorna false si la antiguedad es menor a 10 y true si es mayor o igual a 10
        return this.antiguedad > 10;
    }

    @Override
    public String toString(){
        return super.toString() + legajo + " " + antiguedad;
    }
}
