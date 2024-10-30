package Ferestre;

import models.Comanda;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FereastraComenzi extends JFrame {

    public FereastraComenzi(List<Comanda> listaComenzi) {
        setTitle("Comenzi Active");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] coloane = {"Număr Comandă", "Produs", "Cantitate", "Preț Total"};
        String[][] date = new String[listaComenzi.size()][4];

        for (int i = 0; i < listaComenzi.size(); i++) {
            date[i][0] = String.valueOf(listaComenzi.get(i).getNumarComanda());
            date[i][1] = listaComenzi.get(i).getProdus().getNume();
            date[i][2] = String.valueOf(listaComenzi.get(i).getCantitate());
            date[i][3] = String.valueOf(listaComenzi.get(i).getPretTotal());
        }

        JTable tabelComenzi = new JTable(date, coloane);
        JScrollPane scrollPane = new JScrollPane(tabelComenzi);

        add(scrollPane, BorderLayout.CENTER);
    }
}
