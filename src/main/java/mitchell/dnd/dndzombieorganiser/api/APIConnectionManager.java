package mitchell.dnd.dndzombieorganiser.api;

import mitchell.dnd.dndzombieorganiser.Constants;

public class APIConnectionManager {

    private static final String BASEURL = "https://www.dnd5eapi.co/api";

    public static String getRace(String race) {
        if (Constants.RACES.contains(race))
            return BASEURL + "/races/" + race;
        return "";
    }

    public static String getMonster(String monster) {
        return BASEURL + "/monsters/" + monster;
    }

    public static String getEquipment(String equipment) {
        return BASEURL + "/equipment/" + equipment;
    }

    public static String getBaseURL() {
        return BASEURL;
    }
}
