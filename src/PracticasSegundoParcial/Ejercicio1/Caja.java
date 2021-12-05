package PracticasSegundoParcial.Ejercicio1;

public class Caja {

    private int botellasMax;
    private int botellasAct = 0;
    private long tiempMad = 0;
    private long tiempoEmp;
    private boolean vino;

    public Caja(int botellasMax, long tiempMad, boolean vino) {
        this.botellasMax = botellasMax;
        this.vino = vino;

        //Si es una caja de vino setea el tiempo de maduracion, caso contrario la deja en 0
        if(this.vino)
            this.tiempMad = tiempMad;
    }

    public Caja(Caja cajaVieja) {
        this.botellasMax = cajaVieja.botellasMax;
        this.tiempMad = cajaVieja.tiempMad;
        this.vino = cajaVieja.vino;

    }

    public void setTiempoEmp(long tiempoEmp){
        this.tiempoEmp = tiempoEmp;
    }

    public boolean agregarBotella(){
        //Suma el contador mas 1 si tiene lugar; retorna true si esta llena
        boolean hayLugar = botellasAct < botellasMax;

        if(hayLugar){
            botellasAct++;
            hayLugar = botellasAct < botellasMax;
        }
        return hayLugar;
    }

    public int getBotellasAct(){
        return this.botellasAct;
    }

    public boolean esVino(){
        return this.vino;
    }

    public boolean estaLlena(){
        //Retorna un boolean en caso de estar llena
        return botellasAct == botellasMax;
    }

    public boolean estaMadura(long tiempoActual){
        //Returna true en caso de estar madura
        return (tiempoActual-this.tiempoEmp) >= tiempMad;
    }
}
