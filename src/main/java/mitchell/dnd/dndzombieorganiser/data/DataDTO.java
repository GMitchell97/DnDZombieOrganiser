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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Zombies"
})
@Generated("jsonschema2pojo")
public class DataDTO {

    @JsonProperty("Zombies")
    private List<ZombieDTO> zombies = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Zombies")
    public List<ZombieDTO> getZombies() {
            if (zombies == null) {
                zombies = new ArrayList<>();
            }
            return zombies;
    }

    @JsonIgnore
    public List<ZombieWrapper> getZombiesWithWrapper() {
        return new ArrayList<ZombieWrapper>(getZombies().stream().map(z -> new ZombieWrapper(z)).toList());
    }

    @JsonProperty("Zombies")
    public void setZombies(List<ZombieDTO> zombies) {
        this.zombies = zombies;
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
