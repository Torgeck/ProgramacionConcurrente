package Ejercicio3;

import java.util.Date;

public class Tecnico extends Empleado{

    private String titulo;
    private Date anioObtenciontitulo;

    public Tecnico(String nombre, String DNI, String direccion, Date fechaNacimiento, char sexo, String legajo, int porcentajeAntiguedad, String titulo, Date anioObtenciontitulo) {
        super(nombre, DNI, direccion, fechaNacimiento, sexo, legajo, porcentajeAntiguedad);
        this.titulo = titulo;
        this.anioObtenciontitulo = anioObtenciontitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getAnioObtenciontitulo() {
        return anioObtenciontitulo;
    }

    public void setAnioObtenciontitulo(Date anioObtenciontitulo) {
        this.anioObtenciontitulo = anioObtenciontitulo;
    }
}
