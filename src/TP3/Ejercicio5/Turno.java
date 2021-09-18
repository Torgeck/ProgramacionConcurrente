package TP3.Ejercicio5;

public class Turno {

    private char turno;
    private int contador;

    Turno(){
        this.turno = 'A';
        this.contador = 0;
    }

    public boolean leToca(char letra) {
        return this.turno == letra ;
    }

    public synchronized void setTurno() {

        if(this.turno == 'A'){
            this.turno = 'B';
            this.contador++;
        }
        else if(this.turno == 'B' && this.contador == 2){
            this.turno = 'C';
            this.contador = 0;
        }
        else if(this.turno == 'C' && this.contador == 2){
            this.turno = 'A';
            this.contador = 0;
        }else
            this.contador++;
    }
}
