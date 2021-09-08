package TesteoDeCosasVarias;

import java.io.*;

public class Usa {

/*    public void hacerCosas(){
        Padre p = new Padre();
        Padre h = new Hijo();
        p.hacerAlgo();
        h.hacerAlgo();
    }

    public static void main (String[] args){

        Usa u = new Usa();

        u.hacerCosas();

    }*/

    public static int metodo(){
        int valor=0;
        try{
            valor = valor + 1;
            valor = valor + Integer.parseInt("WS");
            valor = valor + 1;
            System.out.println("Valor al final del try: " + valor);
            throw new IOException();
        } catch (IOException e){
            valor = valor + Integer.parseInt("42");
            System.out.println("Valor al final del catch: " + valor);
        } finally {
            valor = valor + 1;
            System.out.println("Valor al final del finally: " + valor);
        }

        valor = valor + 1;
        System.out.println("valor antes del return: "+ valor);
        return valor;
    }

    public static void main(String[] args) {
        try {
            System.out.println(metodo());
        } catch (Exception e) {
            System.out.println("Excepcion en emtodo() ");
            e.printStackTrace();
        }
    }
}