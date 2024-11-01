// gui/OrderDialog.java
package gui;

import exceptions.InvalidOrderException;
import model.Order;
import model.OrderItem;
import model.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderDialog extends JDialog {
    private JTextField itemField;
    private JTextField quantityField;
    private JComboBox<Table> tableComboBox;
    private JList<OrderItem> orderItemList; // List to display order items
    private DefaultListModel<OrderItem> orderItemListModel; // Model for the list
    private Order order;

    public OrderDialog(JFrame parent, List<Table> tables) {
        super(parent, "New Order", true);
        setSize(500, 300);
        setLocationRelativeTo(parent);

        itemField = new JTextField(10);
        quantityField = new JTextField(10);
        tableComboBox = new JComboBox<>(tables.toArray(new Table[0]));
        orderItemListModel = new DefaultListModel<>();
        orderItemList = new JList<>(orderItemListModel);

        JButton submitButton = new JButton("Add Order Item");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addOrderItem();
                } catch (InvalidOrderException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton removeButton = new JButton("Remove Order Item");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeOrderItem();
            }
        });

        JButton finalizeButton = new JButton("Finalize Order");
        finalizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizeOrder();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(new JLabel("Select Table:"));
        panel.add(tableComboBox);
        panel.add(new JLabel("Item:"));
        panel.add(itemField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(submitButton);
        panel.add(removeButton);
        panel.add(finalizeButton);
        panel.add(new JScrollPane(orderItemList)); // Add list to the dialog

        add(panel);
    }


    public OrderDialog(JFrame parent, List<Table> tables, Order existingOrder) {
        this(parent, tables);
        this.order = existingOrder;
        tableComboBox.setSelectedItem(existingOrder.getTable());


        for (OrderItem item : existingOrder.getItems()) {
            orderItemListModel.addElement(item);
        }
    }

    private void addOrderItem() throws InvalidOrderException {
        String item = itemField.getText();
        int quantity;

        try {
            quantity = Integer.parseInt(quantityField.getText());
            if (quantity <= 0) {
                throw new NumberFormatException("Quantity must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity: " + e.getMessage());
            return;
        }

        if (order == null) {
            order = new Order((Table) tableComboBox.getSelectedItem());
        }


        boolean itemExists = false;
        for (OrderItem existingItem : order.getItems()) {
            if (existingItem.getName().equals(item)) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            order.addItem(item, quantity);
            orderItemListModel.addElement(new OrderItem(item, quantity));
        } else {

            orderItemListModel.clear();
            for (OrderItem existingItem : order.getItems()) {
                orderItemListModel.addElement(existingItem);
            }
        }

        itemField.setText("");
        quantityField.setText("");


        ((MainWindow) getParent()).updateOrderArea(order);
        JOptionPane.showMessageDialog(this, "Item added to order.");
    }

    private void removeOrderItem() {
        OrderItem selectedItem = orderItemList.getSelectedValue();
        if (selectedItem != null) {
            order.getItems().remove(selectedItem);
            orderItemListModel.removeElement(selectedItem);
            JOptionPane.showMessageDialog(this, "Item removed from order.");
        } else {
            JOptionPane.showMessageDialog(this, "No item selected to remove.");
        }
    }

    private void finalizeOrder() {
        if (order == null || order.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items in order.");
            return;
        }
        dispose();
    }

    public Order getOrder() {
        return order;
    }
}
