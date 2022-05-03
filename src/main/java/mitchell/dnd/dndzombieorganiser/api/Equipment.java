package mitchell.dnd.dndzombieorganiser.api;

public class Equipment {

    private static String endPointURL = APIConnectionManager.getBaseURL() + "/equipment";

    public String getAll() {
        return endPointURL;
    }

    public String getEquipment(String item) {
        return endPointURL + "/" + item;
    }

}
