package PracticasSegundoParcial.Ejercicio1;

import java.time.Clock;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Planta {

    private final int BOTELLAS_MAX = 10;
    private final int CAP_ALMACEN = 100;
    private int capActAlmacen = 0;
    private int cantidadEmbotelladores;
    private long tiempoMad;
    private HashMap<Integer, DuplaCaja> cajasEmbotellador = new HashMap<>();                                            //Simboliza la linea de cajas de los embotelladores
    private LinkedList <Caja> cajasAlmacen = new LinkedList<>();                                                        //Simboliza el espacio en el almacen
    private Lock mutexEmbEmp = new ReentrantLock();                                                                     //Se usa para las interacciones de embotellador y empaquetador
    private Lock mutexEmpCam = new ReentrantLock();                                                                     //Se usa para las interacciones de empaquetador y camion
    private Condition hayLugarCaja = mutexEmbEmp.newCondition(), hayCajaLlena = mutexEmbEmp.newCondition();
    private Condition hayLugarAlmacen = mutexEmpCam.newCondition(), hayAlmacenLleno = mutexEmpCam.newCondition();
    private final Clock RELOJ_PLANTA = Clock.systemDefaultZone();                                                       //Se usa para simular el tiempo de maduracion de los vinos
    private final Random TIPO_BOTELLA = new Random();
    /*private final String COLOR = "\033[1;31m";            // Colores para debug
    private final String RESET = "\033[0m";*/

    public Planta (int cantEmbo, long tiempoMad){
        this.tiempoMad = tiempoMad;
        this.cantidadEmbotelladores = cantEmbo;
        inicializarCajas(cantEmbo,this.tiempoMad);
    }

    private void inicializarCajas(int cantEmbo, long maduracion){
        for(int i = 0 ; i < cantEmbo; i++){
            //Crea un dupla de cajas por cada embotellador la primera es de vino la segunda es de gaseosa
            DuplaCaja dupla = new DuplaCaja(new Caja(this.BOTELLAS_MAX,maduracion,true),new Caja(this.BOTELLAS_MAX,maduracion,false));

            //Finalmente asigna la dupla de cajas con el embotellador
            cajasEmbotellador.put(i,dupla);
        }
    }

    // Embotellador

    public boolean preparaCargaBotella(int id){
        //Retorna el tipo de botella que va a cargar el embotellador de manera aleatoria
        boolean tipo = TIPO_BOTELLA.nextBoolean();

        //Agrego a mi caja correspondiente una botella del mismo tipo
        cajasEmbotellador.get(id).getCajaTipo(tipo).agregarBotella();

        return tipo;
    }

    public void verificaEstadoCaja(int id, boolean tipo) throws InterruptedException{
        //Metodo que verifica si la caja del embotellador [id] del [tipo] esta llena o tiene lugar
        mutexEmbEmp.lock();
        try{
            /*System.out.println("Botellas actuales "+cajasEmbotellador.get(id).getCajaTipo(tipo).getBotellasAct()+ " en caja de " + (tipo ? "vino":"gaseosa"));*/      //Debug
            //Si la caja esta llena
            while(cajasEmbotellador.get(id).getCajaTipo(tipo).estaLlena()){
                /*System.out.println(COLOR+ "******* CAJA LLENA ******** "+Thread.currentThread().getName() + " de tipo: "+ (tipo ? "vino":"gaseosa") + RESET);*/       //Debug
                //Aviso al empaquetador
                hayCajaLlena.signal();
                hayLugarCaja.await();
            }

        }finally {
            mutexEmbEmp.unlock();
        }
    }

    // Empaquetador

    public int preparaCaja() throws InterruptedException{
        //Metodo que busca que embotellador tiene caja llena y retorna su id
        mutexEmbEmp.lock();
        int embotellador = 0;
        boolean cajaIncompleta = true;

        try{
            //Veo si alguien tiene alguna caja llena
            while ( embotellador < cantidadEmbotelladores && cajaIncompleta){

                //Si alguien tiene la caja llena salgo
                if(cajasEmbotellador.get(embotellador).getCajaLlena() != null) {
                    cajaIncompleta = false;
                }
                else {
                    //Me fijo en el siguiente
                    embotellador++;

                    //Si me fije en todos los embotelladores, entonces espero
                    if (embotellador == cantidadEmbotelladores) {
                        embotellador = 0;
                        hayCajaLlena.await();
                    }
                }

            }
        }finally {
            mutexEmbEmp.unlock();
        }
        return embotellador;
    }

    public Caja guardarCaja(int embotellador) throws InterruptedException{
        //Metodo que recibe el id del embotellador y con el obtiene y guarda la caja en el almacen si es que hay espacio
        mutexEmpCam.lock();
        Caja cajaLlena;
        try{

            //Obtiene la caja llena del embotellador
            cajaLlena = cajasEmbotellador.get(embotellador).getCajaLlena();
            //La etiqueta con el tiempo actual de la planta
            cajaLlena.setTiempoEmp(RELOJ_PLANTA.millis());
            //Guarda la caja en el almacen y aumenta la capacidad actual del almacen
            cajasAlmacen.add(cajaLlena);
            capActAlmacen += BOTELLAS_MAX;

            /*System.out.println("CAPACIDAD ALMACEN ACTUAL "+ capActAlmacen);*/     //Debug
            //Si no hay lugar en el almacen le avisa al camion y duerme
            while(capActAlmacen == CAP_ALMACEN){
                /*System.out.println("-----------------ALMACEN LLENO-------------");*/      //Debug
                hayAlmacenLleno.signal();
                hayLugarAlmacen.await();
            }
        }finally {
            mutexEmpCam.unlock();
        }
        return cajaLlena;
    }

    public void reponeCaja(int embotellador, Caja cajaVieja){
        //Obtiene el id de embotellador y la caja vieja a reemplazar, crea una nueva dupla y la reasigna al hashmap
        mutexEmbEmp.lock();
        DuplaCaja nuevaDupla;
        try{
            //si es de vino reemplaza esa caja por una nueva
            if(cajaVieja.esVino()){
                nuevaDupla = new DuplaCaja(new Caja(cajaVieja),cajasEmbotellador.get(embotellador).getGaseosa());
            }
            //Si es de gaseosa reemplaza esa caja por una nueva
            else{
                nuevaDupla = new DuplaCaja(cajasEmbotellador.get(embotellador).getVino(), new Caja(cajaVieja));
            }
            //Reemplaza la caja vieja por una nueva
            cajasEmbotellador.replace(embotellador,nuevaDupla);
            //Avisa a todos para que se fijen si tienen lugar en su caja
            hayLugarCaja.signalAll();

        }finally {
            mutexEmbEmp.unlock();
        }

    }

    //Camion

    public void inspeccionAlmacen() throws InterruptedException{
        mutexEmpCam.lock();
        try {
            //Se fija si esta lleno, en caso de no estarlo se queda esperando a que le avisen
            while (capActAlmacen != CAP_ALMACEN) {
                //System.out.println("EL ALMACEN NO ESTA LLENO------------------");
                hayAlmacenLleno.await();
            }
        }finally {
            mutexEmpCam.unlock();
        }

    }

    public void cargaRepartoCajas(){
        mutexEmpCam.lock();
        int nroCaja = 0, capCajasAlmacen = CAP_ALMACEN/BOTELLAS_MAX;
        Caja cajaAux;
        try{
            //Checkea las cajas
            while(nroCaja < capCajasAlmacen){

                cajaAux = cajasAlmacen.remove();

                //Si la caja es de vino
                if(cajaAux.esVino()) {
                    //Si no esta madura la vuelve a poner en el almacen
                    if (!cajaAux.estaMadura(RELOJ_PLANTA.millis())) {
                        cajasAlmacen.add(cajaAux);
                        capActAlmacen += BOTELLAS_MAX;
                    }
                }

                capActAlmacen -= BOTELLAS_MAX;
                nroCaja++;
            }
            /*System.out.println("El camion dejo el almacen con una capacidad de: "+ capActAlmacen );*/         //Debug

            //Una vez que termine de revisar el almacen aviso que se libero espacio y me voy a repartir
            hayLugarAlmacen.signal();
        }finally {
            mutexEmpCam.unlock();
        }
    }
}
