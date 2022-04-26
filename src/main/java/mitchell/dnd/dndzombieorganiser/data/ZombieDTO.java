package mitchell.dnd.dndzombieorganiser.data;

import java.util.HashMap;
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
    @JsonProperty("strength")
    private String strength;
    @JsonProperty("dexterity")
    private String dexterity;
    @JsonProperty("constitution")
    private String constitution;
    @JsonProperty("intelligence")
    private String intelligence;
    @JsonProperty("wisdom")
    private String wisdom;
    @JsonProperty("charisma")
    private String charisma;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("strength")
    public String getStrength() {
        return strength;
    }

    @JsonProperty("strength")
    public void setStrength(String strength) {
        this.strength = strength;
    }

    @JsonProperty("dexterity")
    public String getDexterity() {
        return dexterity;
    }

    @JsonProperty("dexterity")
    public void setDexterity(String dexterity) {
        this.dexterity = dexterity;
    }

    @JsonProperty("constitution")
    public String getConstitution() {
        return constitution;
    }

    @JsonProperty("constitution")
    public void setConstitution(String constitution) {
        this.constitution = constitution;
    }

    @JsonProperty("intelligence")
    public String getIntelligence() {
        return intelligence;
    }

    @JsonProperty("intelligence")
    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    @JsonProperty("wisdom")
    public String getWisdom() {
        return wisdom;
    }

    @JsonProperty("wisdom")
    public void setWisdom(String wisdom) {
        this.wisdom = wisdom;
    }

    @JsonProperty("charisma")
    public String getCharisma() {
        return charisma;
    }

    @JsonProperty("charisma")
    public void setCharisma(String charisma) {
        this.charisma = charisma;
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
