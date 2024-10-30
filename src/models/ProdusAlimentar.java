package models;

import models.Produs;

public class ProdusAlimentar extends Produs {
    private String termenValabilitate;

    public ProdusAlimentar(String nume, double pret, String termenValabilitate) {
        super(nume, pret);
        this.termenValabilitate = termenValabilitate;
    }

    public String getTermenValabilitate() {
        return termenValabilitate;
    }

    @Override
    public String toString() {
        return super.toString() + ", Termen de valabilitate: " + termenValabilitate;
    }
}
