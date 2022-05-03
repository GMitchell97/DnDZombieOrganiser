package mitchell.dnd.dndzombieorganiser.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;
import java.util.Map;

public class Helper {

    public static boolean validateCreatureType(String type) {
        int status = 500;
        try {
            status = getCreatureType(type).getStatusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return status == 200;
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

    public static boolean validateWeapon(String weapon) {
        int status = 500;
        try {
            status = getEquipment(weapon).getStatusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return status == 200;
    }

    public static boolean validateArmour(String armour) {
        int status = 500;
        try {
            status = getEquipment(armour).getStatusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return status == 200;
    }

    public static CallManager getEquipment(String item) throws IOException, InterruptedException {
        item = formatStrings(item);
        return new CallManager(APIConnectionManager.getEquipment().getEquipment(item));
    }

    private static String formatStrings(String string) {
        String newString = string.toLowerCase();
        newString = newString.trim();
        return newString;
    }

    public static void addZombie(DataDTO data, Map<String, String> args) throws IOException, InterruptedException {
        ZombieDTO newZombie = new ZombieDTO();

        CallManager callManager = getCreatureRace(args.get("race"));
        RaceDTO raceDTO = new RaceDTO(callManager.getJson().orElseThrow());
        callManager = getCreatureType(args.get("type"));
        JsonNode typeJson = callManager.getJson().orElseThrow();

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
            health += zombieDTO.getAbilityScoreModifier("constitution");
        }
        zombieDTO.setHp(Integer.toString(health));
    }
}
