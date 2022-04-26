package mitchell.dnd.dndzombieorganiser.api;

import mitchell.dnd.dndzombieorganiser.Constants;

import java.lang.reflect.Field;

public class Races {

    private static String endPointURL = APIConnectionManager.getBaseURL() + "/races";

    public String getAll() {
        return endPointURL;
    }

    public String getRace(String race) {
        if (Constants.RACES.contains(race))
            return endPointURL + "/" + race;
        return "";
    }
}
