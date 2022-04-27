package mitchell.dnd.dndzombieorganiser.core;


import mitchell.dnd.dndzombieorganiser.data.Pair;

import java.util.*;

public class DiceRoller {

    private List<Pair> history = new ArrayList<>();
    private Random rand = new Random(System.currentTimeMillis() * System.nanoTime());

    public int rollDice(int dice) {
        int value = rand.nextInt(dice) + 1;
        history.add(new Pair(dice, value));
        return value;
    }

    public String getStats() {
        return "Average rolls are as follows:" +
                " d4: " + averageRoll(4) +
                " d8: " + averageRoll(8) +
                " d12: " + averageRoll(12) +
                " d20: " + averageRoll(20) +
                " d100: " + averageRoll(100) +
                ".";
    }

    public double averageRoll(int dice) {
        return history.stream().filter(p -> p.getA() == dice).mapToInt(Pair::getB).average().orElse(0.0);
    }

    public void addHistory(List<Pair> history) {
        this.history.addAll(history);
    }

    public List<Pair> getHistory() {
        return history;
    }
}
