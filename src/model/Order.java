// model/Order.java
package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Table table;
    private List<OrderItem> items;

    public Order(Table table) {
        this.table = table;
        this.items = new ArrayList<>();
    }

    public void addItem(String name, int quantity) {
        items.add(new OrderItem(name, quantity));
    }

    public Table getTable() {
        return table;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Table: ").append(table).append(", Items: ");
        for (OrderItem item : items) {
            sb.append(item).append(", ");
        }
        return sb.toString();
    }
}
