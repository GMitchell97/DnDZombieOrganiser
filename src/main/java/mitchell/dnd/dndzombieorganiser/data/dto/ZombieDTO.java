package mitchell.dnd.dndzombieorganiser.data.dto;

import java.util.*;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import mitchell.dnd.dndzombieorganiser.Constants;
import mitchell.dnd.dndzombieorganiser.core.DiceRoller;
import mitchell.dnd.dndzombieorganiser.data.pojo.Ability;
import mitchell.dnd.dndzombieorganiser.data.pojo.Pair;
import mitchell.dnd.dndzombieorganiser.data.pojo.Weapon;
import mitchell.dnd.dndzombieorganiser.data.properties.Rules;

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
    @JsonProperty("Armour")
    private String armour;
    @JsonProperty("Weapons")
    private Map<String,Weapon> weapons;
    @JsonProperty("Speed")
    private int speed;

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

    @JsonIgnore
    public int getAbilityScore(String ability) {
        return getAbilityScores().stream().filter(a -> a.getName().equals(ability)).mapToInt(Ability::getValue).findFirst().orElse(0);
    }

    @JsonIgnore
    public int getAbilityScoreModifier(String ability) {
        return getAbilityScores().stream().filter(a -> a.getName().equals(ability)).mapToInt(Ability::getModifier).findFirst().orElse(0);
    }

    @JsonIgnore
    public void setAbilityScore(String ability, int score) {
        getAbilityScores().stream().filter(a -> a.getName().equals(ability)).findFirst().ifPresentOrElse(a -> a.setValue(score), new Runnable() {
            @Override
            public void run() {
                if (Arrays.stream(Rules.ability.values()).map(Enum::name).toList().contains(ability)) {
                    getAbilityScores().add(new Ability(ability, score));
                }
            }
        });
    }

    @JsonProperty("Armour")
    public String getArmour() {
        if (armour != null) {
            if (Constants.ARMOUR.contains(armour)) {
                return armour;
            }
        }
        armour = "none";
        return armour;
    }

    @JsonProperty("Armour")
    public void setArmour(String armour) {
        this.armour = armour;
    }

    @JsonProperty("Weapons")
    public Map<String, Weapon> getWeapons() {
        if (weapons == null) {
            weapons = new HashMap<>();
        }
        return weapons;
    }

    @JsonIgnore
    public Weapon getWeapon(String weaponSlot) {
        return getWeapons().get(weaponSlot);
    }

    @JsonProperty("Weapons")
    public void setWeapons(Map<String, Weapon> weapons) {
        this.weapons = weapons;
    }

    @JsonIgnore
    public void addWeapon(String slot, Weapon weapon) {
        getWeapons().put(slot, weapon);
    }

    @JsonIgnore
    public Pair makeWeaponAttack(String weaponSlot, DiceRoller dice, Constants.RollType r) {
        return Objects.requireNonNull(getWeapons().get(weaponSlot)).attack(dice, r);
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

    @JsonProperty("AC")
    public void setAc(int ac) {
        this.ac = Integer.toString(ac);
    }

    @JsonProperty("Speed")
    public int getSpeed() {
        return speed;
    }

    @JsonProperty("Speed")
    public void setSpeed(int speed) {
        this.speed = speed;
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
