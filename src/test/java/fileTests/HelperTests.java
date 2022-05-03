package fileTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mitchell.dnd.dndzombieorganiser.core.Helper;
import mitchell.dnd.dndzombieorganiser.api.CallManager;
import mitchell.dnd.dndzombieorganiser.data.DataDTO;
import mitchell.dnd.dndzombieorganiser.data.RaceDTO;
import mitchell.dnd.dndzombieorganiser.data.Rules;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;
import mitchell.dnd.dndzombieorganiser.data.Ability;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.Map;

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

        Helper.addZombie(data, Map.of(
                "type", "bandit",
                "race", "human",
                "armour", "none"
        ));

        ZombieDTO zombie = data.getZombies().get(0);

        Map<String, Integer> exp = Map.of(
                "strength", 13,
                "dexterity", 6,
                "constitution", 16,
                "intelligence", 3,
                "wisdom", 6,
                "charisma", 5
        );

        exp.forEach((n, v) ->
                assertEquals(v.intValue(), zombie.getAbilityScore(n), n + " was: " + zombie.getAbilityScore(n))
        );
        assertEquals("8", zombie.getAc());
        assertEquals("none", zombie.getArmour());
    }

    @Test
    public void raceDTOReturnsAbilityBonus() throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();

        CallManager callMan = getCreatureRace("Human");
        RaceDTO raceDTO = new RaceDTO(callMan.getJson().orElseThrow());

        assertEquals(1, raceDTO.getAbilityScoreBonus(Rules.ability.strength));
    }

    @Test
    public void negativeRaceDTOReturnsAbilityBonus() throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();

        CallManager callMan = getCreatureRace("elf");

        RaceDTO raceDTO = new RaceDTO(callMan.getJson().orElseThrow());

        assertEquals(0, raceDTO.getAbilityScoreBonus(Rules.ability.intelligence));
    }
}
