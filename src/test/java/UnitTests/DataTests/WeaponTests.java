package UnitTests.DataTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import mitchell.dnd.dndzombieorganiser.core.DiceRoller;
import mitchell.dnd.dndzombieorganiser.data.builders.WeaponBuilder;
import mitchell.dnd.dndzombieorganiser.data.pojo.Pair;
import mitchell.dnd.dndzombieorganiser.data.pojo.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTests {

    @Test
    public void ValidWeaponAttack() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode parentNode = objectMapper.createObjectNode();

        ObjectNode damageNode = objectMapper.createObjectNode();

        damageNode.put("damage_dice", "1d6");

        parentNode.set("damage", damageNode);

        Weapon weapon = WeaponBuilder.createWeapon(parentNode);

        DiceRoller dice = new DiceRoller();
        Pair attack = weapon.attack(dice);
        assertEquals(dice.getHistory().stream().filter(p -> p.getA() == 20).mapToInt(Pair::getB).sum(), attack.getA());
        assertEquals(dice.getHistory().stream().filter(p -> p.getA() == 6).mapToInt(Pair::getB).sum(), attack.getB());
    }
}
