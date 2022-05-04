package mitchell.dnd.dndzombieorganiser.data.builders;

import com.fasterxml.jackson.databind.JsonNode;
import mitchell.dnd.dndzombieorganiser.data.pojo.Weapon;

public class WeaponBuilder {

    public static Weapon createWeapon(JsonNode weaponJson) {
        Weapon weapon = new Weapon();

        weapon.setToHit(0);
        String damageDice = weaponJson.get("damage").get("damage_dice").asText();
        weapon.setDamage(Integer.parseInt(damageDice.split("d")[1]));
        weapon.setDamageBonus(0);

        return weapon;
    }
}
