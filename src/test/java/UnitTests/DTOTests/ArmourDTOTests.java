package UnitTests.DTOTests;

import mitchell.dnd.dndzombieorganiser.core.Helper;
import mitchell.dnd.dndzombieorganiser.data.dto.ArmourDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArmourDTOTests {

    private ArmourDTO armourDTO;

    @BeforeEach
    public void setup() {
        armourDTO = new ArmourDTO(Helper.getEquipment("leather-armor").getJson().orElseThrow());
    }

    @Test
    public void negativeGetMaxDexBonus() {
        assertEquals(10, armourDTO.getMaxDexBonus());
    }
}
