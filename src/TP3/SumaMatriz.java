package TP3;

public class SumaMatriz {

    private int sum;

    public int sumMatriz(int[] nums){
        synchronized (this) {
            sum = 0;
            for (int i = 0; i < nums.length; i++) {

                sum += nums[i];
                System.out.println("Total acumulado de " + Thread.currentThread().getName() + " es " + sum);

                try {
                    Thread.sleep(10);//Permitir el cambio de tarear
                } catch (InterruptedException exc) {
                    System.out.println("Hilo interrumpido");
                }

            }
        }
       return sum;
    }
}
