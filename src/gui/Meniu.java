package gui;

import Ferestre.FereastraComenzi;
import Ferestre.FereastraMeseLibere;
import Ferestre.FereastraProduse;
import models.Comanda;
import models.Masa;
import models.Produs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Meniu extends Login {
    private JPanel panel2;
    private JButton comnezi;
    private JButton produse;
    private JButton meseLibere;
    private JFrame frame2;

    public Meniu(){
        frame2=new JFrame("Meniu");
        frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame2.setPreferredSize(new Dimension(500,400));
        frame2.setResizable(false);

        frame2.add(panel2);
        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);


        comnezi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Comanda> listaComenzi = new ArrayList<>();
                listaComenzi.add(new Comanda(1, new Produs("Pizza", 25.99), 2));
                listaComenzi.add(new Comanda(2, new Produs("Soda", 5.00), 3));
                FereastraComenzi fereastraComenzi = new FereastraComenzi(listaComenzi);
                fereastraComenzi.setVisible(true);

            }
        });
        meseLibere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Masa> meseLibere = new ArrayList<>();
                meseLibere.add(new Masa(1, 4));
                meseLibere.add(new Masa(2, 6));
                FereastraMeseLibere fereastraMeseLibere = new FereastraMeseLibere(meseLibere);
                fereastraMeseLibere.setVisible(true);
            }
        });
        produse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Produs> listaProduse = new ArrayList<>();
                listaProduse.add(new Produs("Pizza", 25.99));
                listaProduse.add(new Produs("Salată", 15.50));
                listaProduse.add(new Produs("Soda", 5.00));

                FereastraProduse fereastraProduse = new FereastraProduse(listaProduse);
                fereastraProduse.setVisible(true);
            }
        });
    }

}
