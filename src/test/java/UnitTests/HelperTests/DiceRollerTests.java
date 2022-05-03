package UnitTests.HelperTests;

import mitchell.dnd.dndzombieorganiser.core.DiceRoller;
import mitchell.dnd.dndzombieorganiser.data.pojo.Pair;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DiceRollerTests {

    DiceRoller diceRoller;

    @BeforeEach
    public void setUp() {
        diceRoller = new DiceRoller();
    }

    @ParameterizedTest(name = "[{index}] Testing for value = {arguments}")
    @ValueSource(ints = {4, 8, 12, 20, 100})
    @DisplayName("Rolling a D{arguments} returns between 1 to {arguments}")
    public void rollingADFourReturnsBetweenOneAndFour(int dice) {
        int value = diceRoller.rollDice(dice);
        assertTrue(1 <= value && value <= dice, "Value out of range of " + dice + ": " + value );
    }

    @Test
    public void averageReturnsAverage() {
        List<Pair> history = new ArrayList<>();
        history.add(new Pair(4, 1));
        history.add(new Pair(4, 2));
        history.add(new Pair(4, 3));
        history.add(new Pair(4, 4));
        history.add(new Pair(4, 1));
        history.add(new Pair(4, 2));
        history.add(new Pair(4, 3));
        history.add(new Pair(4, 4));

        diceRoller.addHistory(history);
        assertEquals(2.5, diceRoller.averageRoll(4));
    }

//    @Test
    public void averageDiceRolls() {
        for (int i = 0; i < 10000; i++) {
            diceRoller.rollDice(4);
            diceRoller.rollDice(8);
            diceRoller.rollDice(12);
            diceRoller.rollDice(20);
            diceRoller.rollDice(100);
        }
        System.out.println(diceRoller.getStats());
    }

}
