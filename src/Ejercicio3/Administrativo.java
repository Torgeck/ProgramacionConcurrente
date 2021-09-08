package Ejercicio3;

import java.util.Date;

public class Administrativo extends Empleado{

    private String categoria;

    public Administrativo(String nombre, String DNI, String direccion, Date fechaNacimiento, char sexo, String legajo, int porcentajeAntiguedad, String categoria) {
        super(nombre, DNI, direccion, fechaNacimiento, sexo, legajo, porcentajeAntiguedad);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString(){
        return super.toString() + " "+ categoria;
    }


}
