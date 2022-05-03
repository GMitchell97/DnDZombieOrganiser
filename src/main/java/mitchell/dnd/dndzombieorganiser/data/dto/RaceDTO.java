package mitchell.dnd.dndzombieorganiser.data.dto;

import com.fasterxml.jackson.databind.JsonNode;
import mitchell.dnd.dndzombieorganiser.data.properties.Rules;

import static mitchell.dnd.dndzombieorganiser.Constants.*;

public class RaceDTO {

    JsonNode json;

    public RaceDTO(JsonNode json) {
        this.json = json;
    }

    public int getAbilityScoreBonus(Rules.ability a) {
        if (json.get(ABILITY_BONUS).isEmpty()) {
            return 0;
        }
        for (JsonNode node : json.get(ABILITY_BONUS)) {
            if (node.get(ABILITY_SCORE).get("index").asText().equals(a.toString().substring(0,3))) {
                return node.get("bonus").asInt();
            }
        }
        return 0;
    }
}
