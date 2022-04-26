package mitchell.dnd.dndzombieorganiser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import mitchell.dnd.dndzombieorganiser.api.APIConnectionManager;
import mitchell.dnd.dndzombieorganiser.api.CallManager;
import mitchell.dnd.dndzombieorganiser.data.DataDTO;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;

import java.io.IOException;
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
        newZombie.setStrength(typeJson.get("strength").toString());
        newZombie.setDexterity(typeJson.get("dexterity").toString());
        newZombie.setConstitution(typeJson.get("constitution").toString());
        newZombie.setIntelligence(typeJson.get("intelligence").toString());
        newZombie.setWisdom(typeJson.get("wisdom").toString());
        newZombie.setCharisma(typeJson.get("charisma").toString());

        data.getZombies().add(newZombie);
    }

}
