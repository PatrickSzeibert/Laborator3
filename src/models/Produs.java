package models;

public class Produs {
    private String nume;
    private double pret;

    public Produs(String nume, double pret) {
        this.nume = nume;
        this.pret = pret;
    }

    public String getNume() {
        return nume;
    }

    public double getPret() {
        return pret;
    }

    @Override
    public String toString() {
        return "Produs: " + nume + ", Pret: " + pret;
    }
}
