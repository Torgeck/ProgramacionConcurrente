package Ejercicio3;

import java.util.Date;

public class Persona {

    private String nombre;
    private String DNI;
    private String direccion;
    private Date fechaNacimiento;
    private char sexo;

    public Persona(String nombre, String DNI, String direccion, Date fechaNacimiento, char sexo) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }



    public boolean antiguedadMayor10() {
        //Retorna false ya que persona no posee antiguedad
        return false;
    }

}
