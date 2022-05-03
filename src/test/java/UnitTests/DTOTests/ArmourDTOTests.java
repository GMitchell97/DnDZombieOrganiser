package UnitTests.DTOTests;

import mitchell.dnd.dndzombieorganiser.core.Helper;
import mitchell.dnd.dndzombieorganiser.data.dto.ArmourDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArmourDTOTests {

    private ArmourDTO armourDTO;

    @Test
    public void LightArmour() {
        armourDTO = new ArmourDTO(Helper.getEquipment("leather-armor").getJson().orElseThrow());
        assertEquals(11, armourDTO.getBaseAC());
        assertEquals(10, armourDTO.getMaxDexBonus());
        assertEquals(true, armourDTO.isDexBonus());
        assertEquals(false, armourDTO.isStealthDisadvantage());
        assertEquals(0, armourDTO.getStrengthRequirement());
    }

    @Test
    public void MediumArmour() {
        armourDTO = new ArmourDTO(Helper.getEquipment("half-plate-armor").getJson().orElseThrow());
        assertEquals(15, armourDTO.getBaseAC());
        assertEquals(2, armourDTO.getMaxDexBonus());
        assertEquals(true, armourDTO.isDexBonus());
        assertEquals(true, armourDTO.isStealthDisadvantage());
        assertEquals(0, armourDTO.getStrengthRequirement());
    }

    @Test
    public void HeavyArmour() {
        armourDTO = new ArmourDTO(Helper.getEquipment("plate-armor").getJson().orElseThrow());
        assertEquals(18, armourDTO.getBaseAC());
        assertEquals(10, armourDTO.getMaxDexBonus());
        assertEquals(false, armourDTO.isDexBonus());
        assertEquals(true, armourDTO.isStealthDisadvantage());
        assertEquals(15, armourDTO.getStrengthRequirement());
    }
}
