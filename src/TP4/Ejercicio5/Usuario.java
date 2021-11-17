package TP4.Ejercicio5;

public class Usuario implements Runnable{
    private CentroImpresion impresoras;
    private char tipo;
    private String archivo;

    public Usuario(String archivo, char tipo, CentroImpresion impresoras){
        this.tipo = tipo;
        this.impresoras = impresoras;
        this.archivo = archivo;
    }

    public void run() {
        impresoras.imprimir(tipo, archivo);
    }
}
