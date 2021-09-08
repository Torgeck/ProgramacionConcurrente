package Ejercicio2;

public class Cliente {

    public String nombreYApellido;
    public String DNI;

    public String getNombreYApellido() {
        return nombreYApellido;
    }

    public void setNombreYApellido(String nombreYApellido) {
        this.nombreYApellido = nombreYApellido;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Cliente(String nombreYApellido, String DNI) {
        this.nombreYApellido = nombreYApellido;
        this.DNI = DNI;
    }
}


