package mitchell.dnd.dndzombieorganiser.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ability {

    @JsonProperty("value")
    int value;
    @JsonProperty("name")
    String name;

    public Ability(String name, int value) {
        this.value = value;
        this.name = name;
    }

    @JsonProperty("value")
    public int getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(int value) {
        this.value = value;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public int getModifier() {
        return (int) Math.floor(((double)value - 10.0) / 2);
    }

}
