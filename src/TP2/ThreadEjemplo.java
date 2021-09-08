package TP2;

public class ThreadEjemplo implements Runnable{

   String nombreHilo;

   public ThreadEjemplo(String unNom){
       nombreHilo = unNom;
   }


    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println(i + " " + nombreHilo );
            System.out.println("Termina thread " + nombreHilo);
        }
    }


}
