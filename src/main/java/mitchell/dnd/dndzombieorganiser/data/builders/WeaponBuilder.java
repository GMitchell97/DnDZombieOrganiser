package mitchell.dnd.dndzombieorganiser.data.builders;

import com.fasterxml.jackson.databind.JsonNode;
import mitchell.dnd.dndzombieorganiser.data.dto.ZombieDTO;
import mitchell.dnd.dndzombieorganiser.data.pojo.Weapon;
import mitchell.dnd.dndzombieorganiser.data.properties.Rules;

import java.util.ArrayList;
import java.util.List;

public class WeaponBuilder {

    public static Weapon createWeapon(JsonNode weaponJson) {
        Weapon weapon = new Weapon();

        String damageDice = weaponJson.get("damage").get("damage_dice").asText();
        weapon.setDamage(Integer.parseInt(damageDice.split("d")[1]));

        List<String> props = new ArrayList<>();
        for (JsonNode property : weaponJson.get("properties")) {
            props.add(property.get("index").asText());
        }

        weapon.setProperties(props);
        weapon.setName(weaponJson.get("name").asText());

        return weapon;
    }

    public static void configWeapon(Weapon weapon, ZombieDTO zombie) {
        int modifier;
        if (weapon.getProperties().contains("finesse")) {
            modifier = zombie.getAbilityScoreModifier(getBetterFinesseAbility(zombie));
        } else {
            modifier = zombie.getAbilityScoreModifier("strength");
        }

        Rules rules = new Rules();
        if (rules.ownerHasUndeadThralls()) {
            weapon.setDamageBonus(modifier + rules.getOwnerProficiency());
        } else {
            weapon.setDamageBonus(modifier);
        }
        weapon.setAttackBonus(modifier);
    }

    public static void configWeapon(String weapon, ZombieDTO zombie) {
        configWeapon(zombie.getWeapon(weapon), zombie);
    }

    public static String getBetterFinesseAbility(ZombieDTO zombie) {
        return zombie.getAbilityScore("strength") > zombie.getAbilityScore("dexterity") ? "strength" : "dexterity";
    }
}
