package PracticasSegundoParcial.Ejercicio1;

public class DuplaCaja {

    private Caja gaseosa;
    private Caja vino;

    public DuplaCaja(Caja vino, Caja gaseosa) {
        this.gaseosa = gaseosa;
        this.vino = vino;
    }

    public void setGaseosa(Caja gaseosa) {
        this.gaseosa = gaseosa;
    }

    public void setVino(Caja vino) {
        this.vino = vino;
    }

    public Caja getGaseosa() {
        return gaseosa;
    }

    public Caja getVino() {
        return vino;
    }

    public Caja getCajaTipo(boolean vino){
        return (vino) ? this.vino : this.gaseosa;
    }

    public Caja getCajaLlena(){
        //Retorna caja llena si la hay, caso contrario retorna null
        Caja cajaLlena = null;

        if(this.vino.estaLlena())
            cajaLlena = this.vino;
        else if(this.gaseosa.estaLlena())
            cajaLlena = this.gaseosa;

        return cajaLlena;
    }

    public void reemplazaCaja(Caja cajaVieja){
        Caja cajaNueva = new Caja(cajaVieja);

        if(this.vino.estaLlena())
            this.vino = cajaNueva;
        else
            if(this.gaseosa.estaLlena())
                this.vino = cajaNueva;

    }
}
