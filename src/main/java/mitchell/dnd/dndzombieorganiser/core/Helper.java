package mitchell.dnd.dndzombieorganiser.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import mitchell.dnd.dndzombieorganiser.Constants;
import mitchell.dnd.dndzombieorganiser.api.APIConnectionManager;
import mitchell.dnd.dndzombieorganiser.api.CallManager;
import mitchell.dnd.dndzombieorganiser.data.DataDTO;
import mitchell.dnd.dndzombieorganiser.data.RaceDTO;
import mitchell.dnd.dndzombieorganiser.data.Rules;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Helper {

    public static boolean validateCreatureType(String type) throws IOException, InterruptedException {
        CallManager callMan = getCreatureType(type);
        return callMan.getStatusCode() == 200;
    }

    public static CallManager getCreatureType(String type) throws IOException, InterruptedException {
        type = formatStrings(type);
        return new CallManager(APIConnectionManager.getMonster().getMonster(type));
    }

    public static boolean validateCreatureRace(String race) {
        race = formatStrings(race);
        return Constants.RACES.contains(race);
    }

    public static CallManager getCreatureRace(String race) throws IOException, InterruptedException {
        race = formatStrings(race);
        return new CallManager(APIConnectionManager.getRace().getRace(race));
    }

    private static String formatStrings(String string) {
        String newString = string.toLowerCase();
        newString = newString.trim();
        return newString;
    }

    public static void addZombie(DataDTO data, JsonNode raceJson, JsonNode typeJson) {
        ZombieDTO newZombie = new ZombieDTO();
        RaceDTO raceDTO = new RaceDTO(raceJson);

        newZombie.setStrength(calculateAbilityScore(calculateCurrentAbilityScore(Rules.ability.strength, raceDTO, typeJson), Rules.creature.zombie, Rules.ability.strength));
        newZombie.setDexterity(calculateAbilityScore(calculateCurrentAbilityScore(Rules.ability.dexterity, raceDTO, typeJson), Rules.creature.zombie, Rules.ability.dexterity));
        newZombie.setConstitution(calculateAbilityScore(calculateCurrentAbilityScore(Rules.ability.constitution, raceDTO, typeJson), Rules.creature.zombie, Rules.ability.constitution));
        newZombie.setIntelligence(calculateAbilityScore(calculateCurrentAbilityScore(Rules.ability.intelligence, raceDTO, typeJson), Rules.creature.zombie, Rules.ability.intelligence));
        newZombie.setWisdom(calculateAbilityScore(calculateCurrentAbilityScore(Rules.ability.wisdom, raceDTO, typeJson), Rules.creature.zombie, Rules.ability.wisdom));
        newZombie.setCharisma(calculateAbilityScore(calculateCurrentAbilityScore(Rules.ability.charisma, raceDTO, typeJson), Rules.creature.zombie, Rules.ability.charisma));

        data.addZombie(newZombie);
    }

    public static int calculateCurrentAbilityScore(Rules.ability a,RaceDTO raceDTO, JsonNode typeJson) {
        int score = typeJson.get(a.toString()).asInt() + raceDTO.getAbilityScoreBonus(a);
        return score;
    }

    public static String calculateAbilityScore(int current, Rules.creature c, Rules.ability a) {
        Rules rules = new Rules();
        if (rules.getAbilityScoreAdjustment(c, Rules.type.set, a) == 0) {
            int newScore = (int) (Math.floor(current * rules.getAbilityScoreAdjustment(c, Rules.type.scale, a)) + rules.getAbilityScoreAdjustment(c, Rules.type.flat, a));
            return Integer.toString(newScore);
        } else {
            return Integer.toString((int) rules.getAbilityScoreAdjustment(c, Rules.type.set, a));
        }
    }

}
