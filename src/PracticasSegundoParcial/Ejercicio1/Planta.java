package PracticasSegundoParcial.Ejercicio1;

import java.time.Clock;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Planta {

    private final int TIPO_CAJA = 2;
    private final int BOTELLAS_MAX = 10;
    private final int CAP_ALMACEN = 100;
    private int capActAlmacen = 0;
    private int cantidadEmbotelladores;
    private long tiempoMad;
    private HashMap<Integer, DuplaCaja> cajasEmbotellador = new HashMap<>();
    private LinkedList <Caja> cajasAlmacen = new LinkedList<>();
    private Lock mutexEmbEmp = new ReentrantLock();
    private Lock mutexEmpCam = new ReentrantLock();
    private Condition hayLugarCaja = mutexEmbEmp.newCondition(), hayCajaLlena = mutexEmbEmp.newCondition();
    private Condition hayLugarAlmacen = mutexEmpCam.newCondition(), hayAlmacenLleno = mutexEmpCam.newCondition();
    private Clock relojPlanta = Clock.systemDefaultZone();

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

    public void preparaBotella(int id, boolean tipo) throws InterruptedException {
        mutexEmbEmp.lock();
        try{
            //Si la caja esta llena
            while(cajasEmbotellador.get(id).getCajaTipo(tipo).estaLlena()){
                System.out.println("******* CAJA__________LLENA ********");
                //Aviso al empaquetador
                hayCajaLlena.signal();
                hayLugarCaja.await();
            }
        }finally {
            mutexEmbEmp.unlock();
        }
    }

    public void cargaBotellas (int id, boolean tipo){
        mutexEmbEmp.lock();
        try{
            //Agrego a mi caja correspondiente una botella del mismo tipo
            cajasEmbotellador.get(id).getCajaTipo(tipo).agregarBotella();
            System.out.println("Botellas actules "+cajasEmbotellador.get(id).getCajaTipo(tipo).getBotellasAct()+ " en caja de vino: " +tipo);
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

                if(cajasEmbotellador.get(embotellador).getCajaLlena() != null) {
                    cajaIncompleta = false;
                }
                else {
                    //Me fijo en el siguiente
                    embotellador++;

                    //Si se fijo en todos los embotelladores
                    if (embotellador == cantidadEmbotelladores) {
                        System.out.println();
                        embotellador = 0;
                        cajaIncompleta = true;
                        hayCajaLlena.await();
                    }
                }

            }
        }finally {
            mutexEmbEmp.unlock();
        }
        return embotellador;
    }

    public Caja guardarCaja(int embotellador)throws InterruptedException{
        mutexEmpCam.lock();
        Caja cajaLlena = null;
        try{
            //Si no hay lugar en el almacen le avisa al camion y duerme
            while(capActAlmacen == CAP_ALMACEN){
                System.out.println("-----------------ALMACEN LLENO-------------");
                hayAlmacenLleno.signal();
                hayLugarAlmacen.await();
            }
            //Si hay espacio guarda la caja en el almacen

            //Obtiene la caja llena del embotellador y la guarda en el almacen
            cajaLlena = cajasEmbotellador.get(embotellador).getCajaLlena();
            //La etiqueta con el tiempo actual de la planta
            cajaLlena.setTiempoEmp(relojPlanta.millis());

            cajasAlmacen.add(cajaLlena);
            capActAlmacen += BOTELLAS_MAX;

            System.out.println("CAPACIDAD ALMACEN ACTUAL "+ capActAlmacen);
        }finally {
            mutexEmpCam.unlock();
        }
        return cajaLlena;
    }

    public void reponeCaja(int embotellador, Caja cajaVieja){
        mutexEmbEmp.lock();
        DuplaCaja nuevaDupla;
        try{
            //Le da una caja nueva y le avisa a todos

            if(cajaVieja.esVino()){
                nuevaDupla = new DuplaCaja(new Caja(cajaVieja),cajasEmbotellador.get(embotellador).getGaseosa());
            }
            else{
                nuevaDupla = new DuplaCaja(cajasEmbotellador.get(embotellador).getVino(), new Caja(cajaVieja));
            }

            cajasEmbotellador.replace(embotellador,nuevaDupla);


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
                System.out.println("EL ALMACEN NO ESTA LLENO------------------");
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
                    if (!cajaAux.estaMadura(relojPlanta.millis())) {
                        cajasAlmacen.add(cajaAux);
                        capActAlmacen += BOTELLAS_MAX;
                    }
                }

                capActAlmacen -= BOTELLAS_MAX;
                nroCaja++;
            }
            System.out.println("El camion dejo el almacen con una capacidad de: "+ capActAlmacen );

            //Una vez que termine de revisar las cajas aviso que se libero espacio y me voy a repartir
            hayLugarAlmacen.signal();
        }finally {
            mutexEmpCam.unlock();
        }
    }




}
