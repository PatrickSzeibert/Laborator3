package Ferestre;

import models.Masa;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FereastraMeseLibere extends JFrame {

    public FereastraMeseLibere(List<Masa> meseLibere) {
        setTitle("Mese Libere");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] coloane = {"Număr Masă", "Capacitate"};
        String[][] date = new String[meseLibere.size()][2];

        for (int i = 0; i < meseLibere.size(); i++) {
            date[i][0] = String.valueOf(meseLibere.get(i).getNumarMasa());
            date[i][1] = String.valueOf(meseLibere.get(i).getCapacitate());
        }

        JTable tabelMeseLibere = new JTable(date, coloane);
        JScrollPane scrollPane = new JScrollPane(tabelMeseLibere);

        add(scrollPane, BorderLayout.CENTER);
    }
}
