package TP3.Ejercicio6;

public class Auto extends Vehiculo{

    private String marca;
    private String modelo;
    private String patente;
    private int cantKmFaltantesParaElService;
    private int nastaEnTank;

    public Auto(String marca, String patente, int cantKmFaltantesParaElService) {
        this.marca = marca;
        this.modelo = "modelo";
        this.patente = patente;
        this.cantKmFaltantesParaElService = cantKmFaltantesParaElService;
        this.nastaEnTank = 100;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getCantKmFaltantesParaElService() {
        return cantKmFaltantesParaElService;
    }

    public void setCantKmFaltantesParaElService(int cantKmFaltantesParaElService) {
        this.cantKmFaltantesParaElService = cantKmFaltantesParaElService;
    }

    public int getNastaEnTank() {
        return nastaEnTank;
    }

    public void setNastaEnTank(int nastaEnTank) {
        this.nastaEnTank = nastaEnTank;
    }

    public void andar(){
        this.nastaEnTank--;
        this.cantKmFaltantesParaElService--;
    }

    public boolean enReserva(){
        return nastaEnTank < 25;
    }

    public void llenarTanque(){
        this.nastaEnTank = 100;
    }
}
