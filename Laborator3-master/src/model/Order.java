package model;

import exceptions.InvalidOrderException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private Table table;
    private List<OrderItem> items;


    public Order(Table table) throws InvalidOrderException {
        if (table.isOccupied()) {
            throw new InvalidOrderException("The table is already occupied!");
        }
        this.table = table;
        this.items = new ArrayList<>();
        table.setOccupied(true);
    }


    public void addItem(String itemName, int quantity) throws InvalidOrderException {
        if (quantity <= 0) {
            throw new InvalidOrderException("Quantity must be positive.");
        }


        for (OrderItem existingItem : items) {
            if (existingItem.getName().equals(itemName)) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                return;
            }
        }


        items.add(new OrderItem(itemName, quantity));
    }

    public void removeItem(String itemName) throws InvalidOrderException {
        OrderItem itemToRemove = null;

        for (OrderItem item : items) {
            if (item.getName().equals(itemName)) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            items.remove(itemToRemove);
        } else {
            throw new InvalidOrderException("Item not found in the order.");
        }
    }


    public List<OrderItem> getItems() {
        return items;
    }


    public Table getTable() {
        return table;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order for Table ").append(table.getNumber()).append(":\n");

        for (OrderItem item : items) {
            sb.append("- ").append(item.getName())
                    .append(" x ").append(item.getQuantity())
                    .append("\n");
        }

        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(table, order.table) &&
                Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, items);
    }


    public void completeOrder() {
        table.setOccupied(false);
    }
}
