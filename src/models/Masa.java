package models;

public class Masa {
    private int numarMasa;
    private int capacitate;

    public Masa(int numarMasa, int capacitate) {
        this.numarMasa = numarMasa;
        this.capacitate = capacitate;
    }

    public int getNumarMasa() {
        return numarMasa;
    }

    public int getCapacitate() {
        return capacitate;
    }

    @Override
    public String toString() {
        return "Masa: " + numarMasa + ", Capacitate: " + capacitate;
    }
}
