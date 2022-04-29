package fileTests;

import mitchell.dnd.dndzombieorganiser.UI.ZombieWrapper;
import mitchell.dnd.dndzombieorganiser.data.Ability;
import mitchell.dnd.dndzombieorganiser.data.Rules;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ZombieDTOTests {

    private ZombieDTO zombie;

    @BeforeEach
    public void setUp() {
        zombie = new ZombieDTO();
    }

    @Test
    public void wrapperReturnsSameValue() {

        Arrays.stream(Rules.ability.values()).sequential().forEach(a ->
                zombie.getAbilityScores().add(new Ability(a.toString(), 10))
        );

        ZombieWrapper zombieWrapper = new ZombieWrapper(zombie);

        assertEquals("10", zombieWrapper.getStrength());
        assertEquals("10", zombieWrapper.getDexterity());
        assertEquals("10", zombieWrapper.getConstitution());
        assertEquals("10", zombieWrapper.getIntelligence());
        assertEquals("10", zombieWrapper.getWisdom());
        assertEquals("10", zombieWrapper.getCharisma());
    }

    @Test
    public void setAbilityScoreSetsScore() {

        zombie.setAbilityScore("strength", 12);
        ZombieWrapper zombieWrapper = new ZombieWrapper(zombie);

        assertEquals(12, zombie.getAbilityScore("strength"));
        assertEquals("12", zombieWrapper.getStrength());
    }

    @Test
    public void negativeSetAbilityScoreIgnoresScore() {

        zombie.setAbilityScore("strengsth", 12);

        assertEquals(0, zombie.getAbilityScores().size());
        assertEquals(0, zombie.getAbilityScore("strengsth"));
    }
}
