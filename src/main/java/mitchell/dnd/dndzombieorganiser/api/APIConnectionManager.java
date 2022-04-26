package mitchell.dnd.dndzombieorganiser.api;

public class APIConnectionManager {

    private static final String BASEURL = "https://www.dnd5eapi.co/api";

    public static Races getRace() {
        return new Races();
    }

    public static Monsters getMonster() {
        return new Monsters();
    }

    public static String getBaseURL() {
        return BASEURL;
    }
}
