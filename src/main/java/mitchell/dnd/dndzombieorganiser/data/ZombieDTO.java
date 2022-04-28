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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ID",
        "Name",
        "HP",
        "AC"
})
@Generated("jsonschema2pojo")
public class ZombieDTO {

    @JsonProperty("ID")
    private String id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("HP")
    private String hp;
    @JsonProperty("AC")
    private String ac;
    @JsonProperty("Ability_Scores")
    private List<Ability> abilityScores;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Ability_Scores")
    public List<Ability> getAbilityScores() {
        if (abilityScores == null) {
            abilityScores = new ArrayList<>();
        }
        return abilityScores;
    }

    @JsonProperty("Ability_Scores")
    public void setAbilityScores(List<Ability> abilityScores) {
        this.abilityScores = abilityScores;
    }

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    @JsonProperty("ID")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("HP")
    public String getHp() {
        return hp;
    }

    @JsonProperty("HP")
    public void setHp(String hp) {
        this.hp = hp;
    }

    @JsonProperty("AC")
    public String getAc() {
        return ac;
    }

    @JsonProperty("AC")
    public void setAc(String ac) {
        this.ac = ac;
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
