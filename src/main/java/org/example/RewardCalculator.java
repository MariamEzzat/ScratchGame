package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RewardCalculator {
    private final Config config;

    public RewardCalculator(Config config) {
        this.config = config;
    }

    public Result calculateReward(String[][] matrix) {
        if (matrix == null || config == null || config.getSymbols() == null || config.getWin_combinations() == null) {
            throw new IllegalArgumentException("Matrix or config is null");
        }
        Map<String, Integer> symbolCounts = countSymbols(matrix);
        Map<String, List<String>> appliedWinningCombinations = new HashMap<>();
        List<String> appliedBonusSymbols = new ArrayList<>();
        double baseReward = 0;


        for (Map.Entry<String, Integer> entry : symbolCounts.entrySet()) {
            String symbol = entry.getKey();
            int count = entry.getValue();
            Config.Symbol symbolConfig = config.getSymbols().get(symbol);
            if (symbolConfig == null) continue;

            double symbolReward = symbolConfig.getReward_multiplier();
            double symbolRewardMultiplier = 1;

            for (Map.Entry<String, Config.WinCombination> winEntry : config.getWin_combinations().entrySet()) {
                Config.WinCombination win = winEntry.getValue();
                if (win.getWhen().equals("same_symbols") && count >= win.getCount()) {
                    symbolRewardMultiplier *= win.getReward_multiplier();
                    appliedWinningCombinations.computeIfAbsent(symbol, k -> new ArrayList<>());
                    appliedWinningCombinations.get(symbol).add(winEntry.getKey());
                }
            }

            double symbolTotalReward = symbolReward * symbolRewardMultiplier;
            baseReward += symbolTotalReward;
        }


        double totalReward = baseReward;
        for (Map.Entry<String, Integer> entry : symbolCounts.entrySet()) {
            String symbol = entry.getKey();
            Config.Symbol symbolConfig = config.getSymbols().get(symbol);
            if (symbolConfig != null && symbolConfig.getType().equals("bonus")) {
                appliedBonusSymbols.add(symbol);
                totalReward = applyBonus(totalReward, symbol);
            }
        }

        String appliedBonusSymbol = appliedBonusSymbols.isEmpty() ? null : appliedBonusSymbols.get(0); // Use the first bonus for output

        return new Result(totalReward, appliedWinningCombinations, appliedBonusSymbol);
    }

    private Map<String, Integer> countSymbols(String[][] matrix) {
        Map<String, Integer> counts = new HashMap<>();
        for (String[] row : matrix) {
            for (String symbol : row) {
                counts.put(symbol, counts.getOrDefault(symbol, 0) + 1);
            }
        }
        return counts;
    }

    private double applyBonus(double reward, String bonusSymbol) {
        if (bonusSymbol != null) {
            Config.Symbol bonus = config.getSymbols().get(bonusSymbol);
            if (bonus == null) {
                return reward;
            }

            switch (bonus.getImpact()) {
                case "multiply_reward":
                    return reward * bonus.getReward_multiplier();
                case "extra_bonus":
                    return reward + bonus.getExtra();
                case "miss":

                    return reward;

                default:
                    return reward;
            }
        }
        return reward;
    }


    public static class Result {
        private double reward;
        private Map<String, List<String>> appliedWinningCombinations;
        private String appliedBonusSymbol;

        public Result(double reward, Map<String, List<String>> appliedWinningCombinations, String appliedBonusSymbol) {
            this.reward = reward;
            this.appliedWinningCombinations = appliedWinningCombinations;
            this.appliedBonusSymbol = appliedBonusSymbol;
        }

        public double getReward() {
            return reward;
        }

        public void setReward(double reward) {
            this.reward = reward;
        }

        public Map<String, List<String>> getAppliedWinningCombinations() {
            return appliedWinningCombinations;
        }

        public void setAppliedWinningCombinations(Map<String, List<String>> appliedWinningCombinations) {
            this.appliedWinningCombinations = appliedWinningCombinations;
        }

        public String getAppliedBonusSymbol() {
            return appliedBonusSymbol;
        }

        public void setAppliedBonusSymbol(String appliedBonusSymbol) {
            this.appliedBonusSymbol = appliedBonusSymbol;
        }
    }
}
