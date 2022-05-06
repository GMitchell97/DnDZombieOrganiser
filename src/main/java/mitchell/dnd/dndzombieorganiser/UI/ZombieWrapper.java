package mitchell.dnd.dndzombieorganiser.UI;

import javafx.beans.property.SimpleStringProperty;
import mitchell.dnd.dndzombieorganiser.data.dto.ZombieDTO;
import mitchell.dnd.dndzombieorganiser.data.pojo.Weapon;

public class ZombieWrapper {

    private SimpleStringProperty ID;
    private SimpleStringProperty Name;
    private SimpleStringProperty AC;
    private SimpleStringProperty HP;
    private SimpleStringProperty speed;
    private SimpleStringProperty attack;
    private SimpleStringProperty strength;
    private SimpleStringProperty dexterity;
    private SimpleStringProperty constitution;
    private SimpleStringProperty intelligence;
    private SimpleStringProperty wisdom;
    private SimpleStringProperty charisma;

    private final ZombieDTO zombie;

    public ZombieWrapper(ZombieDTO z) {
        zombie = z;
        loadVariables();
    }

    private void loadVariables() {
        ID = new SimpleStringProperty(getID());
        Name = new SimpleStringProperty(getName());
        AC = new SimpleStringProperty(getAC());
        HP = new SimpleStringProperty(getHP());
        speed = new SimpleStringProperty(getSpeed());
        attack = new SimpleStringProperty(getAttack());
        strength = new SimpleStringProperty(getStrength());
        dexterity = new SimpleStringProperty(getDexterity());
        constitution = new SimpleStringProperty(getConstitution());
        intelligence = new SimpleStringProperty(getIntelligence());
        wisdom = new SimpleStringProperty(getWisdom());
        charisma = new SimpleStringProperty(getCharisma());
    }

    public String getAC() {
        return zombie.getAc() + " (" + zombie.getArmour() + ")";
    }

    public SimpleStringProperty ACProperty() {
        return AC;
    }

    public void setAC(String AC) {
        zombie.setAc(AC);
    }

    public String getHP() {
        return zombie.getHp();
    }

    public SimpleStringProperty HPProperty() {
        return HP;
    }

    public void setHP(String HP) {
        zombie.setHp(HP);
    }

    public String getName() {
        return zombie.getName();
    }

    public SimpleStringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        zombie.setName(name);
    }

    public String getID() {
        return zombie.getId();
    }

    public SimpleStringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        zombie.setId(ID);
    }

    public String getStrength() {
        return Integer.toString(zombie.getAbilityScore("strength"));
    }

    public SimpleStringProperty strengthProperty() {
        return strength;
    }

    public void setStrength(String strength) {
        zombie.setAbilityScore("strength", Integer.parseInt(strength));
    }

    public String getDexterity() {
        return Integer.toString(zombie.getAbilityScore("dexterity"));
    }

    public SimpleStringProperty dexterityProperty() {
        return dexterity;
    }

    public void setDexterity(String dexterity) {
        zombie.setAbilityScore("dexterity", Integer.parseInt(dexterity));
    }

    public String getConstitution() {
        return Integer.toString(zombie.getAbilityScore("constitution"));
    }

    public SimpleStringProperty constitutionProperty() {
        return constitution;
    }

    public void setConstitution(String constitution) {
        zombie.setAbilityScore("constitution", Integer.parseInt(constitution));
    }

    public String getIntelligence() {
        return Integer.toString(zombie.getAbilityScore("intelligence"));
    }

    public SimpleStringProperty intelligenceProperty() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        zombie.setAbilityScore("intelligence", Integer.parseInt(intelligence));
    }

    public String getWisdom() {
        return Integer.toString(zombie.getAbilityScore("wisdom"));
    }

    public SimpleStringProperty wisdomProperty() {
        return wisdom;
    }

    public void setWisdom(String wisdom) {
        zombie.setAbilityScore("wisdom", Integer.parseInt(wisdom));
    }

    public String getCharisma() {
        return Integer.toString(zombie.getAbilityScore("charisma"));
    }

    public SimpleStringProperty charismaProperty() {
        return charisma;
    }

    public void setCharisma(String charisma) {
        zombie.setAbilityScore("charisma", Integer.parseInt(charisma));
    }

    public String getSpeed() {
        return zombie.getSpeed() + "ft";
    }

    public SimpleStringProperty speedProperty() {
        return speed;
    }

    public String getAttack() {
        StringBuilder weapons = new StringBuilder();
        for (Weapon weapon : zombie.getWeapons().values()) {
            weapons.append(weapon.toString()).append("\n");
        }
        return weapons.toString();
    }

    public SimpleStringProperty meleeAttackProperty() {
        return attack;
    }

}
