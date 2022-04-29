package mitchell.dnd.dndzombieorganiser.core;

import com.fasterxml.jackson.databind.JsonNode;
import mitchell.dnd.dndzombieorganiser.Constants;
import mitchell.dnd.dndzombieorganiser.api.APIConnectionManager;
import mitchell.dnd.dndzombieorganiser.api.CallManager;
import mitchell.dnd.dndzombieorganiser.data.DataDTO;
import mitchell.dnd.dndzombieorganiser.data.RaceDTO;
import mitchell.dnd.dndzombieorganiser.data.Rules;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;
import mitchell.dnd.dndzombieorganiser.data.Ability;

import java.io.IOException;
import java.util.Arrays;

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

        Arrays.stream(Rules.ability.values()).sequential().forEach( a ->
                newZombie.getAbilityScores().add(new Ability(a.toString(), calculateAbilityScore(calculateCurrentAbilityScore(a, raceDTO, typeJson), Rules.creature.zombie, a)))
        );

        calculateHealth(newZombie, data);
        data.addZombie(newZombie);
    }

    public static int calculateCurrentAbilityScore(Rules.ability a,RaceDTO raceDTO, JsonNode typeJson) {
        int score = typeJson.get(a.toString()).asInt() + raceDTO.getAbilityScoreBonus(a);
        return score;
    }

    public static int calculateAbilityScore(int current, Rules.creature c, Rules.ability a) {
        Rules rules = new Rules();
        if (rules.getAbilityScoreAdjustment(c, Rules.type.set, a) == 0) {
            int newScore = (int) (Math.floor(current * rules.getAbilityScoreAdjustment(c, Rules.type.scale, a)) + rules.getAbilityScoreAdjustment(c, Rules.type.flat, a));
            return newScore;
        } else {
            return (int) rules.getAbilityScoreAdjustment(c, Rules.type.set, a);
        }
    }

    public static void calculateHealth(ZombieDTO zombieDTO, DataDTO dataDTO) {
        int health = 0;
        for (int i = 0; i < 3; i++) {
            health += dataDTO.getDiceRoller().rollDice(8);
            health += zombieDTO.getAbilityScore("constitution");
        }
        zombieDTO.setHp(Integer.toString(health));
    }
}
