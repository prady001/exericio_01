package org.example.csv;

import org.example.JogadorTemporada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CsvReader {
    private String filePath;
    private ArrayList<JogadorTemporada> data;

    public CsvReader(String filePath) {
        this.filePath = filePath;
        this.data = new ArrayList<>();
    }

    public void readCsv() {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");

                    JogadorTemporada jogadorTemporada = new JogadorTemporada();
                    jogadorTemporada.jogador = values[1];
                    jogadorTemporada.time = values[2];
                    jogadorTemporada.pais = values[7];
                    jogadorTemporada.idade = (int) Float.parseFloat(values[3]);
                    jogadorTemporada.pontos = Float.parseFloat(values[12]);
                    jogadorTemporada.rebotes = Float.parseFloat(values[13]);
                    jogadorTemporada.assistencias = Float.parseFloat(values[14]);
                    jogadorTemporada.temporada = values[21];


                    data.add(jogadorTemporada);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<JogadorTemporada> getData() {
        return new ArrayList<>(data);
    }

    public JogadorTemporada getRow(int index) {
        if (index >= 0 && index < data.size()) {
            return data.get(index);
        }
        return null;
    }

}
