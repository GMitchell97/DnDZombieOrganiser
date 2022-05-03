package fileTests;

import mitchell.dnd.dndzombieorganiser.Constants;
import mitchell.dnd.dndzombieorganiser.core.Helper;
import mitchell.dnd.dndzombieorganiser.data.ArmourDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ArmourDTOTests {

    private ArmourDTO armourDTO;

    @BeforeEach
    public void setup() throws IOException, InterruptedException {
        armourDTO = new ArmourDTO(Helper.getEquipment("leather-armor").getJson().orElseThrow());
    }

    @Test
    public void negativeGetMaxDexBonus() {
        assertEquals(10, armourDTO.getMaxDexBonus());
    }
}
