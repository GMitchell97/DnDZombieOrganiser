package mitchell.dnd.dndzombieorganiser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

    public static String ABILITY_BONUS = "ability_bonuses";
    public static String ABILITY_SCORE = "ability_score";
    public static String AC = "armor_class";
    public static String HP = "hit_points";

    public static List<String> RACES = List.of("dragonborn", "dwarf", "elf", "gnome", "half-elf", "half-orc", "halfling", "human", "tiefling");
    public static List<String> ARMOUR = List.of("none", "padded-armor", "leather-armor", "studded-leather-armor", "hide-armor", "chain-shirt", "scale-mail", "breastplate",
            "half-plate-armor", "ring-mail", "chain-mail", "splint-armor", "plate-armor");

    public enum RollType {
        ADVANTAGE,
        NORMAL,
        DISADVANTAGE
    }
}
