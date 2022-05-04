package UnitTests.DataTests;

import mitchell.dnd.dndzombieorganiser.data.pojo.Ability;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AbilityTests {

    private Ability ability;

    @Test
    public void AbilityCheck() {
        ability = new Ability("strength", 6);

        Assertions.assertEquals(6, ability.getValue());
        Assertions.assertEquals("strength", ability.getName());
        Assertions.assertEquals(-2, ability.getModifier());

        ability.setValue(19);
        Assertions.assertEquals(19, ability.getValue());
        Assertions.assertEquals(4, ability.getModifier());

        ability.setName("dexterity");
        Assertions.assertEquals("dexterity", ability.getName());
    }


}
