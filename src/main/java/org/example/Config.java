package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Config {

    private int columns;
    private int rows;
    private Map<String, Symbol> symbols;
    private Probabilities probabilities;
    private Map<String, WinCombination> win_combinations;

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Symbol> symbols) {
        this.symbols = symbols;
    }

    public Probabilities getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(Probabilities probabilities) {
        this.probabilities = probabilities;
    }

    public Map<String, WinCombination> getWin_combinations() {
        return win_combinations;
    }

    public void setWin_combinations(Map<String, WinCombination> win_combinations) {
        this.win_combinations = win_combinations;
    }

    public static class Symbol {
        private double reward_multiplier;
        private String type;
        private Double extra;
        private String impact;

        public Symbol(double v, String bonus, double v1, String extraBonus) {
        }
        public Symbol() {
        }

        public Symbol(String a, int i, String regular, String multiplyReward, int i1) {
        }

        public double getReward_multiplier() {
            return reward_multiplier;
        }

        public void setReward_multiplier(double reward_multiplier) {
            this.reward_multiplier = reward_multiplier;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Double getExtra() {
            return extra;
        }

        public void setExtra(Double extra) {
            this.extra = extra;
        }

        public String getImpact() {
            return impact;
        }

        public void setImpact(String impact) {
            this.impact = impact;
        }
    }

    public static class Probabilities {
        @JsonProperty("standard_symbols")
        private List<StandardSymbol> standardSymbols;
        @JsonProperty("bonus_symbols")
        private BonusSymbols bonusSymbols;

        public List<StandardSymbol> getStandardSymbols() {
            return standardSymbols;
        }

        public void setStandardSymbols(List<StandardSymbol> standardSymbols) {
            this.standardSymbols = standardSymbols;
        }

        public BonusSymbols getBonusSymbols() {
            return bonusSymbols;
        }

        public void setBonusSymbols(BonusSymbols bonusSymbols) {
            this.bonusSymbols = bonusSymbols;
        }


    }

    public static class StandardSymbol {
        private int column;
        private int row;
        private Map<String, Integer> symbols;

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public Map<String, Integer> getSymbols() {
            return symbols;
        }

        public void setSymbols(Map<String, Integer> symbols) {
            this.symbols = symbols;
        }
    }

    public static class BonusSymbols {
        private Map<String, Integer> symbols;

        public Map<String, Integer> getSymbols() {
            return symbols;
        }

        public void setSymbols(Map<String, Integer> symbols) {
            this.symbols = symbols;
        }
    }

    public static class WinCombination {
        private double reward_multiplier;
        private int count;
        private String group;
        private String when;
        private String[][] covered_areas;

        public WinCombination(double v, int i, String symbol, String sameSymbols, Object o) {
        }
        public WinCombination() {
        }

        public WinCombination(String sameSymbols, int i, double v) {
        }

        public double getReward_multiplier() {
            return reward_multiplier;
        }

        public void setReward_multiplier(double reward_multiplier) {
            this.reward_multiplier = reward_multiplier;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getWhen() {
            return when;
        }

        public void setWhen(String when) {
            this.when = when;
        }

        public String[][] getCovered_areas() {
            return covered_areas;
        }

        public void setCovered_areas(String[][] covered_areas) {
            this.covered_areas = covered_areas;
        }
    }


}
