package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;

public class ScratchGame {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java -jar <your-jar-file> --config config.json --betting-amount 100");
            return;
        }

        String configPath = null;
        int bettingAmount = 0;

        for (int i = 0; i < args.length; i++) {
            if ("--config".equals(args[i])) {
                configPath = args[++i];
            } else if ("--betting-amount".equals(args[i])) {
                bettingAmount = Integer.parseInt(args[++i]);
            }
        }

        if (configPath == null) {
            System.err.println("Config file path is required.");
            return;
        }

        try {
            ConfigLoader loader = new ConfigLoader();
            Config config = loader.loadConfig(configPath);

            MatrixGenerator generator = new MatrixGenerator(config);
            String[][] matrix = generator.generateMatrix();
            printMatrix(matrix);

            RewardCalculator calculator = new RewardCalculator(config);
            RewardCalculator.Result result = calculator.calculateReward(matrix);

            // Print results in required format
            System.out.println(new ObjectMapper().writeValueAsString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (String[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}