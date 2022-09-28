package mitchell.dnd.dndzombieorganiser.core;

import com.fasterxml.jackson.databind.JsonNode;
import mitchell.dnd.dndzombieorganiser.Constants;
import mitchell.dnd.dndzombieorganiser.UI.ZombieWrapper;
import mitchell.dnd.dndzombieorganiser.api.APIConnectionManager;
import mitchell.dnd.dndzombieorganiser.api.CallManager;
import mitchell.dnd.dndzombieorganiser.data.builders.WeaponBuilder;
import mitchell.dnd.dndzombieorganiser.data.dto.ArmourDTO;
import mitchell.dnd.dndzombieorganiser.data.dto.DataDTO;
import mitchell.dnd.dndzombieorganiser.data.dto.RaceDTO;
import mitchell.dnd.dndzombieorganiser.data.dto.ZombieDTO;
import mitchell.dnd.dndzombieorganiser.data.pojo.Pair;
import mitchell.dnd.dndzombieorganiser.data.properties.Rules;
import mitchell.dnd.dndzombieorganiser.data.pojo.Ability;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Helper {

    public static boolean validateCreatureType(String type) {
        return getCreatureType(type).getStatusCode() == 200;
    }

    public static CallManager getCreatureType(String type) {
        type = formatStrings(type);
        return new CallManager(APIConnectionManager.getMonster(type));
    }

    public static boolean validateCreatureRace(String race) {
        race = formatStrings(race);
        return Constants.RACES.contains(race);
    }

    public static CallManager getCreatureRace(String race) {
        race = formatStrings(race);
        return new CallManager(APIConnectionManager.getRace(race));
    }

    public static boolean validateWeapon(String weapon) {
        return getEquipment(weapon).getStatusCode() == 200;
    }

    public static boolean validateArmour(String armour) {
        return getEquipment(armour).getStatusCode() == 200;
    }

    public static CallManager getEquipment(String item) {
        item = formatStrings(item);
        return new CallManager(APIConnectionManager.getEquipment(item));
    }

    private static String formatStrings(String string) {
        String newString = string.toLowerCase();
        newString = newString.trim();
        return newString;
    }

    public static void addZombie(DataDTO data, Map<String, String> args) {
        ZombieDTO newZombie = new ZombieDTO();
        Rules rules = new Rules();

        CallManager callManager = getCreatureRace(args.get("race"));
        RaceDTO raceDTO = new RaceDTO(callManager.getJson().orElseThrow());
        callManager = getCreatureType(args.get("type"));
        JsonNode typeJson = callManager.getJson().orElseThrow();
        newZombie.setArmour(args.get("armour"));

        Arrays.stream(Rules.ability.values()).sequential().forEach(a ->
                newZombie.getAbilityScores().add(new Ability(a.toString(), calculateAbilityScore(calculateCurrentAbilityScore(a, raceDTO, typeJson), Rules.creature.zombie, a)))
        );

        String walkSpeed = getCreatureType("zombie").getJson().orElseThrow().get("speed").get("walk").asText().substring(0,2);

        newZombie.setSpeed(Integer.parseInt(walkSpeed));
        calculateAC(newZombie);
        calculateHealth(newZombie, data);

        if (args.containsKey("melee")) {
            callManager = getEquipment(args.get("melee"));
            newZombie.addWeapon("melee", WeaponBuilder.createWeapon(callManager.getJson().orElseThrow()));
            WeaponBuilder.configWeapon("melee", newZombie, rules);
        } else {
            newZombie.addWeapon("melee", WeaponBuilder.createDefaultWeapon());
            WeaponBuilder.configWeapon("melee", newZombie, rules);
        }

        if (args.containsKey("ranged")) {
            callManager = getEquipment(args.get("ranged"));
            newZombie.addWeapon("ranged", WeaponBuilder.createWeapon(callManager.getJson().orElseThrow()));
            WeaponBuilder.configWeapon("ranged", newZombie, rules);
        }

        data.addZombie(newZombie);
    }

    public static int calculateCurrentAbilityScore(Rules.ability a,RaceDTO raceDTO, JsonNode typeJson) {
        return typeJson.get(a.toString()).asInt() + raceDTO.getAbilityScoreBonus(a);
    }

    public static int calculateAbilityScore(int current, Rules.creature c, Rules.ability a) {
        Rules rules = new Rules();
        if (rules.getAbilityScoreAdjustment(c, Rules.type.set, a) == 0) {
            return (int) (Math.floor(current * rules.getAbilityScoreAdjustment(c, Rules.type.scale, a)) + rules.getAbilityScoreAdjustment(c, Rules.type.flat, a));
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
        Rules rules = new Rules();
        if (rules.ownerHasUndeadThralls()) {
            health += rules.getOwnerLevel();
        }
        zombieDTO.setHp(Integer.toString(health));
    }

    public static void calculateAC(ZombieDTO zombie) {
        String armour = zombie.getArmour();
        int dexModifier = zombie.getAbilityScoreModifier("dexterity");
        if (armour.equals("none")) {
            int AC = 10 + dexModifier;
            zombie.setAc(AC);
        } else {
            ArmourDTO armourDTO = new ArmourDTO(getEquipment(armour).getJson().orElseThrow());
            if (armourDTO.isDexBonus()) {
                int AC = armourDTO.getBaseAC() + Math.min(zombie.getAbilityScoreModifier("dexterity"), armourDTO.getMaxDexBonus());
                zombie.setAc(AC);
            } else {
                zombie.setAc(armourDTO.getBaseAC());
            }
            if (armourDTO.isHeavy()) {
                zombie.setSpeed(zombie.getSpeed() - 5);
            }
        }
    }

    public static String attack(List<ZombieWrapper> selection, String attackType, Constants.RollType r, DataDTO data) {
        StringBuilder sb = new StringBuilder();
        List<ZombieDTO> selectedZombies = selection.stream().map(ZombieWrapper::getZombie).filter(z -> z.getWeapon(attackType) != null).toList();
        List<Pair> attacks = selectedZombies.stream().map(z -> z.getWeapon(attackType).attack(data.getDiceRoller(), r)).toList();
        for (int i = 0; i < attacks.size(); i++) {
            sb.append("Zombie ").append(selectedZombies.get(i).getId())
                    .append(" to Hit: ").append(attacks.get(i).getA())
                    .append(" for Damage of: ").append(attacks.get(i).getB())
                    .append("\n");
        }
        return sb.toString();
    }
}
