package mitchell.dnd.dndzombieorganiser.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import mitchell.dnd.dndzombieorganiser.UI.ZombieWrapper;
import mitchell.dnd.dndzombieorganiser.core.DiceRoller;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "NextID",
        "Zombies"
})
@Generated("jsonschema2pojo")
public class DataDTO {

    @JsonProperty("NextID")
    private int nextID;

    @JsonProperty("RollHistory")
    public List<Pair> RollHistory;

    @JsonProperty("Zombies")
    private List<ZombieDTO> zombies = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonIgnore
    private DiceRoller diceRoller;

    @JsonProperty("Zombies")
    public List<ZombieDTO> getZombies() {
            if (zombies == null) {
                zombies = new ArrayList<>();
            }
            return zombies;
    }

    public void addZombie(ZombieDTO zombie) {
        zombie.setId(Integer.toString(nextID));
        nextID++;
        getZombies().add(zombie);
    }

    @JsonIgnore
    public List<ZombieWrapper> getZombiesWithWrapper() {
        return new ArrayList<ZombieWrapper>(getZombies().stream().map(z -> new ZombieWrapper(z)).toList());
    }

    @JsonProperty("Zombies")
    public void setZombies(List<ZombieDTO> zombies) {
        this.zombies = zombies;
    }

    @JsonProperty("RollHistory")
    public List<Pair> getRollHistory() {
        if (RollHistory == null) {
            RollHistory = new ArrayList<>();
        }
        return RollHistory;
    }

    @JsonProperty("RollHistory")
    public void setRollHistory(List<Pair> rollHistory) {
        RollHistory = rollHistory;
    }

    @JsonIgnore
    public DiceRoller getDiceRoller() {
        if (diceRoller == null) {
            diceRoller = new DiceRoller();
            if (RollHistory != null)
                diceRoller.addHistory(RollHistory);
        }
        return diceRoller;
    }

    @JsonIgnore
    public void saveRollHistory() {
        if (diceRoller != null)
            RollHistory = diceRoller.getHistory();
    }

    @JsonIgnore
    public void setDiceRoller(DiceRoller diceRoller) {
        this.diceRoller = diceRoller;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
