// gui/TableDialog.java
package gui;

import model.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableDialog extends JDialog {
    private JTextField numberField;
    private JTextField seatsField;
    private Table table;

    public TableDialog(JFrame parent) {
        super(parent, "New Table", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);

        numberField = new JTextField(10);
        seatsField = new JTextField(10);

        JButton submitButton = new JButton("Add Table");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitTable();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Table Number:"));
        panel.add(numberField);
        panel.add(new JLabel("Seats:"));
        panel.add(seatsField);
        panel.add(submitButton);

        add(panel);
    }

    private void submitTable() {
        int number = Integer.parseInt(numberField.getText());
        int seats = Integer.parseInt(seatsField.getText());
        table = new Table(number, seats);
        dispose();
    }

    public Table getTable() {
        return table;
    }
}
