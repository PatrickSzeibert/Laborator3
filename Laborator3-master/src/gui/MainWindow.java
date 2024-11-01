package gui;

import model.Order;
import model.Table;
import data.FileManager;
import threads.ClockUpdater;
import threads.OrderFileWatcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    private List<Table> tables;
    private List<Order> orders;
    private JTextArea ordersArea;
    private JLabel clockLabel;

    public MainWindow() {
        setTitle("Restaurant Management");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tables = new ArrayList<>();
        orders = new ArrayList<>();
        createTables();

        ordersArea = new JTextArea();
        ordersArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ordersArea);

        clockLabel = new JLabel("Current Time: ");
        add(clockLabel, BorderLayout.NORTH);

        JButton newOrderButton = new JButton("New Order");
        newOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newOrder();
            }
        });

        JButton updateOrderButton = new JButton("Update Order");
        updateOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOrder();
            }
        });

        JButton addTableButton = new JButton("Add Table");
        addTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTable();
            }
        });

        JButton saveButton = new JButton("Save Orders");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOrders();
            }
        });


        JButton releaseTableButton = new JButton("Release Table");
        releaseTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                releaseTable();
            }
        });

        JPanel panel = new JPanel();
        panel.add(newOrderButton);
        panel.add(updateOrderButton);
        panel.add(addTableButton);
        panel.add(saveButton);
        panel.add(releaseTableButton);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        setVisible(true);


        startThreads();
    }

    private void startThreads() {
        ClockUpdater clockUpdater = new ClockUpdater(clockLabel);
        Thread clockThread = new Thread(clockUpdater);
        clockThread.start();

        OrderFileWatcher fileWatcher = new OrderFileWatcher("orders.txt");
        Thread fileWatcherThread = new Thread(fileWatcher);
        fileWatcherThread.start();
    }

    private void createTables() {
        tables.add(new Table(1, 4));
        tables.add(new Table(2, 2));
        tables.add(new Table(3, 6));
    }

    private void newOrder() {
        OrderDialog dialog = new OrderDialog(this, tables);
        dialog.setVisible(true);
        Order order = dialog.getOrder();
        if (order != null) {
            orders.add(order);
            updateOrdersArea();
        }
    }

    private void updateOrder() {
        String input = JOptionPane.showInputDialog(this, "Enter order index to update (starting from 0):");
        try {
            int index = Integer.parseInt(input);
            if (index >= 0 && index < orders.size()) {
                Order order = orders.get(index);
                OrderDialog dialog = new OrderDialog(this, tables, order);
                dialog.setVisible(true);
                updateOrdersArea();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid index.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void addTable() {
        TableDialog dialog = new TableDialog(this);
        dialog.setVisible(true);
        Table table = dialog.getTable();
        if (table != null) {
            tables.add(table);
            JOptionPane.showMessageDialog(this, "Table added: " + table);
        }
    }

    private void deleteOrder() {
        String input = JOptionPane.showInputDialog(this, "Enter order index to delete (starting from 0):");
        try {
            int index = Integer.parseInt(input);
            if (index >= 0 && index < orders.size()) {
                orders.remove(index);
                updateOrdersArea();
                JOptionPane.showMessageDialog(this, "Order deleted.");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid index.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void releaseTable() {
        String input = JOptionPane.showInputDialog(this, "Enter table number to release:");
        try {
            int tableNumber = Integer.parseInt(input);
            Order orderToRemove = null;

            for (Order order : orders) {
                if (order.getTable().getNumber() == tableNumber) {
                    orderToRemove = order;
                    break;
                }
            }

            if (orderToRemove != null) {
                orders.remove(orderToRemove);
                orderToRemove.getTable().setOccupied(false);
                updateOrdersArea();
                JOptionPane.showMessageDialog(this, "Table " + tableNumber + " has been released.");
            } else {
                JOptionPane.showMessageDialog(this, "No active order for table " + tableNumber + ".");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void updateOrdersArea() {
        StringBuilder sb = new StringBuilder();
        for (Order order : orders) {
            sb.append(order.toString()).append("\n");
        }
        ordersArea.setText(sb.toString());
    }

    public void updateOrderArea(Order order) {
        ordersArea.append(order.toString() + "\n");
    }

    private void saveOrders() {
        List<String> orderData = new ArrayList<>();
        for (Order order : orders) {
            orderData.add(order.toString());
        }
        try {
            FileManager.saveToTextFile("orders.txt", orderData);
            JOptionPane.showMessageDialog(this, "Orders saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving orders: " + e.getMessage());
        }
    }
}
