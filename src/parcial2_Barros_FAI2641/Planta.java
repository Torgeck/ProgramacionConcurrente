package parcial2_Barros_FAI2641;

import java.util.concurrent.locks.*;


public class Planta {

    private int tiempoMad;      //Es el tiempo de maduracion de las cajas de vino
    private int cantidadEmbotelladores;     //Es la cantidad de embotelladores de la planta
    private int[] capActCaja;               //Es la capacidad actual de cada caja (una caja por embotellador)
    private int capActAlmacen;              //Es la capacidad actual del almacen
    private final int CAP_MAX_CAJA = 10;        //Es la capacidad maxima de cada caja
    private final int CAP_MAX_ALMACEN = 100;      //Es la capacidad maxima del almacen en este caso 100Lts
    private boolean hayVino;                    //La setean los embotelladores con su atributo por parametro
    private boolean hayVinoAlmacen;             //Se utiliza por el brazo para saber si guardo vino en el almacen
    private Lock mutex = new ReentrantLock();
    private Condition hayLugarCaja = mutex.newCondition(), hayCajaLlena = mutex.newCondition(), hayLugarAlmacen = mutex.newCondition(), almacenLleno = mutex.newCondition();


    public Planta(int cantEmbo,int tiempoMad){
        this.cantidadEmbotelladores = cantEmbo;
        this.tiempoMad = tiempoMad;
        capActCaja = new int[cantEmbo];
        capActAlmacen = 0;
        hayVino = false;
        hayVinoAlmacen = false;
    }

    //Embotellador---------

    public void prepararBotella(int nroEmbotellador,boolean vino) throws InterruptedException {
        mutex.lock();
        //Intenta embotellar si hay lugar, si no le avisa al brazo
        try {
            //Si embotella vino avisa que hay vino
            if(vino)
                hayVino = true;

            //Si la caja esta llena espera
            while (capActCaja[nroEmbotellador] == CAP_MAX_CAJA){
                System.out.println("=====CAJA LLENA"+nroEmbotellador+"==========");
                hayCajaLlena.signal();
                hayLugarCaja.await();
            }
        }finally {
            mutex.unlock();
        }
    }

    public void guardarBotella(int nroEmbotellador){
        mutex.lock();
        //Pone una botella en la caja
        try{
            capActCaja[nroEmbotellador]++;
        }finally {
            mutex.unlock();
        }

    }

    //Empaquetador--------------
    public int retirarCaja() throws InterruptedException {
        mutex.lock();
        int i = 0;
        try{
            //Se fija en cada caja, si no estan llenas espera
            while(capActCaja[i] < CAP_MAX_CAJA){
                i++;
                if(i == cantidadEmbotelladores)
                    i = 0;
                    hayCajaLlena.await();
            }
        }finally {
            mutex.unlock();
        }
        return i;
    }


    public void reponerCaja(int nroCaja) throws InterruptedException{
        mutex.lock();
        try {
            //Se fija si hay lugar en el almacen si no hay se le avisa al camion
            while(capActAlmacen == CAP_MAX_ALMACEN){
                System.out.println("=====ALMACEN LLENO==========");
                almacenLleno.signal();
                hayLugarAlmacen.await();
            }

            if(hayVino)
                hayVinoAlmacen = true;

            //Se guarda la caja, se le da una caja nueva al embotellador y se le avisa a todos los embotelladores trabados
            System.out.println("====="+capActAlmacen+"==========");
            capActAlmacen += 10;
            capActCaja[nroCaja] = 0;
            hayLugarCaja.signalAll();
        } finally {
            mutex.unlock();
        }
    }

    //Camion----------------
    public int liberarAlmacen() throws InterruptedException{
        mutex.lock();
        int espera = 0;     //Variable para saber cuanto hay que esperar para la maduracion
        try{
            //Se fija si el almacen esta vacio
            while(capActAlmacen != CAP_MAX_ALMACEN) {
                almacenLleno.await();
            }
            //Si hay vino va a tener que esperar la maduracion
            if(hayVinoAlmacen){
                espera = tiempoMad;
            }

        }finally {
            mutex.unlock();
        }
        return espera;
    }

    public void repartir(){
        mutex.lock();
        try {
            //Libera el almacen, resetea que no hay vino en el almacen y le avisa al brazo y se va a repartir
            hayVinoAlmacen = false;
            capActAlmacen = 0;
            hayLugarAlmacen.signal();
        }finally {
            mutex.unlock();
        }
    }


}
