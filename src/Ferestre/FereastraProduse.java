package Ferestre;

import models.Produs;
import models.ProdusAlimentar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FereastraProduse extends JFrame {
    private JTable tabelProduse;
    private List<Produs> listaProduse;
    private DefaultTableModel modelTabel;

    public FereastraProduse(List<Produs> listaProduse) {
        this.listaProduse = listaProduse;

        setTitle("Produse Disponibile");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        String[] coloane = {"Nume", "Preț", "Detalii"};
        modelTabel = new DefaultTableModel(coloane, 0);
        tabelProduse = new JTable(modelTabel);
        JScrollPane scrollPane = new JScrollPane(tabelProduse);


        JPanel panelAdaugare = new JPanel(new GridLayout(4, 2));
        JLabel labelNume = new JLabel("Nume Produs:");
        JTextField textNume = new JTextField();
        JLabel labelPret = new JLabel("Preț Produs:");
        JTextField textPret = new JTextField();
        JLabel labelTermen = new JLabel("Termen Valabilitate (opțional):");
        JTextField textTermen = new JTextField();
        JButton butonAdauga = new JButton("Adaugă Produs");


        panelAdaugare.add(labelNume);
        panelAdaugare.add(textNume);
        panelAdaugare.add(labelPret);
        panelAdaugare.add(textPret);
        panelAdaugare.add(labelTermen);
        panelAdaugare.add(textTermen);
        panelAdaugare.add(new JLabel()); // Spacer
        panelAdaugare.add(butonAdauga);


        actualizeazaTabel();


        butonAdauga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeProdus = textNume.getText();
                String pretProdusText = textPret.getText();
                String termenValabilitate = textTermen.getText();

                if (numeProdus.isEmpty() || pretProdusText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Completați toate câmpurile obligatorii!");
                    return;
                }

                try {
                    double pretProdus = Double.parseDouble(pretProdusText);


                    Produs produsNou;
                    if (!termenValabilitate.isEmpty()) {
                        produsNou = new ProdusAlimentar(numeProdus, pretProdus, termenValabilitate);
                    } else {
                        produsNou = new Produs(numeProdus, pretProdus);
                    }


                    listaProduse.add(produsNou);
                    actualizeazaTabel();


                    textNume.setText("");
                    textPret.setText("");
                    textTermen.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduceți un preț valid!");
                }
            }
        });


        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelAdaugare, BorderLayout.SOUTH);
    }


    private void actualizeazaTabel() {
        modelTabel.setRowCount(0);
        for (Produs produs : listaProduse) {
            Object[] rowData = {
                    produs.getNume(),
                    produs.getPret(),
                    (produs instanceof ProdusAlimentar) ? ((ProdusAlimentar) produs).getTermenValabilitate() : ""
            };
            modelTabel.addRow(rowData);
        }
    }
}
