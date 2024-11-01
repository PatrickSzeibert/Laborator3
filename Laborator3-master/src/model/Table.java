package model;

public class Table {
    private int number;
    private int capacity;
    private boolean occupied;


    public Table(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
        this.occupied = false;
    }


    public int getNumber() {
        return number;
    }


    public int getCapacity() {
        return capacity;
    }


    public boolean isOccupied() {
        return occupied;
    }


    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }


    @Override
    public String toString() {
        return "Table " + number + " (Capacity: " + capacity + ")" + (occupied ? " (Occupied)" : " (Available)");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return number == table.number;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }
}
