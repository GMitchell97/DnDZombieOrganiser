package mitchell.dnd.dndzombieorganiser.api;

import mitchell.dnd.dndzombieorganiser.Constants;

public class Monsters {

    private static String endPointURL = APIConnectionManager.getBaseURL() + "/monsters";

    public String getAll() {
        return endPointURL;
    }

    public String getMonster(String monster) {
        return endPointURL + "/" + monster;
    }
}
