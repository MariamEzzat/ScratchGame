package org.example;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatrixGenerator {
    private final Config config;
    private final Random random = new Random();

    public MatrixGenerator(Config config) {
        this.config = config;
    }

    public String[][] generateMatrix() {
        String[][] matrix = new String[config.getRows()][config.getColumns()];

        Map<String, Integer> standardSymbolsProbabilities = extractProbabilities(config.getProbabilities().getStandardSymbols());
        Map<String, Integer> bonusSymbolsProbabilities = extractProbabilities(config.getProbabilities().getBonusSymbols().getSymbols());

        for (int row = 0; row < config.getRows(); row++) {
            for (int col = 0; col < config.getColumns(); col++) {
                if (random.nextDouble() < 0.1) {
                    matrix[row][col] = getRandomSymbol(bonusSymbolsProbabilities);
                } else {
                    matrix[row][col] = getRandomSymbol(standardSymbolsProbabilities);
                }
            }
        }

        return matrix;
    }

    private Map<String, Integer> extractProbabilities(List<Config.StandardSymbol> symbolsList) {
        Map<String, Integer> probabilities = new HashMap<>();
        for (Config.StandardSymbol symbol : symbolsList) {
            probabilities.putAll(symbol.getSymbols());
        }
        return probabilities;
    }

    private Map<String, Integer> extractProbabilities(Map<String, Integer> symbols) {
        return new HashMap<>(symbols);
    }

    private String getRandomSymbol(Map<String, Integer> symbols) {
        int total = symbols.values().stream().mapToInt(Integer::intValue).sum();
        int randomValue = random.nextInt(total);
        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            if (randomValue < entry.getValue()) {
                return entry.getKey();
            }
            randomValue -= entry.getValue();
        }
        throw new RuntimeException("Failed to generate a random symbol");
    }
}