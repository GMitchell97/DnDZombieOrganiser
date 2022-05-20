package mitchell.dnd.dndzombieorganiser.data.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import mitchell.dnd.dndzombieorganiser.Constants;
import mitchell.dnd.dndzombieorganiser.core.DiceRoller;

import java.util.List;

public class Weapon {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("AttackBonus")
    private int attackBonus;
    @JsonProperty("DamageDie")
    private int damageDie;
    @JsonProperty("DamageDieAmount")
    private int damageDieAmount;
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

    @JsonProperty("DamageDie")
    public int getDamageDie() {
        return damageDie;
    }

    @JsonProperty("DamageDie")
    public void setDamageDie(int damageDie) {
        this.damageDie = damageDie;
    }

    @JsonProperty("DamageDieAmount")
    public int getDamageDieAmount() {
        return damageDieAmount;
    }

    @JsonProperty("DamageDieAmount")
    public void setDamageDieAmount(int damageDieAmount) {
        this.damageDieAmount = damageDieAmount;
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
    public Pair attack(DiceRoller dice, Constants.RollType r) {
        int hitDie = 0;
        switch (r) {
            case NORMAL -> hitDie = dice.rollDice(20);
            case ADVANTAGE -> hitDie = Math.max(dice.rollDice(20), dice.rollDice(20));
            case DISADVANTAGE -> hitDie = Math.min(dice.rollDice(20), dice.rollDice(20));
        }
        int damageDieRolls = (hitDie == 20 ? 2 : 1) * getDamageDieAmount();
        int damageAmount = 0;
        for (int i = 0; i < damageDieRolls; i++) {
            damageAmount += dice.rollDice(getDamageDie());
        }
        return new Pair(hitDie + attackBonus, damageAmount + damageBonus);
    }

    @JsonIgnore
    public String toString() {
        return getName() + ", H: 1d20 +" + getAttackBonus() + ", D: " + getDamageDieAmount() + "d" + getDamageDie() + " +" + getDamageBonus();
    }
}
