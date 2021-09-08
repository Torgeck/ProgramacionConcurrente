package Ejercicio2;

import java.util.Date;

public class Alquiler {

    public Cliente cliente;
    public Date fechaInicial;
    public Date fechaFinal;
    public int amarre;

    public Alquiler(Cliente cliente, Date fechaInicial, Date fechaFinal, int amarre) {
        this.cliente = cliente;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.amarre = amarre;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getAmarre() {
        return amarre;
    }

    public void setAmarre(int amarre) {
        this.amarre = amarre;
    }
}
