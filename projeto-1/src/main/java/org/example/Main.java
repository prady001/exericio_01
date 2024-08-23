package org.example;

import org.example.csv.CsvReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        CsvReader reader = new CsvReader("nba.csv");
        reader.readCsv();

        ArrayList<JogadorTemporada> data = reader.getData();

//        for (JogadorTemporada row : data) {
//            System.out.println(row.jogador);
//            System.out.println(row.pais);
//            System.out.println(row.pontos);
//            System.out.println(row.temporada);
//        }

        // 1 - Jogador com a melhor média de pontos por temporada
        // Criando dois mapas para armazenar o total de pontos e o total de temporadas jogadas por cada jogador
        Map<String, Double> totalPontos = new HashMap<>();
        Map<String, Integer> totalTemporadas = new HashMap<>();

        // Iterando sobre os dados para acumular os pontos e contar as temporadas de cada jogador
        for (JogadorTemporada row : data) {
            // Adiciona os pontos do jogador à soma total de pontos
            totalPontos.put(row.jogador, totalPontos.getOrDefault(row.jogador, 0.0) + row.pontos);
            // Incrementa o contador de temporadas para o jogador
            totalTemporadas.put(row.jogador, totalTemporadas.getOrDefault(row.jogador, 0) + 1);
        }

        // Variáveis para armazenar o nome do jogador com a melhor média de pontos e a própria média de pontos
        String melhorJogador = null;
        double melhorMediaPontos = 0.0;

        // Iterando sobre os jogadores para calcular a média de pontos por temporada
        for (String jogador : totalPontos.keySet()) {
            // Calcula a média de pontos do jogador dividindo o total de pontos pelo número de temporadas
            double mediaPontos = totalPontos.get(jogador) / totalTemporadas.get(jogador);
            // Verifica se a média de pontos atual é maior que a melhor média encontrada até agora
            if (mediaPontos > melhorMediaPontos) {
                // Se for, atualiza a melhor média e o nome do jogador correspondente
                melhorMediaPontos = mediaPontos;
                melhorJogador = jogador;
            }
        }

        // Exibe o jogador com a melhor média de pontos por temporada e a média correspondente
        System.out.println("Jogador com a melhor média de pontos por temporada: " + melhorJogador + " com média de " + melhorMediaPontos + " pontos.");


        // 2 - Jogador com a melhor média de assistências por temporada
        Map<String, Double> totalAssistencias = new HashMap<>();
        Map<String, Integer> totalTemporadasAssistencias = new HashMap<>();

        for (JogadorTemporada row : data) {
            totalAssistencias.put(row.jogador, totalAssistencias.getOrDefault(row.jogador, 0.0) + row.assistencias);
            totalTemporadasAssistencias.put(row.jogador, totalTemporadasAssistencias.getOrDefault(row.jogador, 0) + 1);
        }

        String melhorJogadorAssistencias = null;
        double melhorMediaAssistencias = 0.0;

        for (String jogador : totalAssistencias.keySet()) {
            double mediaAssistencias = totalAssistencias.get(jogador) / totalTemporadas.get(jogador);
            if (mediaAssistencias > melhorMediaAssistencias) {
                melhorMediaAssistencias = mediaAssistencias;
                melhorJogadorAssistencias = jogador;
            }
        }

        System.out.println("Jogador com a melhor média de assistências por temporada: " + melhorJogadorAssistencias + "ocm média de " + melhorMediaAssistencias);

        // 3 - Jogador com a melhor média de rebotes
        Map<String, Double> totalRebotes = new HashMap<>();
        Map<String, Integer> totalTemporadasRebotes = new HashMap<>();

        for (JogadorTemporada row : data) {
            totalRebotes.put(row.jogador, totalRebotes.getOrDefault(row.jogador, 0.0) + row.rebotes);
            totalTemporadasRebotes.put(row.jogador, totalTemporadasRebotes.getOrDefault(row.jogador, 0) + 1);
        }

        String melhorJogadorRebotes = null;
        Double melhorMediaRebotes = 0.0;

        for (String jogador : totalRebotes.keySet()) {
            double mediaRebotes = totalRebotes.get(jogador) / totalTemporadasRebotes.get(jogador);
            if (mediaRebotes > melhorMediaRebotes) {
                melhorMediaRebotes = mediaRebotes;
                melhorJogadorRebotes = jogador;
            }
        }

        System.out.println("Jogador com a melhor média de rebotes por temporada: " + melhorJogadorRebotes + " com média de " + melhorMediaRebotes + " rebotes.");

        // 4 - Jogador mais novo entre todas as temporadas
        String jogadorMaisNovo = null;
        int menorIdade = Integer.MAX_VALUE;

        for (JogadorTemporada row : data) {
            if (row.idade < menorIdade) {
                menorIdade = row.idade;
                jogadorMaisNovo = row.jogador;
            }
        }

        System.out.println("Jogador mais novo entre todas as temporadas: " + jogadorMaisNovo + " com " + menorIdade + " anos.");

        // 5 - Jogador mais velho entre todas as temporadas
        String jogadorMaisVelho = null;
        int maiorIdade = Integer.MIN_VALUE;

        for (JogadorTemporada row : data) {
            if (row.idade > maiorIdade) {
                maiorIdade = row.idade;
                jogadorMaisVelho = row.jogador;
            }
        }

        System.out.println("Jogador mais velho entre todas as temporadas: " + jogadorMaisVelho + " com " + maiorIdade + " anos.");

        // 6 - Quantas vezes cada jogador mudou de time

        Map<String, String> ultimoTime = new HashMap<>();
        Map<String, Integer> mudancasDeTime = new HashMap<>();

        for (JogadorTemporada row : data) {
            String jogador = row.jogador;
            String timeAtual = row.time;

            if (ultimoTime.containsKey(jogador)) {
                if (!ultimoTime.get(jogador).equals(timeAtual)) {
                    mudancasDeTime.put(jogador, mudancasDeTime.getOrDefault(jogador, 0) + 1);
                }
            }

            ultimoTime.put(jogador, timeAtual);
        }

        for (Map.Entry<String, Integer> entry : mudancasDeTime.entrySet()) {
            System.out.println("Jogador: " + entry.getKey() + " mudou de time " + entry.getValue() + " vezes.");
        }

    }

}