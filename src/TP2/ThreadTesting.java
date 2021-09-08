package TP2;

public class ThreadTesting {

    public static void main(String[] args){
        /*Cliente juan= new Cliente();
        juan.setName("Juan Lopez");
        Cliente ines = new Cliente();
        ines.setName("Ines Garcia");
        juan.start();
        ines.start();*/

    /*    ThreadEjemplo mariaJose = new ThreadEjemplo("Maria Jose");
        ThreadEjemplo joseMaria = new ThreadEjemplo("Jose Maria");

        mariaJose.run();
        joseMaria.run();


        System.out.println("Termina thread main");
*/

        System.out.println("Hilo principal iniciando. ");

        //Primero, construye un obj unHilo
        MiHilo h1= new MiHilo("#1");
        MiHilo h2 = new MiHilo("#2");
        MiHilo h3 = new MiHilo("#3");

        //Luego, contruye un hilo de ese obj
        //Thread nuevoHilo = new Thread(mh);

        //Finalmente, comienza la ejecucion del hilo
        h1.start();
        h2.start();
        h3.start();

        for(int i=0; i<50 ; i++){
            System.out.println(" .");
        }

        try{
            Thread.sleep(100);
        } catch (InterruptedException exc) {
            System.out.println("Hilo principal interrumpido.");
        }
        System.out.println("Hilo principal finalizado");
    }
}
