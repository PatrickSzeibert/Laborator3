package models;

public class Comanda {
    private int numarComanda;
    private Produs produs;
    private int cantitate;
    private double pretTotal;

    public Comanda(int numarComanda, Produs produs, int cantitate) {
        this.numarComanda = numarComanda;
        this.produs = produs;
        this.cantitate = cantitate;
        this.pretTotal = produs.getPret() * cantitate;
    }

    public int getNumarComanda() {
        return numarComanda;
    }

    public Produs getProdus() {
        return produs;
    }

    public int getCantitate() {
        return cantitate;
    }

    public double getPretTotal() {
        return pretTotal;
    }

    @Override
    public String toString() {
        return "Comanda: " + numarComanda + ", Produs: " + produs.getNume() + ", Cantitate: " + cantitate + ", Pret Total: " + pretTotal;
    }
}
