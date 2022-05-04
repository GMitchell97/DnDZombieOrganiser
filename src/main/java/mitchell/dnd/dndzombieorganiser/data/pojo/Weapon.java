package mitchell.dnd.dndzombieorganiser.data.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import mitchell.dnd.dndzombieorganiser.core.DiceRoller;

public class Weapon {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("ToHit")
    private int toHit;
    @JsonProperty("Damage")
    private int damage;
    @JsonProperty("DamageBonus")
    private int damageBonus;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("ToHit")
    public int getToHit() {
        return toHit;
    }

    @JsonProperty("ToHit")
    public void setToHit(int toHit) {
        this.toHit = toHit;
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

    @JsonIgnore
    public Pair attack(DiceRoller dice) {
        return new Pair(dice.rollDice(20) + toHit, dice.rollDice(damage) + damageBonus);
    }
}
