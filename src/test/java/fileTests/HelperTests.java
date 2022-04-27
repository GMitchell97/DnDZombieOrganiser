package fileTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mitchell.dnd.dndzombieorganiser.core.Helper;
import mitchell.dnd.dndzombieorganiser.api.CallManager;
import mitchell.dnd.dndzombieorganiser.data.DataDTO;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static mitchell.dnd.dndzombieorganiser.core.Helper.*;
import static org.junit.jupiter.api.Assertions.*;

public class HelperTests {

    @ParameterizedTest(name = "[{index}] Testing for value = {arguments}")
    @ValueSource(strings = {"GUARD", "Guard", "guard"})
    public void validateCreatureTypeReturnsTrue(String type) throws IOException, InterruptedException {
        assertEquals(true, validateCreatureType(type));
    }

    @ParameterizedTest(name = "[{index}] Testing for value = {arguments}")
    @ValueSource(strings = {"efw"})
    public void validateCreatureTypeReturnsFalse(String type) throws IOException, InterruptedException {
        assertEquals(false, validateCreatureType(type));
    }

    @ParameterizedTest(name = "[{index}] Testing for value = {arguments}")
    @ValueSource(strings = {"Human", "DRAGONBORN"})
    public void validateCreatureRaceReturnsTrue(String race) {
        assertEquals(true, validateCreatureRace(race));
    }

    @ParameterizedTest(name = "[{index}] Testing for value = {arguments}")
    @ValueSource(strings = {"Humanssss", ""})
    public void validateCreatureRaceReturnsFalse(String race) {
        assertEquals(false, validateCreatureRace(race));
    }

    @Test
    public void getCreatureTypeReturns200() throws IOException, InterruptedException {
        assertEquals(200, getCreatureRace("human").getStatusCode());
    }

    @Test
    public void addZombieAddsAZombie() throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        DataDTO data = new DataDTO();

        CallManager callMan = getCreatureType("guard");
        JsonNode typeJson = mapper.readTree(callMan.getJson());
        callMan = getCreatureRace("Human");
        JsonNode raceJson = mapper.readTree(callMan.getJson());
        Helper.addZombie(data, raceJson, typeJson);

        ZombieDTO zombie = data.getZombies().get(0);

        assertEquals("13", zombie.getStrength());
    }
}
