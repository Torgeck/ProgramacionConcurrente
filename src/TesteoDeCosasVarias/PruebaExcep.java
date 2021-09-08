package TesteoDeCosasVarias;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class PruebaExcep{
    //Clase para testear distintas exceptions

    public static int edad(int edad) throws MenorDeEdadException {
        //Metodo que dispara una excepcion si es menor de edad

        if (edad < 18)
            throw new MenorDeEdadException("Error menor de edad detected boe que ponia");

        return edad;
    }

    public static String ruleta(int numJugador) throws RuletteNotEqualsPlayerNumberException {
        //Metodo que recibe por parametro un numero de la ruleta y lo compara, si no es igual dispata una excepcion
        Random r = new Random();
        int numRuleta = r.nextInt(36);
       // int numRuleta = 15;
        String respuesta = "Ganaste";

        System.out.println("El numero del jugador es: " + numJugador);

        try{
            System.out.println("El numero de la ruleta es: " + numRuleta);
            if(numRuleta != numJugador)
                throw new RuletteNotEqualsPlayerNumberException("No coinciden los numeros");
        }
        catch (RuletteNotEqualsPlayerNumberException re){
            System.out.println(re);
            respuesta = "Perdiste";
        }

        return respuesta;

    }

    public static void muestra7(){
        //Metodo que pide ingresar 5 datos y muestra 7 del array
        Scanner teclado = new Scanner(System.in);
        int[] colleccion = new int[5];
        int i=-1;

        //Ingresa usuario
        try{
            for(int numUsuario : colleccion) {
                i++;
                System.out.println("Ingrese un num");
                numUsuario = teclado.nextInt();
                colleccion[i] = numUsuario;
            }
        }

        catch (InputMismatchException im){
            System.out.println("Caracter ingresado no es valido");
            //modularizar el codigo de  arriba en un metodo
        }

        //Ahora es donde se lee fuera del array

        try{

            //Modularizar for
            for (int j = 0; j <= 7 ; j++){
                System.out.println(colleccion[j]);
            }
        }

        catch (IndexOutOfBoundsException e){
            System.out.println("Error el metodo leyo de mas");
        }

    }

    public static void main (String[] args) {

     /*   try {
            System.out.println(ruleta(15));
        }catch (RuletteNotEqualsPlayerNumberException r){
            System.out.println(r);
        }*/

        muestra7();
    }
}


