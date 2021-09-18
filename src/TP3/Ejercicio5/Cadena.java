package TP3.Ejercicio5;

public class Cadena {

    private String cadena;
    private Turno turno;

    Cadena(){
        this.cadena = "";
        this.turno = new Turno();
    }

    public synchronized void escribirCadena(char unChar){
        cadena = cadena.concat(String.valueOf(unChar));
    }

    public String getCadena(){
        return this.cadena;
    }

    public Turno getTurno() {
        return turno;
    }
}
