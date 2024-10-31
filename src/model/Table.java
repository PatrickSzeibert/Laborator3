// model/Table.java
package model;

public class Table {
    private int number;
    private int seats;

    public Table(int number, int seats) {
        this.number = number;
        this.seats = seats;
    }

    public int getNumber() {
        return number;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return "Table " + number + " (" + seats + " seats)";
    }
}
