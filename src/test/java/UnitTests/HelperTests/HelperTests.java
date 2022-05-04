package UnitTests.HelperTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import mitchell.dnd.dndzombieorganiser.core.Helper;
import mitchell.dnd.dndzombieorganiser.api.CallManager;
import mitchell.dnd.dndzombieorganiser.data.dto.DataDTO;
import mitchell.dnd.dndzombieorganiser.data.dto.RaceDTO;
import mitchell.dnd.dndzombieorganiser.data.properties.Rules;
import mitchell.dnd.dndzombieorganiser.data.dto.ZombieDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

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

    private static Stream<Arguments> AC() {
        return Stream.of(
                Arguments.of("none", "8"),
                Arguments.of("", "8"),
                Arguments.of("leather-armor", "9"),
                Arguments.of("half-plate-armor", "13"),
                Arguments.of("splint-armor", "17")
        );
    }

    @ParameterizedTest()
    @MethodSource("AC")
    public void calculateACreturnsCorrectValue(String armour, String exp) {
        ZombieDTO zombie = new ZombieDTO();
        zombie.setAbilityScore("dexterity", 6);
        zombie.setArmour(armour);

        calculateAC(zombie);

        assertEquals(exp, zombie.getAc());

    }
}
