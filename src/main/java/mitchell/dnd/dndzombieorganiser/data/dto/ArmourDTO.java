package mitchell.dnd.dndzombieorganiser.data.dto;

import com.fasterxml.jackson.databind.JsonNode;

import static mitchell.dnd.dndzombieorganiser.Constants.ABILITY_BONUS;
import static mitchell.dnd.dndzombieorganiser.Constants.ABILITY_SCORE;

public class ArmourDTO {

    JsonNode json;

    public ArmourDTO(JsonNode json) {
        this.json = json;
    }

    public int getBaseAC() {
        return json.get("armor_class").get("base").asInt();
    }

    public boolean isDexBonus() {
        return json.get("armor_class").get("dex_bonus").asBoolean();
    }

    public int getMaxDexBonus() {
        JsonNode max = json.get("armor_class").get("max_bonus");
        return max != null ? max.asInt() : 10;
    }

    public int getStrengthRequirement() {
        return json.get("str_minimum").asInt();
    }

    public boolean isStealthDisadvantage() {
        return json.get("stealth_disadvantage").asBoolean();
    }
}
