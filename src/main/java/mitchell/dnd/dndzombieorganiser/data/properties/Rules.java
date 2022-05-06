package mitchell.dnd.dndzombieorganiser.data.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Rules {

    private Properties rules = new Properties();

    public enum creature {
        zombie,
        skeleton
    }

    public enum type {
        flat,
        scale,
        set
    }

    public enum ability {
        strength,
        dexterity,
        constitution,
        intelligence,
        wisdom,
        charisma
    }

    public Rules() {
        try (InputStream file = Rules.class.getResourceAsStream("rules.properties")) {
            rules.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAttribute(String attribute) {
        return rules.getProperty(attribute);
    }

    public double getAbilityScoreAdjustment(creature c, type t, ability a) {
        String value = getAttribute(c + "." + t + "." + a);
        if (value != null) {
            return Double.parseDouble(value);
        } else {
            return 0;
        }
    }

    public int getOwnerLevel() {
        return Integer.parseInt(getAttribute("owner.level"));
    }

    public int getOwnerProficiency() {
        return Integer.parseInt(getAttribute("owner.proficiency"));
    }

    public boolean ownerHasUndeadThralls() {
        return Boolean.parseBoolean(getAttribute("owner.undeadthralls"));
    }

}
