

import org.example.Config;
import org.example.RewardCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RewardCalculatorTest {

    private RewardCalculator calculator;
    private Config config;

    @BeforeEach
    void setUp() {
        config = new Config();
        Map<String, Config.Symbol> symbols = new HashMap<>();
        symbols.put("A", new Config.Symbol("A", 10, "regular", "multiply_reward", 2));
        symbols.put("B", new Config.Symbol("B", 20, "bonus", "extra_bonus", 50));
        config.setSymbols(symbols);

        Map<String, Config.WinCombination> winCombinations = new HashMap<>();
        winCombinations.put("win1", new Config.WinCombination("same_symbols", 3, 1.5));
        config.setWin_combinations(winCombinations);

        calculator = new RewardCalculator(config);
    }



    @Test
    void testEmptyMatrix() {
        String[][] matrix = new String[0][0];

        RewardCalculator.Result result = calculator.calculateReward(matrix);

        assertEquals(0.0, result.getReward(), "Total reward should be 0.0 for empty matrix");
        assertEquals(new HashMap<>(), result.getAppliedWinningCombinations(), "Winning combinations should be empty");
        assertEquals(null, result.getAppliedBonusSymbol(), "No bonus symbol should be applied for empty matrix");
    }



    @Test
    void testNullConfig() {
        calculator = new RewardCalculator(null);

        String[][] matrix = {
                {"A", "A", "A"},
                {"B", "B", "B"}
        };

        assertThrows(IllegalArgumentException.class, () -> calculator.calculateReward(matrix),
                "Expected IllegalArgumentException for null config");
    }

    @Test
    void testNullMatrix() {
        String[][] matrix = null;

        assertThrows(IllegalArgumentException.class, () -> calculator.calculateReward(matrix),
                "Expected IllegalArgumentException for null matrix");
    }


}
