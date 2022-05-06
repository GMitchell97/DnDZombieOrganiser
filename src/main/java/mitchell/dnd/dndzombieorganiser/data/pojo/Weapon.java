package mitchell.dnd.dndzombieorganiser.data.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import mitchell.dnd.dndzombieorganiser.core.DiceRoller;

import java.util.List;

public class Weapon {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("AttackBonus")
    private int attackBonus;
    @JsonProperty("Damage")
    private int damage;
    @JsonProperty("DamageBonus")
    private int damageBonus;
    @JsonProperty("Properties")
    private List<String> properties;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("ToHit")
    public int getAttackBonus() {
        return attackBonus;
    }

    @JsonProperty("ToHit")
    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    @JsonProperty("Damage")
    public int getDamage() {
        return damage;
    }

    @JsonProperty("Damage")
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @JsonProperty("DamageBonus")
    public int getDamageBonus() {
        return damageBonus;
    }

    @JsonProperty("DamageBonus")
    public void setDamageBonus(int damageBonus) {
        this.damageBonus = damageBonus;
    }

    @JsonProperty("Properties")
    public List<String> getProperties() {
        return properties;
    }

    @JsonProperty("Properties")
    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    @JsonIgnore
    public Pair attack(DiceRoller dice) {
        return new Pair(dice.rollDice(20) + attackBonus, dice.rollDice(damage) + damageBonus);
    }

    @JsonIgnore
    public String toString() {
        return getName() + ", H: 1d20 +" + getAttackBonus() + ", D: 1d" + getDamage() + " +" + getDamageBonus();
    }
}
