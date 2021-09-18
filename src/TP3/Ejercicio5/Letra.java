package TP3.Ejercicio5;

public class Letra implements Runnable{

    private char letra;
    private Cadena cadena;

    Letra(char unChar,Cadena unaCad){
        this.letra = unChar;
        this.cadena = unaCad;
    }

    public void escribir() throws InterruptedException {

        if(this.cadena.getTurno().leToca(letra)) {
            this.cadena.escribirCadena(this.letra);
            System.out.println("Cadena actual es: " + this.cadena.getCadena());
            this.cadena.getTurno().setTurno();
        }
        else{
            System.out.println("No es el turno de "+ this.letra);
            Thread.sleep(100);
        }

    }


    public void run(){

        try {
            for (int i = 0; i <= 10; i++) {
                escribir();
            }
        } catch (InterruptedException e){
            System.out.println("Se interrumpio el sistema");
        }
    }
}
