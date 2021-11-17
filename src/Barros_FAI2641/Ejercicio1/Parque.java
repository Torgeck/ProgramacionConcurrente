package Barros_FAI2641.Ejercicio1;

import java.util.concurrent.Semaphore;

public class Parque {

    private int babuinos;       //Se utiliza para saber la cantidad total de babuinos en el parque
    private int babuinosIzq;    //Se utiliza para contar la cantidad de babuinos que pasaron a la izquierda
    private int babuinosDer;    //Se utiliza para conta la cantidad de babuinos que se pasaron a la derecha
    private Semaphore cruzeDiario = new Semaphore(0);         //Lo utiliza el supervisor al finalizar el cruze del dia
    private Semaphore cuerda = new Semaphore(1);        //Los babuinos no respetan ordenes de llegada

    Parque(int b){
        this.babuinos = b;
        this.babuinosIzq = 0;
        this.babuinosDer = 0;
    }

    public int getCantBabuinos() {
        return babuinos;
    }

    public void cruzarCuerda() throws InterruptedException{
        //Cruza la cuerda si es que no hay ningun babuino en ella
        cuerda.acquire();
    }

    public void soltarCuerda(char lado){
        //Incrementa el contador del lado destino del babuino
        incrementarContador(lado);
        //Termina de cruzar la cuerda y se añade un contador al lado correspondiente
        cuerda.release();
        //Si cruzaron todos el supervisor avisa
        if(cruzaronTodos())
            cruzeDiario.release();
    }

    public boolean cruzaronTodos(){
        //Retorna true si ya cruzaron todos los babuinos y false si no
        return babuinos == (babuinosDer + babuinosIzq);
    }

    public void incrementarContador(char lado){
        //Incrementan de destino, esta accion esta protegida por un lock, se podria utilizar un bloque sincronizado para hacer lo mismo
        try {
            if (lado == 'I')
                babuinosDer++;
            else
                babuinosIzq++;
        }finally {
        }
    }

    public void parqueToString() throws InterruptedException{
        //El supervisor espera a que todos los babuinos hayan cruzado para dar el resultado final
        cruzeDiario.acquire();
        System.out.println("Cruzaron "+ babuinosIzq + " al lado izquierdo y " + babuinosDer +" al lado derecho, cruzaron todos?: " +cruzaronTodos());
    }


}
