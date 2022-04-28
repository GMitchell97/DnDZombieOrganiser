package fileTests;

import mitchell.dnd.dndzombieorganiser.UI.ZombieWrapper;
import mitchell.dnd.dndzombieorganiser.data.Ability;
import mitchell.dnd.dndzombieorganiser.data.Rules;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ZombieDTOTests {

    @Test
    public void wrapperReturnsSameValue() {
        ZombieDTO zombie = new ZombieDTO();

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
}
