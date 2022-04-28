package fileTests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import mitchell.dnd.dndzombieorganiser.data.Rules;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class RulesTests {

    Rules rules;

    @BeforeEach
    public void setup() {
        rules = new Rules();
    }

    private static Stream<Arguments> adjustments() {
        return Stream.of(
                Arguments.of(Rules.creature.zombie, Rules.type.flat, Rules.ability.strength, 1),
                Arguments.of(Rules.creature.skeleton, Rules.type.scale, Rules.ability.strength, 1),
                Arguments.of(Rules.creature.skeleton, Rules.type.set, Rules.ability.intelligence, 6)
        );
    }

    @ParameterizedTest()
    @MethodSource("adjustments")
    public void getAbilityScoreAdjustmentReturnsCorrectly(Rules.creature c,Rules.type t, Rules.ability a, int exp) {
        Assertions.assertEquals(exp, rules.getAbilityScoreAdjustment(c, t, a));
    }

    private static Stream<Arguments> negativeAdjustments() {
        return Stream.of(
                Arguments.of(Rules.creature.zombie, Rules.type.flat, Rules.ability.intelligence, 0),
                Arguments.of(Rules.creature.skeleton, Rules.type.set, Rules.ability.strength, 0)
        );
    }

    @ParameterizedTest()
    @MethodSource("negativeAdjustments")
    public void negativeGetAbilityScoreAdjustmentReturnsCorrectly(Rules.creature c,Rules.type t, Rules.ability a, int exp) {
        Assertions.assertEquals(exp, rules.getAbilityScoreAdjustment(c, t, a));
    }

}
